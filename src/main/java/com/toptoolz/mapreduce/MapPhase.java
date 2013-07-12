package com.toptoolz.mapreduce;

import com.toptoolz.mapreduce.map.Mapper;

/**
 * @author: danielpo
 * Date: 7/11/13
 * Time: 11:27 AM
 * This is a utility method that starts the map process from mapper
 */
public class MapPhase {
    Mapper mapper;
    String input;

    public MapPhase(Mapper mapper, String input) {
        this.mapper = mapper;
        this.input = input;
    }

    public Object map() {
        return mapper.map(input);
    }
}
