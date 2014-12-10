package task;

import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author vidhipahwa
 */
public class TaskResult {

    private ArrayList<Long> timeToSend;
    private ArrayList<Long> timeToReceive;

    public TaskResult() {
        timeToReceive = new ArrayList<>();
        timeToSend = new ArrayList<>();
    }

    public ArrayList<Long> getTimeToReceive() {
        return timeToReceive;
    }

    public void setTimeToReceive(ArrayList<Long> timeToReceive) {
        this.timeToReceive = timeToReceive;
    }

    public ArrayList<Long> getTimeToSend() {
        return timeToSend;
    }

    public void setTimeToSend(ArrayList<Long> timeToSend) {
        this.timeToSend = timeToSend;
    }

}
