package com.toptoolz.mapreduce.worker;

import com.toptoolz.mapreduce.reduce.Reducer;

/**
 * User: Daniel P.
 * Date: 7/13/13
 * Time: 8:14 PM
 */
public interface ReduceWorker {
    public void setReducer(Reducer reducer);
}
