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

import com.fitbur.core.metrics.heath.HealthStatus;
import javax.inject.Inject;
import static org.assertj.core.api.Assertions.assertThat;
import org.jvnet.testing.hk2testng.HK2;
import org.testng.annotations.Test;

/**
 *
 * @author saden
 */
@HK2
public class HealthStatusNGTest {

    @Inject
    HealthStatus.Builder sut;

    @Test
    public void assertInjection() {
        assertThat(sut).isNotNull();
    }

    @Test
    public void callToNewBuilderShouldReturnNewHealthStatusBuilder() {
        HealthStatus.Builder result1 = HealthStatus.newBuilder();
        HealthStatus.Builder result2 = HealthStatus.newBuilder();
        assertThat(result1).isNotNull();
        assertThat(result2).isNotNull();
        assertThat(result1).isNotSameAs(result2);
    }

    @Test
    public void callToBuildUnhealthyStatusWithMessageShouldReturn() {
        HealthStatus result = sut.unhealthy()
                .message("test")
                .cause(new IllegalArgumentException())
                .build();
        assertThat(result).isNotNull();
        assertThat(result.isHealthy()).isFalse();
        assertThat(result.getMessage().get()).isEqualTo("test");
        assertThat(result.getCause().get()).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void callToBuildHealthyStatusWithMessageShouldReturn() {
        HealthStatus result = sut.healthy()
                .message("test")
                .build();
        assertThat(result).isNotNull();
        assertThat(result.isHealthy()).isTrue();
        assertThat(result.getMessage().get()).isEqualTo("test");
    }

}
