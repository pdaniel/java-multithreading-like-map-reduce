package com.toptoolz.mapreduce.master;

import com.toptoolz.mapreduce.ReducePhase;
import com.toptoolz.mapreduce.map.Mapper;
import com.toptoolz.mapreduce.master.exception.MasterException;
import com.toptoolz.mapreduce.reduce.Reducer;
import com.toptoolz.mapreduce.worker.MapThreadWorker;
import com.toptoolz.mapreduce.worker.MapWorker;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author danielpo
 * Date: 7/10/13
 * Time: 11:24 AM
 */
public class MasterWorkers extends AbstractMaster {

    private List<String> values = Collections.synchronizedList(new LinkedList<String>());

    private Object reduceResults;


    public MasterWorkers(List input, Mapper mapper, Reducer reducer) {
        super(input, mapper, reducer);
    }

    public MasterWorkers(Reducer reducer, Mapper mapper, List input, int workersNo) {
        super(reducer, mapper, input, workersNo);
    }


    @Override
    public void begin() {
        if(workersNo==0) workersNo = input.size();
        ExecutorService threadPool = Executors.newFixedThreadPool(workersNo) ;
        CompletionService pool = new ExecutorCompletionService(threadPool);
        for (Object s : input) {
            MapWorker worker = new MapThreadWorker(mapper, s, values, threads);
            pool.submit(worker);
        }

        for(Object s: input){
            try {
                pool.take().get();
            } catch (InterruptedException | ExecutionException e) {
                throw new MasterException(e);
            }
        }

        threadPool.shutdown();

        ReducePhase rp = new ReducePhase(values, reducer);
        reduceResults = rp.reduce();
    }

    public Object reduceResults() {
        return reduceResults;
    }

}
