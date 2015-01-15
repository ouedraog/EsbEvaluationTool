package scenario.model;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author vidhipahwa
 */
public class ConsumerTask {
    /**
     * total duration of the task in ms
     */
    private int duration;
    /**
     * The period in ms
     */
    private int period;
    /**
     * The wsdl location of the provider
     */
    private String producerWsdl;
    /**
     * The provider processing time in ms
     */
    private int processingTime;
    /**
     * The request size in byte
     */
    private int requestSize; 
    /**
     * The response size in byte
     */
    private int responseSize;

    public ConsumerTask() {

    }
    /**
     * Produce the message to send
     * @return 
     */
    public String produceMessage() {
        String x = "";
        for (int i = 1; i < (requestSize); i++) {
            x = x + 'a';
        }
        return x;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public String getProducerWsdl() {
        return producerWsdl;
    }

    public void setProducerWsdl(String producerWsdl) {
        this.producerWsdl = producerWsdl;
    }

    public int getProcessingTime() {
        return processingTime;
    }

    public void setProcessingTime(int processingTime) {
        this.processingTime = processingTime;
    }

    public int getRequestSize() {
        return requestSize;
    }

    public void setRequestSize(int requestSize) {
        this.requestSize = requestSize;
    }

    public int getResponseSize() {
        return responseSize;
    }

    public void setResponseSize(int responseSize) {
        this.responseSize = responseSize;
    }

}
