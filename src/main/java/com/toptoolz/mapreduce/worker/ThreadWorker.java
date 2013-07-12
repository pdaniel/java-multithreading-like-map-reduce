package com.toptoolz.mapreduce.worker;

import com.toptoolz.mapreduce.MapPhase;
import com.toptoolz.mapreduce.map.Mapper;

/**
 * @author: danielpo
 * Date: 7/10/13
 * Time: 11:33 AM
 */
public class ThreadWorker extends AbstractWorker {

    public ThreadWorker(Mapper mapper, long workerId) {
        super(mapper, workerId);
    }

    public ThreadWorker(Mapper mapper) {
        super(mapper);
    }

  /*  protected ThreadWorker() {
        super();
    }

    public ThreadWorker(long workerId) {
        super(workerId);
    }*/

    @Override
    public void run() {
        MapPhase mapPhase = new MapPhase(mapper, input);
        results.add(mapPhase.map());
    }


    @Override
    public void begin() {
        System.out.println("Thread start :"+getWorkerId());
        System.out.println("Processing : "+ input );
        markStart();
        start();
        markFinish();
    }

   /* public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }*/
}
