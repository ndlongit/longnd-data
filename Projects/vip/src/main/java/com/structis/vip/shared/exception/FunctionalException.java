package com.structis.vip.shared.exception;

public class FunctionalException extends ServiceException {

    private static final long serialVersionUID = 1L;

    public FunctionalException(String code) {
        super(code);
    }

    public FunctionalException(Throwable e, String code) {
        super(e, code);
    }

}
