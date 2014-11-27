package org.insa.model;

import java.util.ArrayList;
import org.apache.commons.math3.stat.StatUtils;
import org.insa.model.beans.Distribution;
import org.insa.model.beans.KPI;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

/**
 * Class Result
 * @author Yahya Albaba, Deepali Jain, Marc Orfila, Juste Abel Ouedraogo, Vansh Pahwa
 */
public class Result {
    /**
     * The KPI for the defined links
     */
    @ElementList
    private ArrayList<KPI> taskResults;

    /**
     * The distribution of the response time
     */
     @Element
     private Distribution responseTimeDist;

     /**
      * The distribution of the bus reliability
      */
     @Element
    private Distribution busReliabilityDist;

    public Result() {
        taskResults = new ArrayList<KPI>();
        responseTimeDist = new Distribution();
        busReliabilityDist = new Distribution();
    }

    public Distribution getBusReliabilityDist() {
        return busReliabilityDist;
    }

    public ArrayList<KPI> getLinkResults() {
        return taskResults;
    }

    public Distribution getResponseTimeDist() {
        return responseTimeDist;
    }

    public void setBusReliabilityDist(Distribution busReliabilityDist) {
        this.busReliabilityDist = busReliabilityDist;
    }

    public void setLinkResults(ArrayList<KPI> linkResults) {
        this.taskResults = linkResults;
    }

    public void setResponseTimeDist(Distribution responseTimeDist) {
        this.responseTimeDist = responseTimeDist;
    }

   public void addLinkResult(KPI kpi){
       this.taskResults.add(kpi);
   }
   /**
    * Compute the distributions
    */
   public void computeDistributions(){
       busReliabilityDist.computeDist(getBusReliabilities());
       responseTimeDist.computeDist(getRespTimes());
   }
   /**
    * Get the response times for stats computation
    * @return
    */
    private double[] getRespTimes(){
        double [] result = new double[taskResults.size()];
        int i=0;
        for(KPI k : taskResults){
            result[i++] = k.getResponseTime();
        }
        return result;
    }
    /**
     * Get the bus reliabilities for stats computation
     * @return
     */
    private double[] getBusReliabilities(){
        double [] result = new double[taskResults.size()];
        int i=0;
        for(KPI k : taskResults){
            result[i++] = k.getBusReliability();
        }
        return result;
    }
}
