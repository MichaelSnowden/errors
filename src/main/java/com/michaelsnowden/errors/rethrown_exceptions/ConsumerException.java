package com.michaelsnowden.errors.rethrown_exceptions;

public class ConsumerException extends RuntimeException {
    public ConsumerException(Object a, Throwable cause) {
        super("a = " + a, cause);
    }
}