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
package com.fitbur.core.metrics;

import com.fitbur.core.metrics.heath.HealthChecker;
import com.fitbur.core.metrics.heath.HealthStatus;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import static org.assertj.core.api.Assertions.assertThat;
import org.jvnet.testing.hk2testng.HK2;
import org.testng.annotations.Test;

/**
 *
 * @author saden
 */
@HK2
public class HealthCheckerNGTest {

    @Inject
    HealthChecker sut;

    @Test
    public void callToCheckShouldReturnHealthStatusList() {
        List<HealthStatus> result = sut.check();
        assertThat(result).hasSize(2);
    }

    @Test
    public void givenInvalidNameCheckShouldReturnHealthStatus() {
        Optional<HealthStatus> result = sut.check("test");
        assertThat(result).isNotNull();
        assertThat(result.isPresent()).isFalse();
    }

    @Test
    public void givenValidNameCheckShouldReturnHealthStatus() {
        Optional<HealthStatus> result = sut.check("healthy");
        assertThat(result).isNotNull();
        assertThat(result.isPresent()).isTrue();
    }

}
