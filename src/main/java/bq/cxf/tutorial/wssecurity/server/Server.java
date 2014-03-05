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

package bq.cxf.tutorial.wssecurity.server;

import java.util.HashMap;
import java.util.Map;

import javax.xml.ws.Endpoint;

import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.ws.security.wss4j.DefaultCryptoCoverageChecker;
import org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;

import bq.cxf.tutorial.wssecurity.server.gen.EchoserviceImpl;

/**
 * <b>  </b>
 *
 * <p> </p>
 *
 * @author Jonathan Q. Bo (jonathan.q.bo@gmail.com)
 *
 * Created at Feb 26, 2014 4:29:21 PM
 *
 */
public class Server{
	private static final String WSSE_NS 
    	= "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd";
	private static final String WSU_NS
    	= "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd";
	
	protected Server() throws Exception {
	    System.out.println("Starting Server");
	
	    Object implementor = new EchoserviceImpl();
	    String address = "http://localhost:8080/jaxws/ws/securityechoservice";
	    EndpointImpl endpoint = (EndpointImpl)Endpoint.publish(address, implementor);
	    
	    // OutInterceptor
	    Map<String, Object> outProps = new HashMap<String, Object>();
	    outProps.put("action", "UsernameToken Timestamp Signature Encrypt");
	
	    // Username Token
	    outProps.put("user", "bq");
	    outProps.put("signatureUser", "server");
	    outProps.put("passwordType", "PasswordText");
	    outProps.put("passwordCallbackClass", UTPasswordCallback.class.getName());
	
	    // encrypt usernametoken and body using client public key
	    outProps.put("encryptionUser", "client");
	    outProps.put("encryptionPropFile", "bq/cxf/tutorial/wssecurity/properties/server_sign_verf.properties");
//	    outProps.put("encryptionKeyIdentifier", "IssuerSerial");
	    outProps.put("encryptionParts", "{Element}{" + WSSE_NS + "}UsernameToken;"
	                     + "{Content}{http://schemas.xmlsoap.org/soap/envelope/}Body");
	
	    // signature using server private key
	    outProps.put("signaturePropFile", "bq/cxf/tutorial/wssecurity/properties/server_decrypt.properties");
//	    outProps.put("signatureKeyIdentifier", "DirectReference");
	    outProps.put("signatureParts", "{Element}{" + WSU_NS + "}Timestamp;"
	                     + "{Element}{http://schemas.xmlsoap.org/soap/envelope/}Body");
	
//	    outProps.put("encryptionKeyTransportAlgorithm", 
//	                     "http://www.w3.org/2001/04/xmlenc#rsa-oaep-mgf1p");
//	    outProps.put("signatureAlgorithm", "http://www.w3.org/2000/09/xmldsig#rsa-sha1");
	
	    endpoint.getOutInterceptors().add(new WSS4JOutInterceptor(outProps));
	
	    // InInterceptor
	    Map<String, Object> inProps = new HashMap<String, Object>();
	
	    inProps.put("action", "UsernameToken Timestamp Signature Encrypt");
	    inProps.put("passwordType", "PasswordDigest");
	    inProps.put("passwordCallbackClass", UTPasswordCallback.class.getName());
	
	    inProps.put("decryptionPropFile", "bq/cxf/tutorial/wssecurity/properties/server_decrypt.properties");
//	    inProps.put("encryptionKeyIdentifier", "IssuerSerial");
	
	    inProps.put("signaturePropFile", "bq/cxf/tutorial/wssecurity/properties/server_sign_verf.properties");
//	    inProps.put("signatureKeyIdentifier", "DirectReference");
//	    inProps.put("encryptionKeyTransportAlgorithm", 
//	                "http://www.w3.org/2001/04/xmlenc#rsa-oaep-mgf1p");
//	    inProps.put("signatureAlgorithm", "http://www.w3.org/2000/09/xmldsig#rsa-sha1");
	
	    endpoint.getInInterceptors().add(new WSS4JInInterceptor(inProps));
	
	    // Check to make sure that the SOAP Body and Timestamp were signed,
	    // and that the SOAP Body was encrypted
	    DefaultCryptoCoverageChecker coverageChecker = new DefaultCryptoCoverageChecker();
	    coverageChecker.setSignBody(true);
	    coverageChecker.setSignTimestamp(true);
	    coverageChecker.setEncryptBody(true);
	    endpoint.getInInterceptors().add(coverageChecker);
	}

	public static void main(String args[]) throws Exception {
	    new Server();
	    System.out.println("Server ready...");
	
	    Thread.sleep(50 * 60 * 1000);
	
	    System.out.println("Server exiting");
	    System.exit(0);
	}
}
