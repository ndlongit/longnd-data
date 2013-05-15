package com.structis.vip.shared.exception;

public class ChantierTypeException extends ExceptionBase {
	private static final long serialVersionUID = 1L;

	public ChantierTypeException() {
		super(ExceptionType.UNKNOWN);
	}

	public ChantierTypeException(ExceptionType code) {
		super(code);
	}
}
