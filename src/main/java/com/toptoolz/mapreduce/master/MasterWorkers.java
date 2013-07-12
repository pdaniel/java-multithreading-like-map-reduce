package com.toptoolz.mapreduce.master;

import com.toptoolz.mapreduce.ReducePhase;
import com.toptoolz.mapreduce.map.Mapper;
import com.toptoolz.mapreduce.master.exception.MasterException;
import com.toptoolz.mapreduce.reduce.Reducer;
import com.toptoolz.mapreduce.worker.AbstractWorker;
import com.toptoolz.mapreduce.worker.Worker;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author: danielpo
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
        createWorkers(workersNo);
        for (Object s : input) {
            Worker worker = getAvailableworker();
            worker.setInput((String) s);
            worker.setMapper(mapper);
            worker.setResults(values);
            worker.begin();
        }
        for (AbstractWorker worker : workers) {
            try {
                worker.join();
            } catch (InterruptedException e) {
                throw new MasterException(e);
            }
        }
        ReducePhase rp = new ReducePhase(values, reducer);
        reduceResults = rp.reduce();
        System.out.println("End Workers");
    }

    public Object reduceResults() {
        return reduceResults;
    }

}
