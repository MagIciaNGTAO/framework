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

import com.codahale.metrics.Histogram;
import com.fitbur.core.metrics.Metric;
import javax.inject.Inject;
import org.jvnet.hk2.annotations.Service;

/**
 *
 * @author Sharmarke Aden
 */
@Service
public class HistogramInjectedService {

    private final Histogram constructor;
    private final Histogram customConstructor;

    private Histogram method;
    @Inject
    Histogram field;
    @Metric("customField")
    @Inject
    Histogram customField;

    @Inject
    HistogramInjectedService(Histogram constructor, @Metric Histogram customConstructor) {
        this.constructor = constructor;
        this.customConstructor = customConstructor;
    }

    @Inject
    public void setMethod(@Metric("namedHistogram") Histogram meter) {
        this.method = meter;
    }

    public Histogram getField() {
        return field;
    }

    public Histogram getMethod() {
        return method;
    }

    public Histogram getConstructor() {
        return constructor;
    }

    public Histogram getCustomField() {
        return customField;
    }

    public Histogram getCustomConstructor() {
        return customConstructor;
    }

}
