/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.insa.model.beans;

import org.insa.model.Result;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

/**
 *
 * @author JusteAbel
 */
public class Task {

    /**
     * The producer
     */
    @Element
    private Producer producer;
    /**
     * The consumer
     */
    @Element
    private Consumer consumer;
    /**
     * The consumer request size
     */
    @Attribute
    private int requestSize; 
    /**
     * Producer response size
     */
    @Attribute
    private int responseSize;
    /**
     * The number of requests per seconds
     */
    @Attribute
    private int requestFrequency;

    /**
     * The provider processing time
     */
    @Attribute
    private int processingTime;
    
    /**
     * The duration of the task
     */
    @Attribute
    private int duration;

    /**
     * The KPI for this link
     */
    private Result result;
    
    public Task() {
    }

    public Task(Producer producer, Consumer consumer, int requestSize, int responseSize, int requestFrequency,
            int processingTime) {
        this.producer = producer;
        this.consumer = consumer;
        this.requestSize = requestSize;
        this.responseSize = responseSize;
        this.requestFrequency = requestFrequency;
        this.processingTime = processingTime;
    }

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public Consumer getConsumer() {
        return consumer;
    }

    public void setConsumer(Consumer consumer) {
        this.consumer = consumer;
    }

    public int getProcessingTime() {
        return processingTime;
    }

   

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }



    public int getRequestFrequency() {
        return requestFrequency;
    }

    public int getRequestSize() {
        return requestSize;
    }

    public int getResponseSize() {
        return responseSize;
    }

    public void setRequestFrequency(int requestFrequency) {
        this.requestFrequency = requestFrequency;
    }

    public void setRequestSize(int requestSize) {
        this.requestSize = requestSize;
    }

    public void setResponseSize(int responseSize) {
        this.responseSize = responseSize;
    }
    
    
    @Override
    public String toString() {
        return "Link( producer = " + producer + ", consumer = " + consumer
                + ", reqPerSec = " + requestFrequency + ", request size = " + requestSize
                + ", processingTime = " + processingTime + " )";
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setProcessingTime(int processingTime) {
        this.processingTime = processingTime;
    }
    

}
