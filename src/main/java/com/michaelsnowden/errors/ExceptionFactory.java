package com.michaelsnowden.errors;

/**
 * Created by michael.snowden on 7/17/16.
 */
public interface ExceptionFactory<T> {
    RuntimeException createException(T t, Exception e);
}