package io.app.exception;

public class DuplicateFoundException extends RuntimeException {
    public DuplicateFoundException(String msg) {
        super(msg);
    }
}
