package com.structis.vip.shared.exception;

public class NatureException extends ExceptionBase {

    private static final long serialVersionUID = 1L;

    public NatureException() {
        super(ExceptionType.UNKNOWN);
    }

    public NatureException(ExceptionType code) {
        super(code);
    }
}
