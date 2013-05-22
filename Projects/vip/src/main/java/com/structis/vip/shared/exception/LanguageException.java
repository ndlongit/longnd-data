package com.structis.vip.shared.exception;

public class LanguageException extends ExceptionBase {

    private static final long serialVersionUID = 1L;

    public LanguageException() {
        super(ExceptionType.UNKNOWN);
    }

    public LanguageException(ExceptionType code) {
        super(code);
    }
}
