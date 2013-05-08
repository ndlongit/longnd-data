package com.structis.fichesst.shared.exception;

public class FunctionalException extends ServiceException {

	private static final long serialVersionUID = 1L;

	public FunctionalException() {
	}

	public FunctionalException(String code) {
		super(code);
	}

	public FunctionalException(Throwable e, String code) {
		super(e, code);
	}

}
