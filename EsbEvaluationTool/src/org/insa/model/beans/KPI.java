package org.insa.model.beans;

import org.simpleframework.xml.Attribute;

/**
 * Class KPI
 * @author Yahya Albaba, Deepali Jain, Marc Orfila, Juste Abel Ouedraogo, Vansh Pahwa
 */
public class KPI {
    /**
     * Bus reliability i.e number of packets loss
     */
    @Attribute
    private int busReliability;

    /**
     * Response time
     */
    @Attribute
    private double responseTime;

    public KPI() {
    }

    public KPI(int busReliability, double responseTime) {
        this.busReliability = busReliability;
        this.responseTime = responseTime;
    }

    public int getBusReliability() {
        return busReliability;
    }

    public double getResponseTime() {
        return responseTime;
    }

    public void setBusReliability(int busReliability) {
        this.busReliability = busReliability;
    }

    public void setResponseTime(double responseTime) {
        this.responseTime = responseTime;
    }

    
}
