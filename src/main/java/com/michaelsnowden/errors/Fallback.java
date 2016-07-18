package com.michaelsnowden.errors;

/**
 * Created by michael.snowden on 7/17/16.
 */
public interface Fallback<T, R> {
    R handle(T t, Exception e);
}
