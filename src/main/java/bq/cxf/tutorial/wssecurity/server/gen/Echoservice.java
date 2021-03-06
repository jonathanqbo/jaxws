package bq.cxf.tutorial.wssecurity.server.gen;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.7.8
 * 2014-02-26T16:51:50.312-05:00
 * Generated source version: 2.7.8
 * 
 */
@WebService(targetNamespace = "http://bq.cxf.tutorial/echoservice/", name = "echoservice")
@XmlSeeAlso({ObjectFactory.class})
public interface Echoservice {

    @WebResult(name = "out", targetNamespace = "")
    @RequestWrapper(localName = "echo", targetNamespace = "http://bq.cxf.tutorial/echoservice/", className = "bq.cxf.tutorial.wssecurity.server.gen.Echo")
    @WebMethod(action = "http://bq.cxf.tutorial/echoservice/echo")
    @ResponseWrapper(localName = "echoResponse", targetNamespace = "http://bq.cxf.tutorial/echoservice/", className = "bq.cxf.tutorial.wssecurity.server.gen.EchoResponse")
    public java.lang.String echo(
        @WebParam(name = "message", targetNamespace = "")
        java.lang.String message
    );
}
