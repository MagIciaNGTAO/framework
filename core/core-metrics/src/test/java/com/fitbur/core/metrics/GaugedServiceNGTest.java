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

import com.codahale.metrics.Gauge;
import com.codahale.metrics.MetricRegistry;
import static com.codahale.metrics.MetricRegistry.name;
import com.fitbur.core.metrics.fixture.GaugedService;
import java.lang.reflect.Method;
import java.security.Permission;
import javax.inject.Inject;
import static org.assertj.core.api.Assertions.assertThat;
import org.jvnet.testing.hk2testng.HK2;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author Sharmarke Aden
 */
@HK2
public class GaugedServiceNGTest {

    @Inject
    GaugedService sut;
    @Inject
    MetricRegistry registry;

    @BeforeClass
    public void init() {
        assertThat(sut).isNotNull();
        assertThat(registry).isNotNull();
    }

    @Test
    public void callToGaugedMethodShouldCreateGauge() {
        String result = sut.gaugedMethod();
        assertThat(result).isNotNull();

        Gauge<String> gauge = registry.getGauges()
                .get(name(GaugedService.class, "gaugedMethod"));
        assertThat(result).isEqualTo(gauge.getValue());
    }

    @Test
    public void callToAbsoluteGaugedMethodShouldCreateGauge() {
        String result = sut.absoluteGaugedMethod();
        assertThat(result).isNotNull();

        Gauge<String> gauge = registry.getGauges()
                .get(name("absoluteGaugedMethod"));
        assertThat(result).isEqualTo(gauge.getValue());
    }

    @Test
    public void callToNamedGaugedMethodShouldCreateGauge() {
        String result = sut.namedGaugedMethod();
        assertThat(result).isNotNull();

        Gauge<String> gauge = registry.getGauges()
                .get(name(GaugedService.class, "namedGaugedMethod"));
        assertThat(result).isEqualTo(gauge.getValue());
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void callToThrowExceptionShouldThrowException() {
        registry.getGauges()
                .get(name(GaugedService.class, "throwsException"))
                .getValue();
    }
}
