package com.michaelsnowden.errors;

/**
 * Created by michael.snowden on 7/17/16.
 */
public class UncheckedExceptionalFunctionError extends RuntimeException {
    public UncheckedExceptionalFunctionError(Throwable cause) {
        super(cause);
    }

    public UncheckedExceptionalFunctionError(Object argument, Throwable cause) {
        super("argument = " + argument, cause);
    }
}