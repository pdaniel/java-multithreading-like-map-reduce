package com.toptoolz.mapreduce;

import com.toptoolz.mapreduce.map.Mapper;
import com.toptoolz.mapreduce.master.Master;
import com.toptoolz.mapreduce.master.MasterWorkers;
import com.toptoolz.mapreduce.reduce.Reducer;

import java.util.List;

/**
 * @author: danielpo
 * Date: 7/9/13
 * Time: 5:09 PM
 * This is the builder class for map reduce process.
 */
public class MapReduce {
    Object input;
    Object output;
    Mapper mapper;
    Reducer reducer;
    int workersNo;

    private MapReduce(final Builder builder) {
        this.input = builder.input;
        this.output = builder.output;
        this.mapper = builder.mapper;
        this.reducer = builder.reducer;
        this.workersNo = builder.workersNo;
    }

    public static class Builder {
        Object input;
        Object output;
        Mapper mapper;
        Reducer reducer;
        int workersNo;

        public Builder input(Object input) {
            this.input = input;
            return this;
        }

        public Builder output(Object output) {
            this.output = output;
            return this;
        }

        public Builder mapper(Mapper mapper) {
            this.mapper = mapper;
            return this;
        }

        public Builder reducer(Reducer reducer) {
            this.reducer = reducer;
            return this;
        }

        public Builder workersNo(int workersNo) {
            this.workersNo = workersNo;
            return this;
        }

        public MapReduce build() {
            return new MapReduce(this);
        }


    }

    public Master master = null;

    /**
     * this method delegates the work to MasetrWork class that manages the entire map-reduce process.
     */
    public void begin() {
        master = new MasterWorkers(reducer, mapper, (List) input, workersNo);
        master.begin();
    }
}
