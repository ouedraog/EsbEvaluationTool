
package org.insa.tasks;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "ConsumerWSService", targetNamespace = "http://service/", wsdlLocation = "http://localhost:8080/ConsumerApp1/ConsumerWSService?WSDL")
public class ConsumerWSService
    extends Service
{

    private final static URL CONSUMERWSSERVICE_WSDL_LOCATION;
    private final static WebServiceException CONSUMERWSSERVICE_EXCEPTION;
    private final static QName CONSUMERWSSERVICE_QNAME = new QName("http://service/", "ConsumerWSService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/ConsumerApp1/ConsumerWSService?WSDL");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        CONSUMERWSSERVICE_WSDL_LOCATION = url;
        CONSUMERWSSERVICE_EXCEPTION = e;
    }

    public ConsumerWSService() {
        super(__getWsdlLocation(), CONSUMERWSSERVICE_QNAME);
    }

    public ConsumerWSService(WebServiceFeature... features) {
        super(__getWsdlLocation(), CONSUMERWSSERVICE_QNAME, features);
    }

    public ConsumerWSService(URL wsdlLocation) {
        super(wsdlLocation, CONSUMERWSSERVICE_QNAME);
    }

    public ConsumerWSService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, CONSUMERWSSERVICE_QNAME, features);
    }

    public ConsumerWSService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ConsumerWSService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns ConsumerWS
     */
    @WebEndpoint(name = "ConsumerWSPort")
    public ConsumerWS getConsumerWSPort() {
        return super.getPort(new QName("http://service/", "ConsumerWSPort"), ConsumerWS.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ConsumerWS
     */
    @WebEndpoint(name = "ConsumerWSPort")
    public ConsumerWS getConsumerWSPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://service/", "ConsumerWSPort"), ConsumerWS.class, features);
    }

    private static URL __getWsdlLocation() {
        if (CONSUMERWSSERVICE_EXCEPTION!= null) {
            throw CONSUMERWSSERVICE_EXCEPTION;
        }
        return CONSUMERWSSERVICE_WSDL_LOCATION;
    }

}
