/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import scenario.model.TaskResult;
import scenario.model.ConsumerTask;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vidhipahwa
 */
public class Test {

    public static void main(String[] args) {

        ArrayList<ConsumerTask> ctarray = new ArrayList();
        for (int i = 1; i < 5; i++) {
            ctarray.add(getTask(10, 100, 1000*i, 1000, 100));
        }

        ConsumerWS ws = new ConsumerWS();
        ws.setScenario(ctarray);
        ws.startScenario();
        ArrayList<TaskResult> r = ws.retrieveResults();
        for (TaskResult result : r) {
            System.out.println(result);
        }

    }

    public static ConsumerTask getTask(int requestSize, int period, int processingTime, int duration, int responseSize) {
        ConsumerTask ct = new ConsumerTask();
        ct.setRequestSize(requestSize);
        ct.setPeriod(period);
        ct.setProcessingTime(processingTime);
        ct.setDuration(duration);
        //ct.setProducerWsdl("http://192.168.137.37:9080/BridgeProtocoleService1/esbProvider?WSDL");
        ct.setProducerWsdl("http://localhost:8080/ProviderApp/ServiceService?WSDL");
        ct.setResponseSize(responseSize);
        return ct;
    }

}
