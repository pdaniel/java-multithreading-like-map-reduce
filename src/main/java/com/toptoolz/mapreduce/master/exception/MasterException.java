package com.toptoolz.mapreduce.master.exception;

/**
 * @author: danielpo
 * Date: 7/10/13
 * Time: 4:43 PM
 */
public class MasterException extends RuntimeException {
    public MasterException() {
        super();    //To change body of overridden methods use File | Settings | File Templates.
    }

    public MasterException(String message) {
        super(message);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public MasterException(String message, Throwable cause) {
        super(message, cause);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public MasterException(Throwable cause) {
        super(cause);    //To change body of overridden methods use File | Settings | File Templates.
    }

    protected MasterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
