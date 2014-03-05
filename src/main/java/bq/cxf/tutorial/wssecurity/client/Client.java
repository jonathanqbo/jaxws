/*
Copyright (c) 2014 (Jonathan Q. Bo) 

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

The Software shall be used for Good, not Evil.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

package bq.cxf.tutorial.wssecurity.client;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.xml.namespace.QName;

import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.ws.security.wss4j.DefaultCryptoCoverageChecker;
import org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;

import bq.cxf.tutorial.wssecurity.client.gen.Echoservice;
import bq.cxf.tutorial.wssecurity.client.gen.Echoservice_Service;

/**
 * <b>  </b>
 *
 * <p> </p>
 *
 * @author Jonathan Q. Bo (jonathan.q.bo@gmail.com)
 *
 * Created at Feb 26, 2014 10:45:22 PM
 *
 */
public class Client {
	
	private static final String WSSE_NS 
    	= "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd";
	private static final String WSU_NS
	    = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd";

	private static final QName SERVICE_NAME = new QName("http://bq.cxf.tutorial/echoservice/", "echoservice");
	private static final String wsdlURL = "http://localhost:9999/jaxws/ws/securityechoservice?wsdl";
			
	public static void main(String[] args){
		
		Map<String, Object> outProps = new HashMap<String, Object>();
        outProps.put("action", "UsernameToken Timestamp Signature Encrypt");

        outProps.put("passwordType", "PasswordDigest");
        outProps.put("user", "bq");
        outProps.put("signatureUser", "client");
        outProps.put("passwordCallbackClass", UTPasswordCallback.class.getName());

        outProps.put("encryptionUser", "server");
        outProps.put("encryptionPropFile", "bq/cxf/tutorial/wssecurity/properties/client_encrypt.properties");
        outProps.put("encryptionKeyIdentifier", "IssuerSerial");
        outProps.put("encryptionParts",
                     "{Element}{" + WSSE_NS + "}UsernameToken;"
                     + "{Content}{http://schemas.xmlsoap.org/soap/envelope/}Body");

        outProps.put("signaturePropFile", "bq/cxf/tutorial/wssecurity/properties/client_sign.properties");
        outProps.put("signatureKeyIdentifier", "DirectReference");
        outProps.put("signatureParts",
                     "{Element}{" + WSU_NS + "}Timestamp;"
                     + "{Element}{http://schemas.xmlsoap.org/soap/envelope/}Body;"
                    );
//
//        outProps.put("encryptionKeyTransportAlgorithm", 
//                     "http://www.w3.org/2001/04/xmlenc#rsa-oaep-mgf1p");
//        outProps.put("signatureAlgorithm", "http://www.w3.org/2000/09/xmldsig#rsa-sha1");


        Map<String, Object> inProps = new HashMap<String, Object>();

        // multiple action
        inProps.put("action", "UsernameToken Timestamp Signature Encrypt");
        inProps.put("passwordType", "PasswordText");
        
        //
        inProps.put("passwordCallbackClass", UTPasswordCallback.class.getName());

        // client use client-side private key to decrypt
        inProps.put("decryptionPropFile", "bq/cxf/tutorial/wssecurity/properties/client_sign.properties");
//        inProps.put("encryptionKeyIdentifier", "IssuerSerial");
//        inProps.put("encryptionKeyTransportAlgorithm", 
//        		"http://www.w3.org/2001/04/xmlenc#rsa-oaep-mgf1p");

        // client use server-side public key to validate signature
        inProps.put("signaturePropFile", "bq/cxf/tutorial/wssecurity/properties/client_encrypt.properties");
//        inProps.put("signatureKeyIdentifier", "DirectReference");
//        inProps.put("signatureAlgorithm", "http://www.w3.org/2000/09/xmldsig#rsa-sha1");


        // Check to make sure that the SOAP Body and Timestamp were signed,
        // and that the SOAP Body was encrypted
        DefaultCryptoCoverageChecker coverageChecker = new DefaultCryptoCoverageChecker();
        coverageChecker.setSignBody(true);
        coverageChecker.setSignTimestamp(true);
        coverageChecker.setEncryptBody(true);
        
		Echoservice_Service ss = null;
		try {
			ss = new Echoservice_Service(new URL(wsdlURL), SERVICE_NAME);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
        Echoservice port = ss.getEchoserviceSOAP();  
        
        org.apache.cxf.endpoint.Client client = ClientProxy.getClient(port);
        client.getInInterceptors().add(new WSS4JInInterceptor(inProps));
        client.getOutInterceptors().add(new WSS4JOutInterceptor(outProps));
        client.getInInterceptors().add(coverageChecker);
        
        {
        System.out.println("Invoking echo...");
        java.lang.String _echo_message = "Hello, World!";
        java.lang.String _echo__return = port.echo(_echo_message);
        System.out.println("echo.result=" + _echo__return);


        }

        System.exit(0);
	}
	
}
