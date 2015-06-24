package com.sedex.appexch.exception;

public abstract class AbstractException extends Exception {

    private String errorCode;
    private String locallizedMessage;
    private Throwable cause;

    protected AbstractException() {
        this("");
    }

    protected AbstractException(Throwable cause) {
        this.cause = cause;
    }

    protected AbstractException(String message) {
        this(null, message);
    }

    protected AbstractException(String errorCode, String message) {
        super(message);
        this.setErrorCode(errorCode);
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getLocallizedMessage() {
        return locallizedMessage;
    }

    public void setLocallizedMessage(String locallizedMessage) {
        this.locallizedMessage = locallizedMessage;
    }

//    public Throwable getCause() {
//        return cause;
//    }
}
