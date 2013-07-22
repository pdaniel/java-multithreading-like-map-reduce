package com.toptoolz.mapreduce.worker;

import com.toptoolz.mapreduce.map.Mapper;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * @author: danielpo
 * Date: 7/10/13
 * Time: 11:31 AM
 */
public abstract class AbstractMapWorker extends BaseWorker  implements MapWorker{

    Mapper mapper;


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

    public void setWorkerIds(BlockingQueue<Long> workerIds){
      this.workerIds = workerIds;
    }







}
