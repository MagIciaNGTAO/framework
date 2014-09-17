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

import com.codahale.metrics.Counter;
import com.codahale.metrics.MetricRegistry;
import static com.codahale.metrics.MetricRegistry.name;
import com.fitbur.core.metrics.fixture.CountedService;
import javax.inject.Inject;
import static org.assertj.core.api.Assertions.assertThat;
import org.jvnet.testing.hk2testng.HK2;
import org.testng.annotations.Test;

/**
 *
 * @author Sharmarke Aden
 */
@HK2
public class CountedServiceNGTest {

    @Inject
    CountedService sut;
    @Inject
    MetricRegistry registry;

    @Test
    public void callToCountedMethodShouldBeCounted() {
        sut.countedMethod();
        Counter counter = registry.counter(name(CountedService.class, "countedMethod"));
        assertThat(counter).isNotNull();
        assertThat(counter.getCount()).isEqualTo(1);
    }

    @Test
    public void callToCountedAbsoluteMethodShouldBeCounted() {
        sut.countedAbsoluteMethod();
        Counter counter = registry.counter("countedAbsoluteMethod");
        assertThat(counter).isNotNull();
        assertThat(counter.getCount()).isEqualTo(1);
    }

    @Test
    public void callToCountedNamedMethodShouldBeCounted() {
        sut.countedNamedMethod();
        Counter counter = registry.counter(name(CountedService.class, "countedNamedMethod"));
        assertThat(counter).isNotNull();
        assertThat(counter.getCount()).isEqualTo(1);
    }

    @Test
    public void callToCountedNamedAndAbsoluteMethodShouldBeCounted() {
        sut.countedNamedAndAbsoluteMethod();
        Counter counter = registry.counter("countedNamedAndAbsoluteMethod");
        assertThat(counter).isNotNull();
        assertThat(counter.getCount()).isEqualTo(1);
    }

    @Test
    public void callToIncrementShouldBeIncremented() {
        sut.increment();
        Counter counter = registry.counter(name(CountedService.class, "increment"));
        assertThat(counter).isNotNull();
        assertThat(counter.getCount()).isEqualTo(1);
    }

    @Test
    public void callToDecrementShouldBeDecremented() {
        sut.decrement();
        Counter counter = registry.counter(name(CountedService.class, "decrement"));
        assertThat(counter).isNotNull();
        assertThat(counter.getCount()).isEqualTo(-1);
    }
}
