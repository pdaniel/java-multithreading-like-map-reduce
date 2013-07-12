package com.toptoolz;

import com.toptoolz.mapreduce.worker.AbstractWorker;
import com.toptoolz.mapreduce.worker.Worker;

import java.util.Vector;

/**
 * @author: danielpo
 * Date: 7/10/13
 * Time: 3:39 PM
 */
public class WorkerTests extends AbstractBaseTest {
    Vector<AbstractWorker> myWorkers;

    public void testCreateWorker() {
        myWorkers = createWorkers();
        assertTrue(myWorkers.size() == threads);
    }

    public void testGetAvailableWorker() {
        myWorkers = createWorkers();
        for (int i = 0; i < 50; i++) {
            Worker w = myWorkers.get(i);
            w.setRunning(true);
        }
        Worker w = mw.forTestAvailableWorker();

    }

    public void testGetAvailableWorkerWhenNoAvailable() {
        myWorkers = createWorkers();
        for (int i = 0; i < 100; i++) {
            Worker w = myWorkers.get(i);
            w.setRunning(true);
        }
        Worker w = mw.forTestAvailableWorker();

    }
}
