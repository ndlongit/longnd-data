package com.structis.vip.shared.exception;

public class UserException extends ExceptionBase {

    private static final long serialVersionUID = 1L;

    public UserException() {
        super(ExceptionType.UNKNOWN);
    }

    public UserException(ExceptionType code) {
        super(code);
    }
}
