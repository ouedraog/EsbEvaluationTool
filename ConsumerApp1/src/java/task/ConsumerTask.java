package task;

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

    private int duration;//time in milliseconds
    private int period;//time per request
    private String producerWsdl;
    private int processingTime;
    private int requestSize; //requestsize in bytes
    private int responseSize;

    public ConsumerTask() {

    }

    private String composeMessage(int size) {
        String x = "";
        for (int i = 1; i < (size); i++) {
            x = x + 'a';
        }
        return x;
    }

    public Message getMessage() {
        Message msg = new Message();
        msg.setMsg(composeMessage(requestSize));

        return msg;
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
