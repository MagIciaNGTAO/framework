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
import com.github.mustachejava.MustacheFactory;
import com.google.common.collect.ImmutableMap;
import static com.google.common.collect.ImmutableMap.of;
import static com.google.common.collect.ImmutableMap.of;
import static com.google.common.collect.ImmutableMap.of;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import javax.inject.Inject;
import static org.assertj.core.api.Assertions.assertThat;
import org.jvnet.testing.hk2mockito.SC;
import org.jvnet.testing.hk2mockito.SUT;
import org.jvnet.testing.hk2testng.HK2;
import static org.mockito.Mockito.reset;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author saden
 */
@HK2
public class MustacheNGTest {

    @Inject
    @SUT
    MustacheBuilder sut;
    @Inject
    @SC
    MustacheFactory factory;

    @BeforeMethod
    public void init() {
        reset(sut, factory);
    }

    @Test
    public void givenValidResourceExecuteShouldReturn() {
        String resource = "test.mustache";
        ImmutableMap<String, String> scopes = of("name", "Tester");

        Mustache mustache = sut.resource(resource).build();
        StringWriter result = new StringWriter();
        mustache.execute(result, scopes);

        assertThat(result).isNotNull();
        assertThat(result.toString()).isEqualTo("Hello Tester");

    }

    @Test
    public void givenStringTemplateExecuteShouldReturn() {
        String template = "Hello {{name}}";
        String resource = "test";
        ImmutableMap<String, String> scopes = of("name", "Tester");
        Mustache mustache = sut.resource(resource).template(template).build();
        StringWriter result = new StringWriter();
        mustache.execute(result, scopes);

        assertThat(result).isNotNull();
        assertThat(result.toString()).isEqualTo("Hello Tester");

    }

    @Test
    public void givenReaderTemplateExecuteShouldReturn() {
        Reader template = new StringReader("Hello {{name}}");
        String resource = "test";
        ImmutableMap<String, String> scopes = of("name", "Tester");
        Mustache mustache = sut.resource(resource).template(template).build();
        StringWriter result = new StringWriter();
        mustache.execute(result, scopes);

        assertThat(result).isNotNull();
        assertThat(result.toString()).isEqualTo("Hello Tester");

    }
}
