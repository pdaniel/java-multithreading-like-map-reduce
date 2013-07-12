package com.toptoolz;

import com.toptoolz.mapreduce.master.MasterWorkers;
import com.toptoolz.mapreduce.worker.AbstractWorker;
import junit.framework.TestCase;

import java.util.Vector;

/**
 * @author: danielpo
 * Date: 7/10/13
 * Time: 3:39 PM
 */
public class AbstractBaseTest extends TestCase {
    static int threads = 0;
    static MasterWorkers mw;
    protected Vector<AbstractWorker> workers;

    protected Vector<AbstractWorker> createWorkers() {
        mw.forTestCreateWorkers();
        return mw.getWorkers();
    }

    static final String doc1 = "This is document 1";
    static final String doc2 = "This is another document";
    static final String doc3 = "Document 3";
}
