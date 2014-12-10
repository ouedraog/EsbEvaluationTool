/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.insa.tasks;

import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import org.insa.model.beans.Consumer;

/**
 *
 * @author JusteAbel
 */
public class StartThread extends Thread {

    private Consumer consumer;
    private boolean result;

    public StartThread(Consumer c) {
        this.consumer = c;
    }

    @Override
    public void run() {
        try {
            result = sendStartScenario(consumer);
            System.out.println(result+"; "+Thread.currentThread());
        } catch (MalformedURLException ex) {
            Logger.getLogger(StartThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException_Exception ex) {
            Logger.getLogger(StartThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public boolean isResult() {
        return result;
    }


    private boolean sendStartScenario(Consumer c) throws MalformedURLException, InterruptedException_Exception {
        startScenario(c);
        return true;
    }
        private static boolean startScenario(Consumer c) throws MalformedURLException, InterruptedException_Exception {
        org.insa.tasks.ConsumerWSService service = new org.insa.tasks.ConsumerWSService(c.getURL(), Consumer.getQname());
        org.insa.tasks.ConsumerWS port = service.getConsumerWSPort();
        return port.startScenario();
    }
}
