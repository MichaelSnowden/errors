package com.michaelsnowden.errors;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Created by michael.snowden on 7/15/16.
 */
public class Errors {
    /**
     * Tries to call a function and instead calls the handler if an exception was caught.
     *
     * @param function The original function which throws an exception
     * @param handler  A handler to fallback to when the function call fails
     * @param <T>      Argument type
     * @param <R>      Return type
     * @return Either {@link ExceptionalFunction#apply(Object)} or {@link BiFunction#apply(Object, Object)} if
     * the original function threw an exception
     */
    public static <T, R> Function<T, R> fallback(ExceptionalFunction<T, R> function, Fallback<T, R> handler) {
        return (t) -> {
            try {
                return function.apply(t);
            } catch (Exception e) {
                return handler.handle(t, e);
            }
        };
    }

    /**
     * Default a function to a fallback in case it fails originally. These can be chained quite nicely with something
     * like <pre>{@code defaultTo(f1, defaultTo(f2, defaultTo(f3, f4))) }</pre>.
     *
     * @param function The checked exceptional function to convert into an unchecked exceptional function
     * @param fallback The fallback method to call in case an exception is thrown by the original function
     * @param <T> Argument type
     * @param <R> Return type
     * @return An unchecked exceptional function which just calls the fallback
     */
    public static <T, R> Function<T, R> fallback(ExceptionalFunction<T, R> function, Function<T, R> fallback) {
        return fallback(function, (t, e) -> fallback.apply(t));
    }

    /**
     * Just rethrows an exception according to {@link ExceptionFactory#createException(Object, Exception)}
     *
     * @param function         The checked exceptional function to convert into an unchecked exceptional function
     * @param exceptionFactory Will create a new exception if the original function application fails
     * @param <T>              Argument type
     * @param <R>              Return type
     * @return An unchecked exceptional function created via {@link ExceptionFactory#createException(Object, Exception)}
     */
    public static <T, R> Function<T, R> rethrow(ExceptionalFunction<T, R> function, ExceptionFactory<T> exceptionFactory) {
        return fallback(function, (t, e) -> {
            throw exceptionFactory.createException(t, e);
        });
    }

    /**
     * Rethrows an exception according to {@link UncheckedExceptionalFunctionError#UncheckedExceptionalFunctionError(Object,
     * Throwable)}
     *
     * @param function The checked exceptional function to convert into an unchecked exceptional function
     * @param <T>      Argument type
     * @param <R>      Return type
     * @return An unchecked exceptional function created via {@link UncheckedExceptionalFunctionError#UncheckedExceptionalFunctionError(Object,
     * Throwable)}
     */
    public static <T, R> Function<T, R> rethrow(ExceptionalFunction<T, R> function) {
        return rethrow(function, UncheckedExceptionalFunctionError::new);
    }
}