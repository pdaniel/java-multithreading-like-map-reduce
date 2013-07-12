package com.toptoolz.mapreduce.worker;

import com.toptoolz.mapreduce.map.Mapper;
import com.toptoolz.mapreduce.task.Task;

import java.util.List;

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
    public void setMapper(Mapper mapper);
    public void setInput(String input);
    public void setTaken(boolean taken);
    public boolean isTaken();
    public void setResults(List<String> results);
}
