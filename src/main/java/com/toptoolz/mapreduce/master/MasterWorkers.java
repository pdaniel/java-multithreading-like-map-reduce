package com.toptoolz.mapreduce.master;

import com.toptoolz.mapreduce.ReducePhase;
import com.toptoolz.mapreduce.map.Mapper;
import com.toptoolz.mapreduce.master.exception.MasterException;
import com.toptoolz.mapreduce.reduce.Reducer;
import com.toptoolz.mapreduce.worker.AbstractMapWorker;
import com.toptoolz.mapreduce.worker.MapWorker;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

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

    BlockingQueue<Long> workersIds = new LinkedBlockingQueue<>();

    @Override
    public void begin() {
        //createWorkers(workersNo);
        int i = 0;
        for (Object s : input) {
            MapWorker worker = getAvailableMapWorker(workersIds);
            worker.setInput(s);
            worker.setMapper(mapper);
            worker.setResults(values);
            worker.setWorkerIds(workersIds);
            worker.begin();
            i++;
            if(i==workersNo){
                System.out.println("sleeping");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        }
        for (AbstractMapWorker worker : workers) {
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
