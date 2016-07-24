package com.michaelsnowden.errors.exception_factories;

public interface BiConsumerExceptionFactory<T, U> {
    RuntimeException createException(T t, U u, Exception e);
}
