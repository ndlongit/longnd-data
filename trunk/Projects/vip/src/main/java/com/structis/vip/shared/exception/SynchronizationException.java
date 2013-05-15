package com.structis.vip.shared.exception;

public class SynchronizationException extends ExceptionBase {	
	private static final long serialVersionUID = 1L;
	public SynchronizationException() {
		super(ExceptionType.UNKNOWN);
	}
	public SynchronizationException(ExceptionType code) {
		super(code);			
	}				
}
