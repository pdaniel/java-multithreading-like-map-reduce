package com.toptoolz.mapreduce.master;

import com.toptoolz.mapreduce.map.Mapper;
import com.toptoolz.mapreduce.reduce.Reducer;
import com.toptoolz.mapreduce.worker.*;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author: danielpo
 * Date: 7/10/13
 * Time: 11:23 AM
 * This class manages the map-reduce process. Creates workers, allocate workers to jobs.
 */
public abstract class AbstractMaster implements Master {

    Vector<AbstractMapWorker> workers = new Vector<>();
    protected ArrayBlockingQueue<Worker> threads;
    Vector<AbstractReduceWorker> reduceWorkers = new Vector<>();
    List input;
    Mapper mapper;
    Reducer reducer;
    int workersNo;

    protected AbstractMaster(List input, Mapper mapper, Reducer reducer) {
        this.input = input;
        this.mapper = mapper;
        this.reducer = reducer;
       // init();
    }

    protected AbstractMaster(Reducer reducer, Mapper mapper, List input, int workersNo) {
        this.reducer = reducer;
        this.mapper = mapper;
        this.input = input;
        this.workersNo = workersNo;
        //init();
    }

    private void init() {
        this.threads = new ArrayBlockingQueue<>(workersNo);
    }

    /**
     * This method register a new worker in the worker list
     *
     * @param abstractMapWorker - the worker
     */
    public void registerWorker(AbstractMapWorker abstractMapWorker) {
        workers.add(abstractMapWorker);
    }

    public void deleteWorker(AbstractMapWorker abstractMapWorker) {
        workers.remove(abstractMapWorker);
    }

    /**
     * This method creates a pool of workers from witch a worker will be retrieved
     *
     * @param taskNumOfWorkers - how many workers will be created
     */
    protected void createWorkers(int taskNumOfWorkers) {
        int workersSize = workers.size();
        if (workersSize < taskNumOfWorkers) {
            int dif = taskNumOfWorkers - workersSize;
            for (int i = 0; i < dif; i++) {
                registerWorker(WorkerFactory.getWorker(mapper));
            }
        }
    }


    public Vector<AbstractMapWorker> getWorkers() {
        return workers;
    }
}
