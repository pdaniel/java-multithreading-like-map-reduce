package com.toptoolz.mapreduce.worker;

import com.toptoolz.mapreduce.MapPhase;
import com.toptoolz.mapreduce.map.Mapper;
import com.toptoolz.mapreduce.reduce.MultithreadReducer;
import com.toptoolz.mapreduce.reduce.Reducer;

import java.util.Collection;

/**
 * @author: danielpo
 * Date: 7/10/13
 * Time: 11:32 AM
 */
public class ReduceMultithreadWorker extends AbstractReduceWorker {

    protected ReduceMultithreadWorker(Reducer reducer, long workerId) {
        super(reducer, workerId);
    }

    protected ReduceMultithreadWorker(Reducer reducer) {
        super(reducer);
    }

    @Override
    public void run() {

    }

    @Override
    public void begin() {
    }
}
