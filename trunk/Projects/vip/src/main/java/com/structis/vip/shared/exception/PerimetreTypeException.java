package com.structis.vip.shared.exception;

public class PerimetreTypeException extends ExceptionBase {

    private static final long serialVersionUID = 1L;

    public PerimetreTypeException() {
        super(ExceptionType.UNKNOWN);
    }

    public PerimetreTypeException(ExceptionType code) {
        super(code);
    }
}
