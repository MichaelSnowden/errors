package com.michaelsnowden.errors.exception_handlers;

public interface FunctionExceptionHandler<T, R> {
    R apply(T t, Exception e);
}
