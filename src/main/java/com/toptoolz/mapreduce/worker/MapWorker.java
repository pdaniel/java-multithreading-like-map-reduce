package com.toptoolz.mapreduce.worker;

import com.toptoolz.mapreduce.map.Mapper;

/**
 * User: Daniel P.
 * Date: 7/13/13
 * Time: 8:14 PM
 * Copyright @ Toptoolz Com
 */
public interface MapWorker extends Worker {
    public void setMapper(Mapper mapper);
}
