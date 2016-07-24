package com.michaelsnowden.errors.exception_handlers;

public interface ConsumerExceptionHandler<T> {
    void accept(T t, Exception e);
}
