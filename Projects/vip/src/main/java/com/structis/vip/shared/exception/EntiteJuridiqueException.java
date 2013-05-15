package com.structis.vip.shared.exception;

public class EntiteJuridiqueException extends ExceptionBase {
	private static final long serialVersionUID = 1L;

	public EntiteJuridiqueException() {
		super(ExceptionType.UNKNOWN);
	}

	public EntiteJuridiqueException(ExceptionType code) {
		super(code);
	}
}