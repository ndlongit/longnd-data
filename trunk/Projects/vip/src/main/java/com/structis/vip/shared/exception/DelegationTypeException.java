package com.structis.vip.shared.exception;

public class DelegationTypeException extends ExceptionBase {
	private static final long serialVersionUID = 1L;

	public DelegationTypeException() {
		super(ExceptionType.UNKNOWN);
	}

	public DelegationTypeException(ExceptionType code) {
		super(code);
	}
}
