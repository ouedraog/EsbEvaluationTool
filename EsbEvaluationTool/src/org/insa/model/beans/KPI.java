package org.insa.model.beans;

import org.insa.model.beans.Distribution;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

/**
 * Class Result
 * @author Yahya Albaba, Deepali Jain, Marc Orfila, Juste Abel Ouedraogo, Vansh Pahwa
 */
public class KPI {

    /**
     * The distribution of the response time
     */
     @Element
     private Distribution responseTimeDist;
     
     /**
      * The distribution of the request time
      */
     @Element
     private Distribution requestTimeDist;
     
     /**
      * The RTT
      */
     @Element
     private Distribution rttDist;
     
     @Attribute
     private int numberOfLoss;
     
     @Attribute
     private int numberOfNonLoss;

    public KPI() {
        requestTimeDist = new Distribution();
        responseTimeDist = new Distribution();
        rttDist = new Distribution();
    }


    public Distribution getResponseTimeDist() {
        return responseTimeDist;
    }

    public void setResponseTimeDist(Distribution responseTimeDist) {
        this.responseTimeDist = responseTimeDist;
    }

    public Distribution getRequestTimeDist() {
        return requestTimeDist;
    }

    public void setRequestTimeDist(Distribution requestTimeDist) {
        this.requestTimeDist = requestTimeDist;
    }

    public Distribution getRttDist() {
        return rttDist;
    }

    public void setRttDist(Distribution rttDist) {
        this.rttDist = rttDist;
    }

    public int getNumberOfLoss() {
        return numberOfLoss;
    }

    public void setNumberOfLoss(int numberOfLoss) {
        this.numberOfLoss = numberOfLoss;
    }

    public void setNumberOfNonLoss(int numberOfNonLoss) {
        this.numberOfNonLoss = numberOfNonLoss;
    }

    public int getNumberOfNonLoss() {
        return numberOfNonLoss;
    }

 
    
}
