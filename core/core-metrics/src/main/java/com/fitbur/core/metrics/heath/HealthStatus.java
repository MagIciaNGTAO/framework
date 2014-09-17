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
package com.fitbur.core.metrics.heath;

import java.util.Optional;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import org.glassfish.hk2.api.PerLookup;
import org.jvnet.hk2.annotations.Service;

/**
 *
 * @author Sharmarke Aden
 */
public class HealthStatus {

    public static Builder newBuilder() {
        return new Builder();
    }

    private final boolean healthy;
    private final Optional<String> message;
    private final Optional<Throwable> cause;

    HealthStatus(boolean healthy, Optional<String> message, Optional<Throwable> cause) {
        this.healthy = healthy;
        this.message = message;
        this.cause = cause;
    }

    public boolean isHealthy() {
        return healthy;
    }

    public Optional<String> getMessage() {
        return message;
    }

    public Optional<Throwable> getCause() {
        return cause;
    }

    @PerLookup
    @Service
    public static class Builder {

        public HealthyBuilder healthy() {
            return new HealthyBuilder(true);
        }

        public UnhealthyBuilder unhealthy() {
            return new UnhealthyBuilder(false);
        }

        public class HealthyBuilder {

            private final boolean healthy;
            private Optional<String> message = empty();

            HealthyBuilder(boolean healthy) {
                this.healthy = healthy;
            }

            public HealthyBuilder message(String message) {
                this.message = of(message);

                return this;
            }

            public HealthStatus build() {
                return new HealthStatus(healthy, message, empty());
            }

        }

        public class UnhealthyBuilder {

            private final boolean healthy;
            private Optional<String> message = empty();
            private Optional<Throwable> cause = empty();

            UnhealthyBuilder(boolean healthy) {
                this.healthy = healthy;
            }

            public UnhealthyBuilder message(String message) {
                this.message = of(message);

                return this;
            }

            public UnhealthyBuilder cause(Throwable cause) {
                this.cause = of(cause);

                return this;
            }

            public HealthStatus build() {
                return new HealthStatus(healthy, message, cause);
            }

        }

    }
}
