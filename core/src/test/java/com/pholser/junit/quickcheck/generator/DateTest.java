/*
 The MIT License

 Copyright (c) 2010-2013 Paul R. Holser, Jr.

 Permission is hereby granted, free of charge, to any person obtaining
 a copy of this software and associated documentation files (the
 "Software"), to deal in the Software without restriction, including
 without limitation the rights to use, copy, modify, merge, publish,
 distribute, sublicense, and/or sell copies of the Software, and to
 permit persons to whom the Software is furnished to do so, subject to
 the following conditions:

 The above copyright notice and this permission notice shall be
 included in all copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

package com.pholser.junit.quickcheck.generator;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

import static java.util.Arrays.*;

import com.pholser.junit.quickcheck.internal.generator.GeneratingUniformRandomValuesForTheoryParameterTest;

import static org.mockito.Mockito.*;

public class DateTest extends GeneratingUniformRandomValuesForTheoryParameterTest {
    @Override protected void primeSourceOfRandomness() {
        when(randomForParameterGenerator.nextLong(Integer.MIN_VALUE, Long.MAX_VALUE))
            .thenReturn(0L).thenReturn(60000L).thenReturn(100000000L).thenReturn(300000000000L);
    }

    @Override protected Type parameterType() {
        return Date.class;
    }

    @Override protected int sampleSize() {
        return 4;
    }

    @Override protected List<?> randomValues() {
        return asList(new Date(0), new Date(60000), new Date(100000000), new Date(300000000000L));
    }

    @Override public void verifyInteractionWithRandomness() {
        verify(randomForParameterGenerator, times(4)).nextLong(Integer.MIN_VALUE, Long.MAX_VALUE);
    }
}
