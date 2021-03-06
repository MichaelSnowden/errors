package com.michaelsnowden.errors.exceptional;

/**
 * Created by michael.snowden on 7/17/16.
 */
public interface ExceptionalFunction<T, R> {
    R apply(T t) throws Exception;
}