package com.michaelsnowden.errors.exception_handlers;

public interface BiConsumerExceptionHandler<T, U> {
    void accept(T t, U u, Exception e);
}