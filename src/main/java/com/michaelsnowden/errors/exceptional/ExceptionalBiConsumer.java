package com.michaelsnowden.errors.exceptional;

public interface ExceptionalBiConsumer<T, U> {
    void accept(T t, U u) throws Exception;
}
