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
package com.fitbur.core.jdk.util.builder.concurrent;

import java.util.Collection;
import java.util.concurrent.LinkedBlockingQueue;
import org.glassfish.hk2.api.PerLookup;
import org.jvnet.hk2.annotations.Service;

/**
 *
 * @author Sharmarke Aden
 */
@PerLookup
@Service
public class LinkedBlockingQueueBuilder {

    public ElementsBuilder elements(Collection elements) {
        return new ElementsBuilder(elements);
    }

    public CapacityBuilder capacity(int capacity) {
        return new CapacityBuilder(capacity);
    }

    public LinkedBlockingQueue build() {
        return new LinkedBlockingQueue();
    }

    public class ElementsBuilder {

        private final Collection elements;

        private ElementsBuilder(Collection elements) {
            this.elements = elements;
        }

        public LinkedBlockingQueue build() {
            return new LinkedBlockingQueue(elements);
        }

    }

    public class CapacityBuilder {

        private final int capacity;

        private CapacityBuilder(int capacity) {
            this.capacity = capacity;
        }

        public LinkedBlockingQueue build() {
            return new LinkedBlockingQueue(capacity);
        }
    }

}
