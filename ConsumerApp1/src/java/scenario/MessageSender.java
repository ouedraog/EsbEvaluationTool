/*
 * Thread used to send message to a service provider
 */
package scenario;

import java.net.MalformedURLException;
import java.net.URL;
import scenario.model.MessageResult;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import service.Service;
import service.ServiceService;

/**
 *
 * @author JusteAbel
 */
public class MessageSender implements Callable<MessageResult> {

    /**
     * The message to send and the results (timestamps)
     */
    private final String message;
    /**
     * The provider wsdl
     */
    private final String producerWsdl;
    /**
     * The producer processing time
     */
    private final int processingTime;
    /**
     * The request size in byte
     */
    private final int requestSize;
    /**
     * The response size in byte
     */
    private final int responseSize;

    public MessageSender(String message, String producerWsdl, int processingTime, int requestSize, int responseSize) {
        this.message = message;
        this.producerWsdl = producerWsdl;
        this.processingTime = processingTime;
        this.requestSize = requestSize;
        this.responseSize = responseSize;
    }

    @Override
    public MessageResult call() {
        MessageResult result = new MessageResult();
        try {
            long sendingTime = System.currentTimeMillis();
            String response = sendMessage();
            long receptionTime = System.currentTimeMillis();
            long[] producerTimes = extractTimestamps(response);
            
            result.setRequestTime(producerTimes[0] - sendingTime);
            result.setResponseTime(receptionTime - producerTimes[1]);
        } catch (Exception ex) {
            //loss
            result.setRequestTime(-1);
            result.setResponseTime(-1);

            Logger.getLogger(MessageSender.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    private String sendMessage() throws Exception {
        URL url = new URL(producerWsdl);
        QName qname = new QName("http://service/", "ServiceService");
        ServiceService service = new ServiceService(url, qname);

        Service port = service.getServicePort();
        String response = port.operation(message, responseSize, processingTime);
        return response;
    }

    /**
     * Extract timestamp from the response Response looks like that : (125542,
     * 154424)aaaaaaaaaa --> (t1,t2)message
     *
     * @param response
     * @return
     */
    private long[] extractTimestamps(String response) {
        long ans[] = new long[2];
        String data = response.replace("a", "").replace("(", "").replace(")", "");

        int index = data.indexOf(",");
        String firstTimestamp = data.substring(0, index);
        String lastTimestamp = data.replace(firstTimestamp, "").replace(",", "");
        ans[0] = Long.valueOf(firstTimestamp);
        ans[1] = Long.valueOf(lastTimestamp);
        return ans;
    }

}
