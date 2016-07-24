package com.michaelsnowden.errors.exceptional;

/**
 * Created by michael.snowden on 7/17/16.
 */
public interface ExceptionalConsumer<T> {
    void accept(T t) throws Exception;
}