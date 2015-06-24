package com.sedex.appexch.exception;

public class ServiceException extends AbstractException {

    protected ServiceException() {
        this(null);
    }

    protected ServiceException(String message) {
        this(null, message);
    }

    protected ServiceException(String errorCode, String message) {
        super(errorCode, message);
    }
}
