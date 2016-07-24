package com.michaelsnowden.errors.rethrown_exceptions;

public class RunnableException extends RuntimeException {
    public RunnableException(Throwable cause) {
        super(cause);
    }
}