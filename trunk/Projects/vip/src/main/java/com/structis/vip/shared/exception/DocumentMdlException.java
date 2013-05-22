package com.structis.vip.shared.exception;

public class DocumentMdlException extends ExceptionBase {

    private static final long serialVersionUID = 1L;

    public DocumentMdlException() {
        super(ExceptionType.UNKNOWN);
    }

    public DocumentMdlException(ExceptionType code) {
        super(code);
    }
}
