package com.michaelsnowden.errors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.function.Function;

import static org.mockito.Mockito.verify;

/**
 * Created by michael.snowden on 7/15/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class ErrorsTest {
    @Mock
    Errors.ExceptionalFunction<Object, Object> mockFunction;

    @Test
    public void uncheckedFunctionShouldCallOriginalFunction() throws Exception {
        Function<Object, Object> unchecked = Errors.unchecked(mockFunction);
        Object object = new Object();
        unchecked.apply(object);
        verify(mockFunction).apply(object);
    }
}
