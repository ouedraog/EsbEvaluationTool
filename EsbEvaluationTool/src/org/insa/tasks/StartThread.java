/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.insa.tasks;

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
        result = sendStartScenario(consumer);
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public boolean isResult() {
        return result;
    }


    private boolean sendStartScenario(Consumer c) {
        try {
            //TODO : call the startScenario service from the consumer service
            Thread.sleep(30000);
        } catch (InterruptedException ex) {
            Logger.getLogger(StartThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
}
