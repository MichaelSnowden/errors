package com.michaelsnowden.errors.exception_handlers;

public interface BiFunctionExceptionHandler<T, U, R> {
    R apply(T t, U u, Exception e);
}
