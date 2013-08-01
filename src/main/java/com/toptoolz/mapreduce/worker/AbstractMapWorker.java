package com.toptoolz.mapreduce.worker;

import com.toptoolz.mapreduce.map.Mapper;
import com.toptoolz.mapreduce.master.exception.MasterException;

import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * @author danielpo
 *         Date: 7/10/13
 *         Time: 11:31 AM
 */
public abstract class AbstractMapWorker extends BaseWorker implements MapWorker {

    Mapper mapper;
    BlockingQueue<Worker> threads;

    protected AbstractMapWorker(final Mapper mapper, final Object input, final List results) {
        this.mapper = mapper;
        this.input = input;
        this.results = results;
    }

    protected AbstractMapWorker(final Mapper mapper, final Object input, final List results, final BlockingQueue<Worker> threads) {
        this.mapper = mapper;
        this.input = input;
        this.results = results;
        this.threads = threads;
    }

    protected AbstractMapWorker(Mapper mapper, long workerId) {
        this.mapper = mapper;
        this.workerId = workerId;
    }

    protected AbstractMapWorker(Mapper mapper) {
        this.mapper = mapper;
        this.workerId = genWorkerId();
    }

    public Mapper getMapper() {
        return mapper;
    }

    public void setMapper(Mapper mapper) {
        this.mapper = mapper;
    }

    public synchronized void addToThreads(Worker worker) {
        try {
            this.threads.put(worker);
        } catch (InterruptedException e) {
            throw new MasterException(e);
        }
    }

    public synchronized void removeFromThreads(Worker worker) {
        this.threads.remove(worker);
        this.threads.notify();
    }


}
