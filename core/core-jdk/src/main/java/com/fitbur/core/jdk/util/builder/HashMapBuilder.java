/*
 * Copyright 2014 Sharmarke Aden (saden1).
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
package com.fitbur.core.jdk.util.builder;

import java.util.HashMap;
import java.util.Map;
import org.glassfish.hk2.api.PerLookup;
import org.jvnet.hk2.annotations.Service;

/**
 *
 * @author Sharmarke Aden
 */
@PerLookup
@Service
public class HashMapBuilder {

    public static final Float DEFAULT_FACTOR = 0.75f;

    public EntriesBuilder entries(Map entries) {
        return new EntriesBuilder(entries);
    }

    public CapacityBuilder capacity(int capacity) {
        return new CapacityBuilder(capacity);
    }

    public HashMap build() {
        return new HashMap();
    }

    public class EntriesBuilder {

        private final Map entries;

        private EntriesBuilder(Map entries) {
            this.entries = entries;
        }

        public HashMap build() {
            return new HashMap(entries);
        }

    }

    public class CapacityBuilder {

        private final int capacity;
        private Float factor = DEFAULT_FACTOR;

        private CapacityBuilder(int capacity) {
            this.capacity = capacity;
        }

        public CapacityBuilder factor(float factor) {
            this.factor = factor;

            return this;
        }

        public HashMap build() {
            return new HashMap(capacity, factor);
        }
    }
}
