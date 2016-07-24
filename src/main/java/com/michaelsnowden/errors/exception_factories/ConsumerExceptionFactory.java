package com.michaelsnowden.errors.exception_factories;

public interface ConsumerExceptionFactory<T> {
    RuntimeException createException(T t, Exception e);
}
