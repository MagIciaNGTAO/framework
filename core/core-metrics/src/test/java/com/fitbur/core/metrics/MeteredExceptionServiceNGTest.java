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

import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import static com.codahale.metrics.MetricRegistry.name;
import static com.codahale.metrics.MetricRegistry.name;
import com.fitbur.core.metrics.fixture.CustomException;
import com.fitbur.core.metrics.fixture.MeteredExceptionConstructorService;
import com.fitbur.core.metrics.fixture.MeteredExceptionMethodService;
import com.fitbur.core.metrics.fixture.MeteredExceptionService;
import com.fitbur.core.metrics.fixture.MeteredService;
import javax.inject.Inject;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import org.glassfish.hk2.api.MultiException;
import org.glassfish.hk2.api.ServiceLocator;
import org.jvnet.testing.hk2testng.HK2;
import org.testng.annotations.Test;

/**
 *
 * @author Sharmarke Aden
 */
@HK2
public class MeteredExceptionServiceNGTest {

    @Inject
    ServiceLocator locator;
    @Inject
    MetricRegistry registry;

    @Test
    public void callToConstructorShouldNotBeMetered() {
        locator.getService(MeteredExceptionService.class);
        Meter meter = registry.meter(name(MeteredExceptionService.class));

        assertThat(meter).isNotNull();
        assertThat(meter.getCount()).isEqualTo(0);
    }

    @Test
    public void callToMethodShouldNotBeMetered() {
        locator.getService(MeteredExceptionService.class).greet();
        Meter meter = registry.meter(name(MeteredExceptionService.class));

        assertThat(meter).isNotNull();
        assertThat(meter.getCount()).isEqualTo(0);
    }

    @Test
    public void callToExceptionThrowingConstructorShouldBeMetered() {
        Meter meter = null;

        try {
            locator.getService(MeteredExceptionConstructorService.class);
            fail("Exception not thrown");
        }
        catch (MultiException e) {
            meter = registry.meter(name(MeteredExceptionConstructorService.class));
        }

        assertThat(meter).isNotNull();
        assertThat(meter.getCount()).isEqualTo(1);
    }

    @Test
    public void callToExceptionThrowingMethodShouldBeMetered() {
        Meter meter = null;

        try {
            locator.getService(MeteredExceptionMethodService.class).throwException();
            fail("Exception not thrown");
        }
        catch (CustomException e) {
            meter = registry.meter(name(MeteredExceptionMethodService.class, "throwException"));
        }

        assertThat(meter).isNotNull();
        assertThat(meter.getCount()).isEqualTo(1);
    }

    @Test
    public void callToIllegalArgumentExceptionThrowingMethodShouldBeMetered() {
        Meter meter = null;

        try {
            locator.getService(MeteredExceptionMethodService.class).throwIllegalArgumentException();
            fail("Exception not thrown");
        }
        catch (IllegalArgumentException e) {
            meter = registry.meter(name(MeteredExceptionMethodService.class, "throwIllegalArgumentException"));
        }

        assertThat(meter).isNotNull();
        assertThat(meter.getCount()).isEqualTo(1);
    }

    @Test
    public void callToNPEThrowingMethodShouldNotBeMetered() {
        Meter meter = null;

        try {
            locator.getService(MeteredExceptionMethodService.class).throwNPE();
            fail("Exception not thrown");
        }
        catch (NullPointerException e) {
            meter = registry.meter(name(MeteredExceptionMethodService.class, "throwNPE"));
        }

        assertThat(meter).isNotNull();
        assertThat(meter.getCount()).isEqualTo(0);
    }

}
