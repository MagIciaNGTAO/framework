/*
 * Copyright 2013 saden.
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
package com.fitbur.core.jdk.math;

import java.math.MathContext;
import java.math.RoundingMode;
import org.glassfish.hk2.api.PerLookup;
import org.jvnet.hk2.annotations.Service;

/**
 *
 * @author Sharmarke Aden
 */
@PerLookup
@Service
public class MathContextBuilder {

    public static final RoundingMode DEFAULT_MODE = RoundingMode.HALF_UP;

    public StringBuilder value(String value) {
        return new StringBuilder(value);
    }

    public PercisionBuilder percision(int precision) {
        return new PercisionBuilder(precision);
    }

    public class StringBuilder {

        private final String value;

        private StringBuilder(String value) {
            this.value = value;
        }

        public MathContext build() {
            return new MathContext(value);
        }
    }

    public class PercisionBuilder {

        private final Integer precision;
        private RoundingMode mode = DEFAULT_MODE;

        private PercisionBuilder(Integer precision) {
            this.precision = precision;
        }

        public PercisionBuilder mode(RoundingMode mode) {
            this.mode = mode;

            return this;
        }

        public MathContext build() {
            return new MathContext(precision, mode);
        }

    }
}
