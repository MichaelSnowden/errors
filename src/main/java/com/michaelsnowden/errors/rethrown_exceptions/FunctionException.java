package com.michaelsnowden.errors.rethrown_exceptions;

/**
 * Created by michael.snowden on 7/17/16.
 */
public class FunctionException extends RuntimeException {
    public FunctionException(Object argument, Throwable cause) {
        super("argument = " + argument, cause);
    }
}