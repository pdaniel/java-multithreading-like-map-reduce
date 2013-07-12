package com.toptoolz.mapreduce.worker;

import com.toptoolz.mapreduce.map.Mapper;

import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author: danielpo
 * Date: 7/10/13
 * Time: 11:31 AM
 */
public abstract class AbstractWorker extends Thread implements Worker {
    long workerId;
    boolean isRunning;
    boolean taken;
    Mapper mapper;
    String input;
    List results;

    protected AbstractWorker(Mapper mapper, long workerId) {
        this.mapper = mapper;
        this.workerId = workerId;
    }

    protected AbstractWorker(Mapper mapper) {
        Random random = new Random();
        this.mapper = mapper;
        this.workerId = random.nextInt(40)+1;
    }

/*    protected AbstractWorker() {
        this.workerId = new Date().getTime();
    }

    protected AbstractWorker(long workerId) {
        this.workerId = workerId;
    }*/

    public Mapper getMapper() {
        return mapper;
    }

    public void setMapper(Mapper mapper) {
        this.mapper = mapper;
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

    public void markStart(){
        setRunning(true);
    }

    public void markFinish(){
        setRunning(false);
    }

    public boolean isTaken() {
        return taken;
    }

    public void setTaken(boolean taken) {
        this.taken = taken;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public List<String> getResults() {
        return results;
    }

    public void setResults(List<String> results) {
        this.results = results;
    }
}
