package com.michaelsnowden.errors.rethrown_exceptions;

public class BiConsumerException extends RuntimeException {
    public BiConsumerException(Object a, Object b, Throwable cause) {
        super("a = " + a + " b = " + b, cause);
    }
}