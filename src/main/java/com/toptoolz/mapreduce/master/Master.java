package com.toptoolz.mapreduce.master;

import java.util.List;

/**
 * @author: danielpo
 * Date: 7/10/13
 * Time: 3:28 PM
 */
public interface Master {
    public void begin();
    public Object reduceResults();
}
