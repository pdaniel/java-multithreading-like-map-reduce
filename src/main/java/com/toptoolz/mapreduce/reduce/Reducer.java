package com.toptoolz.mapreduce.reduce;

import java.util.Collection;

/**
 * @author: danielpo
 * Date: 7/9/13
 * Time: 12:00 PM
 */
public interface Reducer {
    public Object reduce(Collection c);
}
