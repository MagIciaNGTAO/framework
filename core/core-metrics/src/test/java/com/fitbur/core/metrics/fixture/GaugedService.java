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

import com.fitbur.core.metrics.Gauge;
import org.jvnet.hk2.annotations.Service;

/**
 *
 * @author Sharmarke Aden
 */
@Service
public class GaugedService {

    @Gauge
    public String gaugedMethod() {
        return "gaugedMethod";
    }

    @Gauge(absolute = true)
    public String absoluteGaugedMethod() {
        return "absoluteGaugedMethod";
    }

    @Gauge(name = "namedGaugedMethod")
    public String namedGaugedMethod() {
        return "namedGaugedMethod";
    }

    @Gauge
    public void throwsException() throws IllegalAccessException {
        throw new IllegalAccessException("exception");
    }

}
