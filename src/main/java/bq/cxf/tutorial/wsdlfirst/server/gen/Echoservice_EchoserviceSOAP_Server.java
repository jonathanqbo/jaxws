
package bq.cxf.tutorial.wsdlfirst.server.gen;

import javax.xml.ws.Endpoint;

/**
 * This class was generated by Apache CXF 2.7.8
 * 2014-02-23T10:34:26.871-05:00
 * Generated source version: 2.7.8
 * 
 */
 
public class Echoservice_EchoserviceSOAP_Server{

    protected Echoservice_EchoserviceSOAP_Server() throws java.lang.Exception {
        System.out.println("Starting Server");
        Object implementor = new EchoserviceImpl();
        String address = "http://localhost:8080/jaxws/gen/";
        Endpoint.publish(address, implementor);
    }
    
    public static void main(String args[]) throws java.lang.Exception { 
        new Echoservice_EchoserviceSOAP_Server();
        System.out.println("Server ready..."); 
        
        Thread.sleep(5 * 60 * 1000); 
        System.out.println("Server exiting");
        System.exit(0);
    }
}
