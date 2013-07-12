package com.toptoolz.mapreduce;

import com.toptoolz.mapreduce.map.Mapper;
import com.toptoolz.mapreduce.task.Task;

/**
 * @author: danielpo
 * Date: 7/11/13
 * Time: 11:27 AM
 */
public class MapPhase {
    Mapper mapper;
    String input;

    public MapPhase(Mapper mapper, String input) {
        this.mapper = mapper;
        this.input = input;
    }

    public  Object map(){
       return mapper.map(input) ;
    }
}
