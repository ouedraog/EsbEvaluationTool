/*
 *Used to send emulation start command and wait until the emulation finish
 */
package org.insa.emulation;

import java.net.MalformedURLException;
import java.util.concurrent.Callable;
import org.insa.model.beans.Consumer;
import org.insa.tasks.InterruptedException_Exception;

/**
 *
 * @author JusteAbel
 */
public class ScenarioExecutor implements Callable<Void> {

    private final Consumer consumer;

    public ScenarioExecutor(Consumer c) {
        this.consumer = c;
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

    @Override
    public Void call() throws Exception {
        sendStartScenario(consumer);
        return null;
    }

}
