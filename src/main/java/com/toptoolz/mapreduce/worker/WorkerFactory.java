package com.toptoolz.mapreduce.worker;

import com.toptoolz.mapreduce.map.Mapper;

/**
 * @author: danielpo
 * Date: 7/10/13
 * Time: 12:23 PM
 */
public class WorkerFactory {
    public static AbstractMapWorker getWorker(Mapper mapper) {
        return new MapThreadWorker(mapper);
    }
}
