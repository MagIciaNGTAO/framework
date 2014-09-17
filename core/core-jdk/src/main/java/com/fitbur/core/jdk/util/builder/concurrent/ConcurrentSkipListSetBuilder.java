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
package com.fitbur.core.jdk.util.builder.concurrent;

import java.util.Collection;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.concurrent.ConcurrentSkipListSet;
import org.glassfish.hk2.api.PerLookup;
import org.jvnet.hk2.annotations.Service;

/**
 *
 * @author Sharmarke Aden
 */
@PerLookup
@Service
public class ConcurrentSkipListSetBuilder {

    public ElementsBuilder elements(Collection elements) {
        return new ElementsBuilder(elements);
    }

    public SortedBuilder elements(SortedSet elements) {
        return new SortedBuilder(elements);
    }

    public ComparatorBuilder comparator(Comparator comparator) {
        return new ComparatorBuilder(comparator);
    }

    public ConcurrentSkipListSet build() {
        return new ConcurrentSkipListSet();
    }

    @PerLookup
    @Service
    public class ElementsBuilder {

        private final Collection elements;

        private ElementsBuilder(Collection elements) {
            this.elements = elements;
        }

        public ConcurrentSkipListSet build() {
            return new ConcurrentSkipListSet(elements);
        }

    }

    @PerLookup
    @Service
    public class SortedBuilder {

        private final SortedSet elements;

        private SortedBuilder(SortedSet elements) {
            this.elements = elements;
        }

        public ConcurrentSkipListSet build() {
            return new ConcurrentSkipListSet(elements);
        }

    }

    @PerLookup
    @Service
    public class ComparatorBuilder {

        private final Comparator comparator;

        private ComparatorBuilder(Comparator comparator) {
            this.comparator = comparator;
        }

        public ConcurrentSkipListSet build() {
            return new ConcurrentSkipListSet(comparator);
        }

    }
}
