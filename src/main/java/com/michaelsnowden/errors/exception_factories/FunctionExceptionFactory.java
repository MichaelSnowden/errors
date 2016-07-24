package com.michaelsnowden.errors.exception_factories;

/**
 * Created by michael.snowden on 7/17/16.
 */
public interface FunctionExceptionFactory<T> {
    RuntimeException createException(T t, Exception e);
}