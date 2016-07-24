package com.michaelsnowden.errors;

import com.michaelsnowden.errors.exception_factories.*;
import com.michaelsnowden.errors.exception_handlers.*;
import com.michaelsnowden.errors.exceptional.*;
import com.michaelsnowden.errors.rethrown_exceptions.*;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public class Errors {
    //region BiConsumer
    static <T, U> BiConsumer<T, U> handle(ExceptionalBiConsumer<T, U> consumer, BiConsumerExceptionHandler<T, U>
            handler) {
        return (t, u) -> {
            try {
                consumer.accept(t, u);
            } catch (Exception e) {
                handler.accept(t, u, e);
            }
        };
    }

    static <T, U> BiConsumer<T, U> fallback(ExceptionalBiConsumer<T, U> consumer, BiConsumer<T, U> fallback) {
        return handle(consumer, (t, u, e) -> {
            fallback.accept(t, u);
        });
    }

    static <T, U> BiConsumer<T, U> rethrow(ExceptionalBiConsumer<T, U> consumer, BiConsumerExceptionFactory<T, U>
            factory) {
        return handle(consumer, (t, u, e) -> {
            throw factory.createException(t, u, e);
        });
    }

    static <T, U> BiConsumer<T, U> rethrow(ExceptionalBiConsumer<T, U> consumer) {
        return rethrow(consumer, BiConsumerException::new);
    }
    //endregion

    //region BiFunction
    static <T, U, R> BiFunction<T, U, R> handle(ExceptionalBiFunction<T, U, R> function,
                                                BiFunctionExceptionHandler<T, U, R> handler) {
        return (t, u) -> {
            try {
                return function.apply(t, u);
            } catch (Exception e) {
                return handler.apply(t, u, e);
            }
        };
    }

    static <T, U, R> BiFunction<T, U, R> fallback(ExceptionalBiFunction<T, U, R> function, BiFunction<T, U, R>
            fallback) {
        return handle(function, (t, u, e) -> fallback.apply(t, u));
    }

    static <T, U, R> BiFunction<T, U, R> rethrow(ExceptionalBiFunction<T, U, R> function,
                                                 BiFunctionExceptionFactory<T, U> factory) {
        return handle(function, (t, u, e) -> {
            throw factory.createException(t, u, e);
        });
    }

    static <T, U, R> BiFunction<T, U, R> rethrow(ExceptionalBiFunction<T, U, R> function) {
        return rethrow(function, BiFunctionException::new);
    }
    //endregion

    //region Consumer
    static <T> Consumer<T> handle(ExceptionalConsumer<T> consumer, ConsumerExceptionHandler<T> handler) {
        return (t) -> {
            try {
                consumer.accept(t);
            } catch (Exception e) {
                handler.accept(t, e);
            }
        };
    }

    static <T> Consumer<T> fallback(ExceptionalConsumer<T> consumer, Consumer<T> fallback) {
        return handle(consumer, (t, e) -> {
            fallback.accept(t);
        });
    }

    static <T> Consumer<T> rethrow(ExceptionalConsumer<T> consumer, ConsumerExceptionFactory<T> factory) {
        return handle(consumer, (t, e) -> {
            throw factory.createException(t, e);
        });
    }

    static <T> Consumer<T> rethrow(ExceptionalConsumer<T> consumer) {
        return rethrow(consumer, ConsumerException::new);
    }
    //endregion

    //region Function
    static <T, R> Function<T, R> handle(ExceptionalFunction<T, R> function, FunctionExceptionHandler<T, R> handler) {
        return (t) -> {
            try {
                return function.apply(t);
            } catch (Exception e) {
                return handler.apply(t, e);
            }
        };
    }

    static <T, R> Function<T, R> fallback(ExceptionalFunction<T, R> function, Function<T, R> fallback) {
        return handle(function, (t, e) -> fallback.apply(t));
    }

    static <T, R> Function<T, R> rethrow(ExceptionalFunction<T, R> function, FunctionExceptionFactory<T> factory) {
        return handle(function, (t, e) -> {
            throw factory.createException(t, e);
        });
    }

    static <T, R> Function<T, R> rethrow(ExceptionalFunction<T, R> function) {
        return rethrow(function, FunctionException::new);
    }
    //endregion

    //region Runnable
    static Runnable handle(ExceptionalRunnable runnable, RunnableExceptionHandler handler) {
        return () -> {
            try {
                runnable.run();
            } catch (Exception e) {
                handler.run(e);
            }
        };
    }

    static Runnable fallback(ExceptionalRunnable runnable, Runnable fallback) {
        return handle(runnable, (e) -> {
            fallback.run();
        });
    }

    static Runnable rethrow(ExceptionalRunnable runnable, RunnableExceptionFactory factory) {
        return handle(runnable, (e) -> {
            throw factory.createException(e);
        });
    }

    static Runnable rethrow(ExceptionalRunnable runnable) {
        return rethrow(runnable, RunnableException::new);
    }
    //endregion
}