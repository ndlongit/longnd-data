package com.structis.fichesst.shared.exception;

public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String code;

	public ServiceException() {
	}

	public ServiceException(String code) {
		this.code = code;
	}

	public ServiceException(Throwable e, String code) {
		super(e);
		this.code = code;
	}

	public String getCode() {
		return this.code;
	}

}
