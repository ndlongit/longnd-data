package com.structis.vip.shared.exception;

public class ExternControllerException extends ExceptionBase {

    private static final long serialVersionUID = 1L;

    public ExternControllerException() {
        super(ExceptionType.UNKNOWN);
    }

    public ExternControllerException(ExceptionType code) {
        super(code);
    }
}
