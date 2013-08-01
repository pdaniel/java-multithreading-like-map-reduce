package com.toptoolz.mapreduce.master;

import com.toptoolz.mapreduce.ReducePhase;
import com.toptoolz.mapreduce.map.Mapper;
import com.toptoolz.mapreduce.reduce.Reducer;
import com.toptoolz.mapreduce.worker.MapThreadWorker;
import com.toptoolz.mapreduce.worker.MapWorker;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

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
        int t = 0;
        if (input.size() > workersNo) {
            t = workersNo;
        } else {
            t = input.size();
        }
        this.threads = new ArrayBlockingQueue<>(t);
        int i = 0;
        for (Object s : input) {
            MapWorker worker = new MapThreadWorker(mapper, s, values, threads);
            worker.begin();
            synchronized (this.threads) {
                while (threads.size() > workersNo) {
                    System.out.println("blocking thread creation");
                }
            }


            /*i++;
            if(i==workersNo){
                System.out.println("sleeping");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
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
        System.out.println("End Workers");*/
        }

        synchronized (this.threads) {
            while (!this.threads.isEmpty()) {
                try {
                    this.threads.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        ReducePhase rp = new ReducePhase(values, reducer);
        reduceResults = rp.reduce();
        System.out.println("End Workers");
    }

    private int calculateRealWorkersNo() {
        return 0;
    }

    public Object reduceResults() {
        return reduceResults;
    }

}
