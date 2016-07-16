package com.michaelsnowden.errors;

import java.util.function.Function;

/**
 * Created by michael.snowden on 7/15/16.
 */
public class Errors {
    interface ExceptionalFunction<T, R> {
        R apply(T t) throws Exception;
    }

    static class UncheckedExceptionalFunctionError extends RuntimeException {
        public UncheckedExceptionalFunctionError(Throwable cause) {
            super(cause);
        }
    }

    public static <T, R> Function<T, R> unchecked(ExceptionalFunction<T, R> function) {
        return (t) -> {
            try {
                return function.apply(t);
            } catch (Exception e) {
                throw new UncheckedExceptionalFunctionError(e);
            }
        };
    }
}
