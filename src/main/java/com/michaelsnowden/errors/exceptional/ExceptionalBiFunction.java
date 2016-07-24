package com.michaelsnowden.errors.exceptional;

public interface ExceptionalBiFunction<T, U, R> {
    R apply(T t, U u) throws Exception;
}