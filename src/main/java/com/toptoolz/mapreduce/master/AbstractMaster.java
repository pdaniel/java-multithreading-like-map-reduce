package com.toptoolz.mapreduce.master;

import com.toptoolz.mapreduce.map.Mapper;
import com.toptoolz.mapreduce.master.exception.MasterException;
import com.toptoolz.mapreduce.reduce.Reducer;
import com.toptoolz.mapreduce.worker.*;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.BlockingQueue;

/**
 * @author: danielpo
 * Date: 7/10/13
 * Time: 11:23 AM
 * This class manages the map-reduce process. Creates workers, allocate workers to jobs.
 */
public abstract class AbstractMaster implements Master {

    Vector<AbstractMapWorker> workers = new Vector<>();
    Vector<AbstractReduceWorker> reduceWorkers = new Vector<>();
    List input;
    Mapper mapper;
    Reducer reducer;
    int workersNo;

    protected AbstractMaster(List input, Mapper mapper, Reducer reducer) {
        this.input = input;
        this.mapper = mapper;
        this.reducer = reducer;
    }

    protected AbstractMaster(Reducer reducer, Mapper mapper, List input, int workersNo) {
        this.reducer = reducer;
        this.mapper = mapper;
        this.input = input;
        this.workersNo = workersNo;
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


    protected MapWorker getAvailableMapWorker(BlockingQueue<Long> workerIds) {
        return getAvailableMapWorker(0,workerIds);
    }

    /**
     * Returns an instante of available worker based in idx
     *
     * @param idx - the worker id
     * @return - an available worker from workers list
     */
    protected AbstractMapWorker getAvailableMapWorker(int idx,BlockingQueue<Long> workerIds) {
       /* AbstractMapWorker worker;
        int workersSize = workers.size();
        if (workersSize > 0 && idx < workersSize) {
            worker = workers.get(idx);
            if (!worker.isRunning() && !worker.isTaken()) {
                worker.setTaken(true);
                return worker;
            } else {
                return getAvailableMapWorker(idx + 1);
            }
        }
        try {
            Thread.sleep(100000);  //TODO : find a better way
        } catch (InterruptedException e) {
            throw new MasterException(e);
        }
        return getAvailableMapWorker(0);*/
        while (workerIds.size()>workersNo){
            System.out.println("Too many connections");
        }
        MapThreadWorker worker = new MapThreadWorker(mapper);
        workers.add(worker);
        return worker;
    }

    public Vector<AbstractMapWorker> getWorkers() {
        return workers;
    }
}
