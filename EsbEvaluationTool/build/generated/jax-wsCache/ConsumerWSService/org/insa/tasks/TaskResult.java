
package org.insa.tasks;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for taskResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="taskResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="timeToReceive" type="{http://www.w3.org/2001/XMLSchema}long" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="timeToSend" type="{http://www.w3.org/2001/XMLSchema}long" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "taskResult", propOrder = {
    "timeToReceive",
    "timeToSend"
})
public class TaskResult {

    @XmlElement(nillable = true)
    protected List<Long> timeToReceive;
    @XmlElement(nillable = true)
    protected List<Long> timeToSend;

    /**
     * Gets the value of the timeToReceive property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the timeToReceive property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTimeToReceive().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Long }
     * 
     * 
     */
    public List<Long> getTimeToReceive() {
        if (timeToReceive == null) {
            timeToReceive = new ArrayList<Long>();
        }
        return this.timeToReceive;
    }

    /**
     * Gets the value of the timeToSend property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the timeToSend property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTimeToSend().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Long }
     * 
     * 
     */
    public List<Long> getTimeToSend() {
        if (timeToSend == null) {
            timeToSend = new ArrayList<Long>();
        }
        return this.timeToSend;
    }

}
