
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package bq.cxf.tutorial.soapheader.server.gen;

import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 2.7.8
 * 2014-02-24T13:09:17.714-05:00
 * Generated source version: 2.7.8
 * 
 */

@javax.jws.WebService(
                      serviceName = "echoHeaderService",
                      portName = "echoheaderserviceSOAP",
                      targetNamespace = "http://bq.cxf.tutorial/echoservicewithheader/",
                      wsdlLocation = "src/main/resource/bq/cxf/tutorial/soapheader/echoservicewithheader.wsdl",
                      endpointInterface = "bq.cxf.tutorial.soapheader.server.gen.EchoHeaderServicePortType")
                      
public class EchoHeaderServicePortTypeImpl implements EchoHeaderServicePortType {

    private static final Logger LOG = Logger.getLogger(EchoHeaderServicePortTypeImpl.class.getName());

    /* (non-Javadoc)
     * @see bq.cxf.tutorial.soapheader.server.gen.EchoHeaderServicePortType#echoHeader(bq.cxf.tutorial.soapheader.server.gen.Message  message ,)bq.cxf.tutorial.soapheader.server.gen.MessageHeader  header ,)bq.cxf.tutorial.soapheader.server.gen.Message  returnMessage ,)bq.cxf.tutorial.soapheader.server.gen.MessageHeader  returnHeader )*
     */
    public void echoHeader(Message message,MessageHeader header,javax.xml.ws.Holder<Message> returnMessage,javax.xml.ws.Holder<MessageHeader> returnHeader) { 
        LOG.info("Executing operation echoHeader");
        System.out.println(message);
        System.out.println(header);
        try {
        	
            bq.cxf.tutorial.soapheader.server.gen.Message returnMessageValue = message;
            returnMessage.value = returnMessageValue;
            
            bq.cxf.tutorial.soapheader.server.gen.MessageHeader returnHeaderValue = header;
            returnHeaderValue.isEncypt="false";
            returnHeaderValue.sender="jonathan.q.bo@gmail.com";
            returnHeader.value = returnHeaderValue;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

}
