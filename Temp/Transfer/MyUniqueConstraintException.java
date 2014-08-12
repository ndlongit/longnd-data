package jp.co.inte.attendance.exception;

public class MyUniqueConstraintException extends RuntimeException {

    public MyUniqueConstraintException() {
        this(null);
    }

    public MyUniqueConstraintException(Throwable cause) {
        super(cause);
    }
}
