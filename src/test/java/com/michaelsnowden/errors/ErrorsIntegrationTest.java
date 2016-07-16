package com.michaelsnowden.errors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.function.Function;

import static com.michaelsnowden.errors.Errors.unchecked;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

/**
 * Created by michael.snowden on 7/15/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class ErrorsIntegrationTest {
    @Mock
    Errors.ExceptionalFunction<Object, Object> mockFunction;

    @Mock
    Errors.ExceptionalFunction<Object, Object> mockExceptionalFunction;

    @Test
    public void uncheckedFunctionShouldReturnSameValue() throws Exception {
        Object argument = new Object();
        Object expectedResult = new Object();
        when(mockFunction.apply(argument)).thenReturn(expectedResult);
        Function<Object, Object> unchecked = unchecked(mockFunction);
        assertEquals(expectedResult, unchecked.apply(argument));
    }

    @Test
    public void exceptionalFunctionShouldThrowError() throws Exception {
        when(mockExceptionalFunction.apply(any())).thenThrow(new RuntimeException());
        boolean exceptionThrown = false;
        Function<Object, Object> unchecked = unchecked(mockExceptionalFunction);
        try {
            unchecked.apply(null);
        } catch (RuntimeException e) {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
    }
}
