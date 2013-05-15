package com.structis.vip.shared.exception;

import java.io.Serializable;

public class ExceptionBase extends RuntimeException implements Serializable {

	private static final long serialVersionUID = 1L;
	
	protected ExceptionType code;

	public ExceptionBase() {		
	}
	
	public ExceptionBase(ExceptionType code) {
		this.code = code;
	}
	
	public ExceptionBase(Throwable e, ExceptionType code){
		super(e);
		this.code = code;
	}
	
	public ExceptionType getCode(){
		return this.code;
	}
	
	public String getErrorMessage(){
		return "";
	}

}
