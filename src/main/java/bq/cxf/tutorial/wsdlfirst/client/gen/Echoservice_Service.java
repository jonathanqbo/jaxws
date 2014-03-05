package bq.cxf.tutorial.wsdlfirst.client.gen;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.7.8
 * 2014-02-23T10:40:02.235-05:00
 * Generated source version: 2.7.8
 * 
 */
@WebServiceClient(name = "echoservice", 
                  wsdlLocation = "http://localhost:8080/jaxws/gen/echoservice?wsdl",
                  targetNamespace = "http://bq.cxf.tutorial/echoservice/") 
public class Echoservice_Service extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://bq.cxf.tutorial/echoservice/", "echoservice");
    public final static QName EchoserviceSOAP = new QName("http://bq.cxf.tutorial/echoservice/", "echoserviceSOAP");
    static {
        URL url = null;
        try {
            url = new URL("http://localhost:8080/jaxws/gen/echoservice?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(Echoservice_Service.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://localhost:8080/jaxws/gen/echoservice?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public Echoservice_Service(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public Echoservice_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public Echoservice_Service() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public Echoservice_Service(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public Echoservice_Service(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public Echoservice_Service(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     *
     * @return
     *     returns Echoservice
     */
    @WebEndpoint(name = "echoserviceSOAP")
    public Echoservice getEchoserviceSOAP() {
        return super.getPort(EchoserviceSOAP, Echoservice.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns Echoservice
     */
    @WebEndpoint(name = "echoserviceSOAP")
    public Echoservice getEchoserviceSOAP(WebServiceFeature... features) {
        return super.getPort(EchoserviceSOAP, Echoservice.class, features);
    }

}
