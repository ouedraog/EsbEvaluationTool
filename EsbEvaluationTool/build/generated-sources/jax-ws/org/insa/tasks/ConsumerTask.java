
package org.insa.tasks;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for consumerTask complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="consumerTask">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="duration" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="period" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="processingTime" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="producerWsdl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="requestSize" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="responseSize" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consumerTask", propOrder = {
    "duration",
    "period",
    "processingTime",
    "producerWsdl",
    "requestSize",
    "responseSize"
})
public class ConsumerTask {

    protected int duration;
    protected int period;
    protected int processingTime;
    protected String producerWsdl;
    protected int requestSize;
    protected int responseSize;

    /**
     * Gets the value of the duration property.
     * 
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Sets the value of the duration property.
     * 
     */
    public void setDuration(int value) {
        this.duration = value;
    }

    /**
     * Gets the value of the period property.
     * 
     */
    public int getPeriod() {
        return period;
    }

    /**
     * Sets the value of the period property.
     * 
     */
    public void setPeriod(int value) {
        this.period = value;
    }

    /**
     * Gets the value of the processingTime property.
     * 
     */
    public int getProcessingTime() {
        return processingTime;
    }

    /**
     * Sets the value of the processingTime property.
     * 
     */
    public void setProcessingTime(int value) {
        this.processingTime = value;
    }

    /**
     * Gets the value of the producerWsdl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProducerWsdl() {
        return producerWsdl;
    }

    /**
     * Sets the value of the producerWsdl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProducerWsdl(String value) {
        this.producerWsdl = value;
    }

    /**
     * Gets the value of the requestSize property.
     * 
     */
    public int getRequestSize() {
        return requestSize;
    }

    /**
     * Sets the value of the requestSize property.
     * 
     */
    public void setRequestSize(int value) {
        this.requestSize = value;
    }

    /**
     * Gets the value of the responseSize property.
     * 
     */
    public int getResponseSize() {
        return responseSize;
    }

    /**
     * Sets the value of the responseSize property.
     * 
     */
    public void setResponseSize(int value) {
        this.responseSize = value;
    }

}
