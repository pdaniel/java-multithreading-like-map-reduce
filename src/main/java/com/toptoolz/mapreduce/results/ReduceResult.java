package com.toptoolz.mapreduce.results;

/**
 * @author: danielpo
 * Date: 7/9/13
 * Time: 6:21 PM
 */
public class ReduceResult<T> {
    T data;

    public ReduceResult(T data) {
        this.data = data;
    }

    public T getData(){
        return data;
    }
}
