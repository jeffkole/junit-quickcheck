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

package com.pholser.junit.quickcheck.internal.generator;

import java.util.List;
import java.util.concurrent.Callable;

import com.pholser.junit.quickcheck.generator.CallableGenerator;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.generator.IntegerGenerator;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.google.common.collect.Lists.*;
import static com.pholser.junit.quickcheck.internal.generator.Generators.*;

@RunWith(MockitoJUnitRunner.class)
public class RegisteringGeneratorsForHierarchyOfInterfaceTest {
    private GeneratorRepository repo;
    private CallableGenerator<?> generator;
    @Mock private SourceOfRandomness random;

    @Before
    public void beforeEach() {
        repo = new GeneratorRepository(random);

        generator = new CallableGenerator<Object>();
        List<Generator<?>> generators = newArrayList();
        generators.add(generator);
        generators.add(new IntegerGenerator());

        repo.add(generators);
    }

    @Test
    public void callable() {
        Generator<?> result = repo.generatorFor(Callable.class);

        assertGenerators(result, generator.getClass());
    }

    @Test
    public void object() {
        Generator<?> result = repo.generatorFor(Object.class);

        assertGenerators(result, generator.getClass(), IntegerGenerator.class);
    }
}