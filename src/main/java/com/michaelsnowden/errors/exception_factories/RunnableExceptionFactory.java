package com.michaelsnowden.errors.exception_factories;

public interface RunnableExceptionFactory {
    RuntimeException createException(Exception e);
}
