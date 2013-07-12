package com.toptoolz.mapreduce.master;

import com.toptoolz.mapreduce.map.Mapper;
import com.toptoolz.mapreduce.master.exception.MasterException;
import com.toptoolz.mapreduce.reduce.Reducer;
import com.toptoolz.mapreduce.worker.AbstractWorker;
import com.toptoolz.mapreduce.worker.Worker;
import com.toptoolz.mapreduce.worker.WorkerFactory;

import java.util.List;
import java.util.Vector;

/**
 * @author: danielpo
 * Date: 7/10/13
 * Time: 11:23 AM
 * This class manages the map-reduce process. Creates workers, allocate workers to jobs.
 */
public abstract class AbstractMaster implements Master {

    Vector<AbstractWorker> workers = new Vector<>();
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
     * @param abstractWorker - the worker
     */
    public void registerWorker(AbstractWorker abstractWorker) {
        workers.add(abstractWorker);
    }

    public void deleteWorker(AbstractWorker abstractWorker) {
        workers.remove(abstractWorker);
    }

    /**
     * This method creates a pool of workers from witch a worker will be retrieved
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


    protected Worker getAvailableworker() {
        return getAvailableworker(0);
    }

    /**
     * Returns an instante of available worker based in idx
     * @param idx - the worker id
     * @return - an available worker from workers list
     */
    protected AbstractWorker getAvailableworker(int idx) {
        AbstractWorker worker;
        int workersSize = workers.size();
        if (workersSize > 0 && idx < workersSize) {
            worker = workers.get(idx);
            if (!worker.isRunning() && !worker.isTaken()) {
                worker.setTaken(true);
                return worker;
            } else {
                return getAvailableworker(idx + 1);
            }
        }
        try {
            Thread.sleep(100000);  //TODO : find a better way
        } catch (InterruptedException e) {
            throw new MasterException(e);
        }
        return getAvailableworker(0);
    }

    public Vector<AbstractWorker> getWorkers() {
        return workers;
    }
}
