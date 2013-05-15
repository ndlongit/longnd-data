package com.structis.vip.shared.exception;

public class DelegationException extends ExceptionBase {
	private static final long serialVersionUID = 1L;

	public DelegationException() {
		super(ExceptionType.UNKNOWN);
	}

	public DelegationException(ExceptionType code) {
		super(code);
	}
}
