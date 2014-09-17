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

import com.fitbur.core.metrics.fixture.HealthyService;
import com.fitbur.core.metrics.fixture.UnhealthyService;
import com.fitbur.core.metrics.heath.HealthCheck;
import com.fitbur.core.metrics.heath.HealthStatus;
import javax.inject.Inject;
import static org.assertj.core.api.Assertions.assertThat;
import org.glassfish.hk2.api.IterableProvider;
import org.jvnet.testing.hk2testng.HK2;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author saden
 */
@HK2
public class HealthCheckNGTest {

    @Inject
    IterableProvider<HealthCheck> healthAudits;
    @Inject
    HealthyService healthyService;
    @Inject
    UnhealthyService unhealthyService;

    @BeforeClass
    void init() {
        assertThat(healthyService).isNotNull();
        assertThat(unhealthyService).isNotNull();
        assertThat(healthAudits).isNotNull()
                .hasSize(2);
    }

    @Test
    public void assertHealthyService() {
        HealthStatus result = healthyService.get();
        assertThat(result).isNotNull();
        assertThat(result.isHealthy()).isTrue();
        assertThat(result.getMessage().get()).isEqualTo("healthy");
        assertThat(result.getCause().isPresent()).isFalse();
    }

    @Test
    public void assertUnhealthyService() {
        HealthStatus result = unhealthyService.get();
        assertThat(result).isNotNull();
        assertThat(result.isHealthy()).isFalse();
        assertThat(result.getMessage().get()).isEqualTo("unhealthy");
        assertThat(result.getCause().get()).isInstanceOf(IllegalStateException.class);
    }
}
