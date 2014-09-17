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
package com.fitbur.core.metrics.fixture;

import com.codahale.metrics.Gauge;
import com.fitbur.core.metrics.Metric;
import javax.inject.Inject;
import org.jvnet.hk2.annotations.Service;

/**
 *
 * @author Sharmarke Aden
 */
@Service
public class GaugeInjectedService {

    private final GaugedService service;
    private final Gauge constructor;

    @Inject
    GaugeInjectedService(GaugedService service,
            @Metric(value = "absoluteGaugedMethod", absolute = true) Gauge constructor) {
        this.service = service;
        this.constructor = constructor;
    }

    public Gauge getConstructor() {
        return constructor;
    }

}
