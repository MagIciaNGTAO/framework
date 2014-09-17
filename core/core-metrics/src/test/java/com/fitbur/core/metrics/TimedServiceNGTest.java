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

import com.codahale.metrics.MetricRegistry;
import static com.codahale.metrics.MetricRegistry.name;
import com.codahale.metrics.Timer;
import com.fitbur.core.metrics.fixture.TimedService;
import javax.inject.Inject;
import static org.assertj.core.api.Assertions.assertThat;
import org.jvnet.testing.hk2testng.HK2;
import org.testng.annotations.Test;

/**
 *
 * @author Sharmarke Aden
 */
@HK2
public class TimedServiceNGTest {

    @Inject
    TimedService sut;
    @Inject
    MetricRegistry registry;

    @Test
    public void callToTimedMethodShouldBeTimed() {
        sut.timedMethod();
        Timer timer = registry.timer(name(TimedService.class, "timedMethod"));
        assertThat(timer).isNotNull();
        assertThat(timer.getCount()).isEqualTo(1);
    }

    @Test
    public void callToTimedAbsoluteMethodShouldBeTimed() {
        sut.timedAbsoluteMethod();
        Timer timer = registry.timer("timedAbsoluteMethod");
        assertThat(timer).isNotNull();
        assertThat(timer.getCount()).isEqualTo(1);
    }

    @Test
    public void callToTimedNamedMethodShouldBeTimed() {
        sut.timedNamedMethod();
        Timer timer = registry.timer(name(TimedService.class, "timedNamedMethod"));
        assertThat(timer).isNotNull();
        assertThat(timer.getCount()).isEqualTo(1);
    }

    @Test
    public void callToTimedNamedAndAbsoluteMethodShouldBeTimed() {
        sut.timedNamedAndAbsoluteMethod();
        Timer timer = registry.timer("timedNamedAndAbsoluteMethod");
        assertThat(timer).isNotNull();
        assertThat(timer.getCount()).isEqualTo(1);
    }
}
