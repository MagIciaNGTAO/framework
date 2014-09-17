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
import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;
import org.glassfish.hk2.api.PerLookup;
import org.jvnet.hk2.annotations.Service;

/**
 *
 * @author Sharmarke Aden
 */
@PerLookup
@Service
public class PriorityBlockingQueueBuilder {

    public CapacityBuilder capacity(int capacity) {
        return new CapacityBuilder(capacity);
    }

    public PriorityBlockingQueue build() {
        return new PriorityBlockingQueue();
    }

    public class ElementsBuilder {

        private final Collection elements;

        private ElementsBuilder(Collection elements) {
            this.elements = elements;
        }

        public PriorityBlockingQueue build() {
            return new PriorityBlockingQueue(elements);
        }

    }

    public class CapacityBuilder {

        private final Integer capacity;
        private Comparator comparator;

        private CapacityBuilder(Integer capacity) {
            this.capacity = capacity;
        }

        public CapacityBuilder comparator(Comparator comparator) {
            this.comparator = comparator;

            return this;
        }

        public PriorityBlockingQueue build() {
            return new PriorityBlockingQueue(capacity, comparator);
        }

    }

}
