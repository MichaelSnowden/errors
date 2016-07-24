package com.michaelsnowden.errors.exception_factories;

public interface BiFunctionExceptionFactory <T, U> {
    RuntimeException createException(T t, U u, Exception e);
}
