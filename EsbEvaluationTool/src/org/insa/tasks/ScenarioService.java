/*
 * Service for executing the scenario deployment and results retrieving tasks
 */
package org.insa.tasks;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.insa.model.Model;
import org.insa.model.beans.Consumer;
import org.insa.model.beans.Distribution;
import org.insa.model.beans.KPI;

/**
 *
 * @author JusteAbel
 */
public class ScenarioService extends Service<Void> {

    /**
     * The model
     */
    private Model model;
    private int finishedTasksNumber = 0;
    
    public ScenarioService(Model model) {
        this.model = model;
    }
    
    @Override
    protected Task createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {

                //send the scenario
                for (Consumer c : model.getScenario().getConsumers()) {
                    sendScenario(c);
                }

                //send start command
                double start = System.currentTimeMillis();
                for (Consumer c : model.getScenario().getConsumers()) {
                    StartThread s = new StartThread(c);
                    s.start();
                    s.join();
                    
                }

                // retrieve the result
                for (Consumer c : model.getScenario().getConsumers()) {
                    sendRetrieveResult(c);
                }
                return null;
            }
        };
    }

    /**
     * Send scenario to a consumer
     */
    private void sendScenario(Consumer c) throws MalformedURLException {
        //get the scenario to send
        ArrayList<ConsumerTask> tasks = model.getConsumerTasks(c);
        //TODO : call setScenario service from the consumer service
        setScenario(tasks, c);
    }
    
    private void sendRetrieveResult(Consumer c) throws MalformedURLException {
        //TODO invoque the retrieve result service and store the result in the model
        ArrayList<ConsumerResult> results = (ArrayList<ConsumerResult>) retrieveResults(c);
        
        if (results.size() != model.getScenario().getTasks().size()) {
            System.err.println("We have a big problem");
        }
        
        for (int i = 0; i < results.size(); i++) {
            //request time
            ConsumerResult r = results.get(i);
            
            ArrayList<Double> requestTimes = new ArrayList<>();
            ArrayList<Double> responseTimes = new ArrayList<>();
            ArrayList<Double> rttTimes = new ArrayList<>();
            
            r.getTime2Send().stream().filter((time) -> (time > 0)).forEach((time) -> {
                requestTimes.add((double) time);
            });
            
            r.getTime2Rec().stream().filter((time) -> (time > 0)).forEach((time) -> {
                responseTimes.add((double) time);
            });
            
            for (int j = 0; j < r.getTime2Send().size(); j++) {
                if (r.getTime2Send().get(i) > 0) {
                    rttTimes.add((double) (r.getTime2Rec().get(j) + r.getTime2Send().get(j)));
                }
            }
            
            DescriptiveStatistics dRequestTime = new DescriptiveStatistics(toArray(requestTimes));
            DescriptiveStatistics dRespTime = new DescriptiveStatistics(toArray(responseTimes));
            DescriptiveStatistics dRttTime = new DescriptiveStatistics(toArray(rttTimes));
            
            KPI kpi = new KPI();
            kpi.setRequestTimeDist(new Distribution(dRequestTime));
            kpi.setResponseTimeDist(new Distribution(dRespTime));
            kpi.setRttDist(new Distribution(dRttTime));
            kpi.setNumberOfLoss(r.getTime2Send().size() - requestTimes.size());
            kpi.setNumberOfNonLoss(requestTimes.size());
            model.getScenario().getTasks().get(i).setResult(kpi);
            
        }
    }
    
    private static boolean setScenario(java.util.List<ConsumerTask> arg0, Consumer c) throws MalformedURLException {
        org.insa.tasks.ConsumerWSService service = new org.insa.tasks.ConsumerWSService(c.getURL(), Consumer.getQname());
        org.insa.tasks.ConsumerWS port = service.getConsumerWSPort();
        return port.setScenario(arg0);
    }
    
    private static java.util.List<ConsumerResult> retrieveResults(Consumer c) throws MalformedURLException {
        org.insa.tasks.ConsumerWSService service = new org.insa.tasks.ConsumerWSService(c.getURL(), Consumer.getQname());
        org.insa.tasks.ConsumerWS port = service.getConsumerWSPort();
        return port.retrieveResults();
    }
    
    private double[] toArray(ArrayList<Double> list) {
        double[] res = new double[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }
    
}
