package jp.co.inte.attendance.exception;

public class MyValidationException extends RuntimeException {

    public MyValidationException() {
        this(null);
    }

    public MyValidationException(Throwable cause) {
        super(cause);
    }
}
