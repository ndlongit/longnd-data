package com.structis.vip.shared.exception;

public class TechnicalException extends ServiceException {

    private static final long serialVersionUID = 1L;

    public TechnicalException(String code) {
        super(code);
    }

    public TechnicalException(Throwable e, String code) {
        super(e, code);
    }

}
