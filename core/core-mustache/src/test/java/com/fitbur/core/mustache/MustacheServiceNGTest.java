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
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import org.jvnet.testing.hk2mockito.SC;
import org.jvnet.testing.hk2mockito.SUT;
import org.jvnet.testing.hk2testng.HK2;
import org.mockito.Matchers;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isNull;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author saden
 */
@HK2
public class MustacheServiceNGTest {

    @Inject
    @SUT
    MustacheService sut;
    @Inject
    @SC
    MustacheFactory factory;

    @BeforeMethod
    public void init() {
        reset(sut, factory);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void givenNullResourceNewMustacheShouldThrowException() {
        sut.newMustache(null);
    }

    @Test(expectedExceptions = MustacheException.class)
    public void givenInvalidResourceNewMustacheShouldThrowExcetion() {
        sut.newMustache("nonexistent");
    }

    @Test
    public void givenValidResourceNewMustacheShouldReturn() {
        String resource = "test.mustache";
        Mustache result = sut.newMustache(resource);
        assertThat(result).isNotNull();
        verify(factory).compile(resource);
    }

    @Test
    public void givenNullNameNewMustacheStringTemplateShouldReturn() {
        String name = null;
        String template = "template";
        Mustache result = sut.newMustache(name, template);
        assertThat(result).isNotNull();
        verify(factory).compile(any(Reader.class), eq(name));
    }

    @Test
    public void givenValidNameAndStringTemplateNewMustacheShouldReturn() {
        String name = "name";
        Mustache result = sut.newMustache(name, "template");
        assertThat(result).isNotNull();
        verify(factory).compile(any(Reader.class), eq(name));
    }

    @Test
    public void givenNullNameNewMustacheReaderTemplateShouldReturn() {
        String name = null;
        Mustache result = sut.newMustache(name, new StringReader("template"));
        assertThat(result).isNotNull();
        verify(factory).compile(any(Reader.class), eq(name));
    }

    @Test
    public void givenValidNameAndReaderTemplateNewMustacheShouldReturn() {
        String name = "name";
        Mustache result = sut.newMustache(name, new StringReader("template"));
        assertThat(result).isNotNull();
        verify(factory).compile(any(Reader.class), eq(name));
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void givenNullStringTemplateNewMustacheShouldThrowException() {
        sut.newMustache("test", (String) null);
    }

    @Test(expectedExceptions = MustacheException.class)
    public void givenNullReaderTemplateNewMustacheShouldThrowException() {
        sut.newMustache("test", (Reader) null);
    }

}
