package com.structis.vip.shared.exception;

public class PerimetreException extends ExceptionBase {

    private static final long serialVersionUID = 1L;

    public PerimetreException() {
        super(ExceptionType.UNKNOWN);
    }

    public PerimetreException(ExceptionType code) {
        super(code);
    }
}
