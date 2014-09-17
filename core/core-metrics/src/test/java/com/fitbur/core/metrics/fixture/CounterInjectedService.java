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

import com.codahale.metrics.Counter;
import com.fitbur.core.metrics.Metric;
import javax.inject.Inject;
import org.jvnet.hk2.annotations.Service;

/**
 *
 * @author Sharmarke Aden
 */
@Service
public class CounterInjectedService {

    private final Counter constructor;
    private final Counter customConstructor;

    private Counter method;
    @Inject
    Counter field;
    @Metric("customField")
    @Inject
    Counter customField;

    @Inject
    CounterInjectedService(Counter constructor, @Metric Counter customConstructor) {
        this.constructor = constructor;
        this.customConstructor = customConstructor;
    }

    @Inject
    public void setMethod(@Metric("namedCounter") Counter counter) {
        this.method = counter;
    }

    public Counter getField() {
        return field;
    }

    public Counter getMethod() {
        return method;
    }

    public Counter getConstructor() {
        return constructor;
    }

    public Counter getCustomField() {
        return customField;
    }

    public Counter getCustomConstructor() {
        return customConstructor;
    }

}
