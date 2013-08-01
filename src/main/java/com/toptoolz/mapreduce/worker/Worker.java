package com.toptoolz.mapreduce.worker;

import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * @author: danielpo
 * Date: 7/10/13
 * Time: 3:56 PM
 */
public interface Worker {
    public boolean isRunning();

    public long getWorkerId();

    public void begin();

    public void setRunning(boolean running);

    public void setInput(Object input);

    public void setTaken(boolean taken);

    public boolean isTaken();

    public void setResults(List<String> results);

}
