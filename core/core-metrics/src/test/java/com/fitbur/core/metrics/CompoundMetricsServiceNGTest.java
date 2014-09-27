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
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;

import static com.codahale.metrics.MetricRegistry.name;
import com.fitbur.core.metrics.fixture.CompoundMetricsService;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

import org.jvnet.testing.hk2testng.HK2;
import org.testng.annotations.Test;

/**
 *
 * @author Sharmarke Aden
 */
@HK2
public class CompoundMetricsServiceNGTest {

    @Inject
    CompoundMetricsService sut;
    @Inject
    MetricRegistry registry;

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void callToDefaultMethodWithoutExplcitNameShouldThrowException() {
        sut.defaultCountedMeteredTimedMethod();
    }

    @Test
    public void callToCountedMethodShouldBeCounted() {
        sut.explictCountedMetertedTimedMethod();
        Counter counter = registry.counter(name(CompoundMetricsService.class, "counted.method"));
        Meter meter = registry.meter(name(CompoundMetricsService.class, "metered.method"));
        Timer timer = registry.timer(name(CompoundMetricsService.class, "timed.method"));
        assertThat(counter).isNotNull();
        assertThat(counter.getCount()).isEqualTo(1);
        assertThat(meter).isNotNull();
        assertThat(meter.getCount()).isEqualTo(1);
        assertThat(timer).isNotNull();
        assertThat(timer.getCount()).isEqualTo(1);
    }

}
