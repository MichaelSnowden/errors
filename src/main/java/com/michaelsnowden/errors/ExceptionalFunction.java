package com.michaelsnowden.errors;

/**
 * Created by michael.snowden on 7/17/16.
 */
public interface ExceptionalFunction<T, R> {
    R apply(T t) throws Exception;
}
