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
package com.fitbur.core.metrics.heath;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import javax.inject.Inject;
import org.glassfish.hk2.api.IterableProvider;
import org.jvnet.hk2.annotations.Service;

/**
 *
 * @author saden
 */
@Service
public class HealthChecker {

    private final IterableProvider<HealthCheck> healthChekers;

    @Inject
    HealthChecker(IterableProvider<HealthCheck> healthChekers) {
        this.healthChekers = healthChekers;
    }

    public List<HealthStatus> check() {
        return StreamSupport.stream(healthChekers.spliterator(), true)
                .map(p -> p.get())
                .collect(Collectors.toList());
    }

    public Optional<HealthStatus> check(String name) {
        return StreamSupport.stream(healthChekers.named(name).spliterator(), true)
                .findFirst()
                .map(p -> p.get());

    }

}
