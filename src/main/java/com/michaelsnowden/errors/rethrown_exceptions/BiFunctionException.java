package com.michaelsnowden.errors.rethrown_exceptions;

public class BiFunctionException extends RuntimeException {
    public BiFunctionException(Object a, Object b, Throwable cause) {
        super("a = " + a + " b = " + b, cause);
    }
}
