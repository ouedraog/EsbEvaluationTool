/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import service.Service;
import service.ServiceService;

/**
 * Sends message at a given frequency for a given time
 *
 * @author JusteAbel
 */
public class TaskExecutor extends Thread {

    private final ScheduledExecutorService scheduler
            = Executors.newScheduledThreadPool(1);

    private final ConsumerTask task;

    private final TaskResult result;

    public TaskExecutor(ConsumerTask task) {
        this.task = task;
        result = new TaskResult();
    }

    @Override
    public void run() {
        try {
            executeTask();
        } catch (InterruptedException ex) {
            Logger.getLogger(TaskExecutor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void executeTask() throws InterruptedException {
        final Runnable MessageSender = new Runnable() {

            @Override
            public void run() {
                try {
                    sendMessage();
                } catch (MalformedURLException ex) {
                    Logger.getLogger(TaskExecutor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            private void sendMessage() throws MalformedURLException {
                //System.out.println("Message sent "+scheduler);

                Message toSend = task.getMessage();
                toSend.setSendingTime(System.currentTimeMillis());
                //System.err.println("Task wsdl"+task.getProducerWsdl());
                URL url = new URL(task.getProducerWsdl());
                QName qname = new QName("http://service/", "ServiceService");

                String receivedMsg = requestService(url, qname, toSend.getMsg());
                toSend.setReceptionTime(System.currentTimeMillis());

                long[] ans = timestamps(receivedMsg);
                long time2send = ans[0] - toSend.getSendingTime();
                long time2receive = toSend.getReceptionTime() - ans[1];

                //System.out.println(" t1= "+ toSend.getTimestamp4()+", ans1 = " +ans[1]+", t2 = "+time2receive);
                result.getTimeToSend().add(time2send);
                result.getTimeToReceive().add(time2receive);
            }

            private String requestService(URL url, QName qname, String msg) {
                ServiceService service = new ServiceService(url, qname);

                Service port = service.getServicePort();
                String res = port.operation(msg, task.getResponseSize(), task.getProcessingTime());
                return res;
            }

            private long[] timestamps(String tempo) {
                long ans[] = new long[2];
                String data = tempo.replace("a", "").replace("(", "").replace(")", "");

                int index = data.indexOf(",");
                String firstTimestamp = data.substring(0, index);
                String lastTimestamp = data.replace(firstTimestamp, "").replace(",", "");
                ans[0] = Long.valueOf(firstTimestamp);
                ans[1] = Long.valueOf(lastTimestamp);
                return ans;
            }

        };

        //System.out.println(task.getPeriod());
        final ScheduledFuture<?> senderHandler
                = scheduler.scheduleAtFixedRate(MessageSender, task.getPeriod(), task.getPeriod(), TimeUnit.MILLISECONDS);

        scheduler.schedule(new Runnable() {

            @Override
            public void run() {
                senderHandler.cancel(true);
                scheduler.shutdown();
            }
        }, task.getDuration(), TimeUnit.MILLISECONDS);

        //wait for the scheduler to finish
        scheduler.awaitTermination(1, TimeUnit.DAYS);

    }

    public TaskResult getResult() {
        return result;
    }

}
