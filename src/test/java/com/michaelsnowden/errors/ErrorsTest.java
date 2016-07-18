package com.michaelsnowden.errors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.function.Function;

import static com.michaelsnowden.errors.Errors.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

/**
 * Created by michael.snowden on 7/15/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class ErrorsTest {
    @Mock
    ExceptionalFunction<Object, Object> mockExceptionalFunction;

    @Mock
    RuntimeException mockRethrownException;

    @Mock
    ExceptionFactory<Object> mockExceptionFactory;

    @Mock
    Object mockArgument;

    @Mock
    Exception mockException;

    @Mock
    Fallback<Object, Object> mockFallback2;

    @Mock
    Function<Object, Object> mockFallback;

    @Test
    public void shouldCallFallbackWithArgumentAndException() throws Exception {
        when(mockExceptionalFunction.apply(any())).thenThrow(mockException);
        fallback(mockExceptionalFunction, mockFallback2).apply(mockArgument);
        verify(mockFallback2).handle(mockArgument, mockException);
    }

    @Test
    public void shouldCallFallbackWithArgument() throws Exception {
        when(mockExceptionalFunction.apply(any())).thenThrow(mockException);
        fallback(mockExceptionalFunction, mockFallback).apply(mockArgument);
        verify(mockFallback).apply(mockArgument);
    }

    @Test
    public void shouldCallExceptionFactory() throws Exception {
        when(mockExceptionFactory.createException(mockArgument, mockException)).thenReturn(mockRethrownException);
        when(mockExceptionalFunction.apply(mockArgument)).thenThrow(mockException);
        try {
            rethrow(mockExceptionalFunction, mockExceptionFactory).apply(mockArgument);
            fail();
        } catch (Exception e) {
            assertEquals(mockRethrownException, e);
        }
    }

    @Test
    public void shouldRethrowException() throws Exception {
        when(mockExceptionalFunction.apply(mockArgument)).thenThrow(mockException);
        try {
            rethrow(mockExceptionalFunction).apply(mockArgument);
            fail();
        } catch (UncheckedExceptionalFunctionError e) {
            assertEquals(mockException, e.getCause());
        }
    }
}