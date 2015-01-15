
package org.insa.tasks;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.insa.tasks package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _RetrieveResults_QNAME = new QName("http://service/", "retrieveResults");
    private final static QName _SetScenarioResponse_QNAME = new QName("http://service/", "setScenarioResponse");
    private final static QName _StartScenarioResponse_QNAME = new QName("http://service/", "startScenarioResponse");
    private final static QName _RetrieveResultsResponse_QNAME = new QName("http://service/", "retrieveResultsResponse");
    private final static QName _InterruptedException_QNAME = new QName("http://service/", "InterruptedException");
    private final static QName _StartScenario_QNAME = new QName("http://service/", "startScenario");
    private final static QName _SetScenario_QNAME = new QName("http://service/", "setScenario");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.insa.tasks
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RetrieveResultsResponse }
     * 
     */
    public RetrieveResultsResponse createRetrieveResultsResponse() {
        return new RetrieveResultsResponse();
    }

    /**
     * Create an instance of {@link StartScenarioResponse }
     * 
     */
    public StartScenarioResponse createStartScenarioResponse() {
        return new StartScenarioResponse();
    }

    /**
     * Create an instance of {@link SetScenarioResponse }
     * 
     */
    public SetScenarioResponse createSetScenarioResponse() {
        return new SetScenarioResponse();
    }

    /**
     * Create an instance of {@link RetrieveResults }
     * 
     */
    public RetrieveResults createRetrieveResults() {
        return new RetrieveResults();
    }

    /**
     * Create an instance of {@link SetScenario }
     * 
     */
    public SetScenario createSetScenario() {
        return new SetScenario();
    }

    /**
     * Create an instance of {@link StartScenario }
     * 
     */
    public StartScenario createStartScenario() {
        return new StartScenario();
    }

    /**
     * Create an instance of {@link InterruptedException }
     * 
     */
    public InterruptedException createInterruptedException() {
        return new InterruptedException();
    }

    /**
     * Create an instance of {@link ConsumerTask }
     * 
     */
    public ConsumerTask createConsumerTask() {
        return new ConsumerTask();
    }

    /**
     * Create an instance of {@link TaskResult }
     * 
     */
    public TaskResult createTaskResult() {
        return new TaskResult();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RetrieveResults }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "retrieveResults")
    public JAXBElement<RetrieveResults> createRetrieveResults(RetrieveResults value) {
        return new JAXBElement<RetrieveResults>(_RetrieveResults_QNAME, RetrieveResults.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetScenarioResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "setScenarioResponse")
    public JAXBElement<SetScenarioResponse> createSetScenarioResponse(SetScenarioResponse value) {
        return new JAXBElement<SetScenarioResponse>(_SetScenarioResponse_QNAME, SetScenarioResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StartScenarioResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "startScenarioResponse")
    public JAXBElement<StartScenarioResponse> createStartScenarioResponse(StartScenarioResponse value) {
        return new JAXBElement<StartScenarioResponse>(_StartScenarioResponse_QNAME, StartScenarioResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RetrieveResultsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "retrieveResultsResponse")
    public JAXBElement<RetrieveResultsResponse> createRetrieveResultsResponse(RetrieveResultsResponse value) {
        return new JAXBElement<RetrieveResultsResponse>(_RetrieveResultsResponse_QNAME, RetrieveResultsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InterruptedException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "InterruptedException")
    public JAXBElement<InterruptedException> createInterruptedException(InterruptedException value) {
        return new JAXBElement<InterruptedException>(_InterruptedException_QNAME, InterruptedException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StartScenario }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "startScenario")
    public JAXBElement<StartScenario> createStartScenario(StartScenario value) {
        return new JAXBElement<StartScenario>(_StartScenario_QNAME, StartScenario.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetScenario }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "setScenario")
    public JAXBElement<SetScenario> createSetScenario(SetScenario value) {
        return new JAXBElement<SetScenario>(_SetScenario_QNAME, SetScenario.class, null, value);
    }

}
