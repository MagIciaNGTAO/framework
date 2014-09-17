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
package com.fitbur.core.metrics;

import com.codahale.metrics.Histogram;
import com.fitbur.core.metrics.fixture.HistogramInjectedService;
import javax.inject.Inject;
import static org.assertj.core.api.Assertions.assertThat;
import org.jvnet.testing.hk2testng.HK2;
import org.testng.annotations.Test;

/**
 *
 * @author Sharmarke Aden
 */
@HK2
public class HistogramInjectedServiceNGTest {

    @Inject
    HistogramInjectedService sut;

    @Test
    public void assertInjection() {
        assertThat(sut).isNotNull();
    }

    @Test
    public void assertConstructorInjection() {
        Histogram result = sut.getConstructor();
        assertThat(result).isNotNull();
    }

    @Test
    public void assertMethodInjection() {
        Histogram result = sut.getMethod();
        assertThat(result).isNotNull();
    }

    @Test
    public void assertFieldInjection() {
        Histogram result = sut.getField();
        assertThat(result).isNotNull();
    }

    @Test
    public void assertCustomFieldInjection() {
        Histogram result = sut.getCustomField();
        assertThat(result).isNotNull();
    }

    @Test
    public void assertCustomConstructorInjection() {
        Histogram result = sut.getCustomConstructor();
        assertThat(result).isNotNull();
    }
}
