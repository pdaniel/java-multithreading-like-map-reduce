package com.toptoolz.mapreduce.worker;

import com.toptoolz.mapreduce.map.Mapper;
import com.toptoolz.mapreduce.task.Task;

/**
 * @author: danielpo
 * Date: 7/10/13
 * Time: 12:23 PM
 */
public class WorkerFactory {
    public static AbstractWorker getWorker(Mapper mapper){
        return new ThreadWorker(mapper);
    }
}
