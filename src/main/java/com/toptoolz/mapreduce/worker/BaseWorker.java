package com.toptoolz.mapreduce.worker;

import com.toptoolz.mapreduce.map.Mapper;

import java.util.List;
import java.util.Random;

/**
 * User: Daniel P.
 * Date: 7/13/13
 * Time: 8:01 PM
 * Copyright @ Toptoolz Com
 */
abstract class BaseWorker extends Thread implements Worker{
    long workerId;
    boolean isRunning;
    boolean taken;
    Object input;
    List results;

    public void markStart(){
        setRunning(true);
    }

    public void markFinish(){
        setRunning(false);
    }

    Integer genWorkerId(){
        Random random = new Random();
        return random.nextInt(40)+1;
    }


    public long getWorkerId() {
        return workerId;
    }

    public void setWorkerId(long workerId) {
        this.workerId = workerId;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public boolean isTaken() {
        return taken;
    }

    public void setTaken(boolean taken) {
        this.taken = taken;
    }

    public Object getInput() {
        return input;
    }

    public void setInput(Object input) {
        this.input = input;
    }

    public List<String> getResults() {
        return results;
    }

    public void setResults(List<String> results) {
        this.results = results;
    }

}
