package com.sedex.appexch.exception;

public class DaoException extends AbstractException {

    protected DaoException() {
        this(null);
    }

    protected DaoException(String message) {
        this(null, message);
    }

    protected DaoException(String errorCode, String message) {
        super(errorCode, message);
    }
}
