/*
 * Copyright 2014 Fitbur, Inc..
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
package com.fitbur.core.core.el.mvel;

import java.io.StringReader;
import java.io.StringWriter;
import javax.inject.Inject;
import static org.assertj.core.api.Assertions.assertThat;
import org.jvnet.testing.hk2testng.HK2;
import org.testng.annotations.Test;

/**
 *
 * @author saden
 */
@HK2
public class MvelTemplateServiceNGTest {

    @Inject
    MvelTemplateService sut;

    @Test(expectedExceptions = java.lang.NullPointerException.class)
    public void giveNullNameEvalShouldThrowException() {
        sut.eval(null, new StringReader("'string has' + length()"), new StringWriter(), "test");
    }

    @Test(expectedExceptions = java.lang.NullPointerException.class)
    public void giveNullTemplateEvalShouldThrowException() {
        sut.eval("test", null, new StringWriter(), "test");
    }

    @Test(expectedExceptions = java.lang.NullPointerException.class)
    public void giveNullOutputEvalShouldThrowException() {
        sut.eval("test", new StringReader("'string has' + length()"), null, "test");
    }

    @Test
    public void giveTemplateEvalShouldReturn() {
        StringWriter result = new StringWriter();
        StringReader template = new StringReader("My name is @{toString()}");
        sut.eval("test", template, result, "test");
        assertThat(result.toString()).isEqualTo("My name is test");
    }

    @Test
    public void callToEvalShouldBePerformant() {
        StringReader template = new StringReader("My name is @{toString()}");
        for (int i = 0; i < 1_000_000; i++) {
            StringWriter result = new StringWriter();
            sut.eval("test", template, result, "test");
            assertThat(result.toString()).isEqualTo("My name is test");
        }

    }
}
