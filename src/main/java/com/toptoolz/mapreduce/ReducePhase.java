package com.toptoolz.mapreduce;

import com.toptoolz.mapreduce.reduce.Reducer;

import java.util.Collection;
import java.util.List;

/**
 * @author: danielpo
 * Date: 7/12/13
 * Time: 10:17 AM
 * This method executes the reduce phase
 */
public class ReducePhase {
    List mapResult;
    Reducer reducer;

    public ReducePhase(List mapResult, Reducer reducer) {
        this.mapResult = mapResult;
        this.reducer = reducer;
    }

    public Object reduce(){
        return reducer.reduce(mapResult);
    }
}
