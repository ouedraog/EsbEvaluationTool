/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;
import task.TaskResult;
import task.ConsumerTask;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author vidhipahwa
 */
public class maintry {
    public static void main(String[] args) 
    {
     
            ConsumerTask ct = getTask(30, 100, 20, 2000, 100);
            ConsumerTask ct2 = getTask(30, 1000, 200, 2000, 100);
            
            ArrayList<ConsumerTask> ctarray =new ArrayList();
            ctarray.add(ct);
            ctarray.add(ct2);
            
            ConsumerWS ws = new ConsumerWS();
            ws.setScenario(ctarray);
        try {
            ws.startScenario();
        } catch (InterruptedException ex) {
            Logger.getLogger(maintry.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            ArrayList<TaskResult> r = ws.retrieveResults();
            
            for(TaskResult result : r){
                System.out.println(result.getTimeToSend()+"\n "+result.getTimeToReceive());
            }

       
       
    }
    public static ConsumerTask getTask(int requestSize, int period, int processingTime, int duration, int responseSize){
                    ConsumerTask ct = new ConsumerTask();
            ct.setRequestSize(requestSize);
            ct.setPeriod(period);
            ct.setProcessingTime(processingTime);
            ct.setDuration(duration);
            ct.setProducerWsdl("http://localhost:8080/ProviderApp/ServiceService?WSDL");
            ct.setResponseSize(100);
            return ct;
    }
    
}
