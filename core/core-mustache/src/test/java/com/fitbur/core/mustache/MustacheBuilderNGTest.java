/*
 * Copyright 2014 Fitbur.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.fitbur.core.mustache;

import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheException;
import com.github.mustachejava.MustacheFactory;
import java.io.Reader;
import java.io.StringReader;
import javax.inject.Inject;
import static org.assertj.core.api.Assertions.assertThat;
import org.jvnet.testing.hk2mockito.SC;
import org.jvnet.testing.hk2mockito.SUT;
import org.jvnet.testing.hk2testng.HK2;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author saden
 */
@HK2
public class MustacheBuilderNGTest {

    @SUT
    @Inject
    MustacheBuilder sut;
    @SC
    @Inject
    MustacheFactory factory;

    @BeforeMethod
    public void init() {
        reset(sut, factory);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void givenNullBuildShouldThrowException() {
        sut.resource(null).build();
    }

    @Test(expectedExceptions = MustacheException.class)
    public void givenInvalidResoruceBuildShouldThrowException() {
        sut.resource("nonexistent").build();
    }

    @Test
    public void givenValidResourceBuildShouldReturn() {
        String resource = "test.mustache";
        Mustache result = sut.resource(resource).build();
        assertThat(result).isNotNull();
        verify(factory).compile(resource);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void givenNullStringTemplateBuildShouldThrowException() {
        String template = null;
        sut.resource("test").template(template).build();
    }

    @Test
    public void givenValidStringTemplateBuildShouldReturn() {
        String template = "Hello {{name}}";
        String resource = "test";
        Mustache result = sut.resource(resource).template(template).build();
        assertThat(result).isNotNull();
        verify(factory).compile(any(StringReader.class), eq(resource));
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void givenNullReaderTemplateBuildShouldThrowException() {
        Reader template = null;
        sut.resource("test").template(template).build();
    }

    @Test
    public void givenValidReaderTemplateBuildShouldReturn() {
        Reader template = new StringReader("Hello {{name}}");
        String resource = "test";
        Mustache result = sut.resource(resource).template(template).build();
        assertThat(result).isNotNull();
        verify(factory).compile(any(StringReader.class), eq(resource));
    }

}
