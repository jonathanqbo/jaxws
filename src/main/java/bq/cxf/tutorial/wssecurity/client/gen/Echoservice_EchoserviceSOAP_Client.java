
package bq.cxf.tutorial.wssecurity.client.gen;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import org.apache.cxf.frontend.ClientProxy;

/**
 * This class was generated by Apache CXF 2.7.8
 * 2014-02-26T16:55:23.667-05:00
 * Generated source version: 2.7.8
 * 
 */
public final class Echoservice_EchoserviceSOAP_Client {

    private static final QName SERVICE_NAME = new QName("http://bq.cxf.tutorial/echoservice/", "echoservice");

    private Echoservice_EchoserviceSOAP_Client() {
    }

    public static void main(String args[]) throws java.lang.Exception {
        URL wsdlURL = Echoservice_Service.WSDL_LOCATION;
        if (args.length > 0 && args[0] != null && !"".equals(args[0])) { 
            File wsdlFile = new File(args[0]);
            try {
                if (wsdlFile.exists()) {
                    wsdlURL = wsdlFile.toURI().toURL();
                } else {
                    wsdlURL = new URL(args[0]);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
      
        Echoservice_Service ss = new Echoservice_Service(wsdlURL, SERVICE_NAME);
        Echoservice port = ss.getEchoserviceSOAP();  
        
        {
        System.out.println("Invoking echo...");
        java.lang.String _echo_message = "Hello, World!";
        java.lang.String _echo__return = port.echo(_echo_message);
        System.out.println("echo.result=" + _echo__return);


        }

        System.exit(0);
    }

}
