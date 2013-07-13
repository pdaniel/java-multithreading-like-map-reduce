package com.toptoolz.mapreduce.worker;

import com.toptoolz.mapreduce.map.Mapper;
import com.toptoolz.mapreduce.reduce.Reducer;

import java.util.Random;

/**
 * User: Daniel P.
 * Date: 7/13/13
 * Time: 8:09 PM
 *
 */
public abstract class AbstractReduceWorker extends BaseWorker implements ReduceWorker{

    Reducer reducer;

    protected AbstractReduceWorker(Reducer reducer, long workerId) {
        this.reducer = reducer;
        this.workerId = workerId;
    }

    protected AbstractReduceWorker(Reducer reducer) {
        this.reducer = reducer;
        this.workerId = genWorkerId();
    }

    public Reducer getReducer() {
        return reducer;
    }

    public void setReducer(Reducer reducer) {
        this.reducer = reducer;
    }
}
