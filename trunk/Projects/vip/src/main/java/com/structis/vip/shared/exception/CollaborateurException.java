package com.structis.vip.shared.exception;

public class CollaborateurException extends ExceptionBase {
	private static final long serialVersionUID = 1L;

	public CollaborateurException() {
		super(ExceptionType.UNKNOWN);
	}

	public CollaborateurException(ExceptionType code) {
		super(code);
	}
}
