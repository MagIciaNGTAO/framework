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

import java.util.Comparator;
import java.util.Map;
import java.util.SortedMap;
import java.util.concurrent.ConcurrentSkipListMap;
import org.glassfish.hk2.api.PerLookup;
import org.jvnet.hk2.annotations.Service;

/**
 *
 * @author Sharmarke Aden
 */
@PerLookup
@Service
public class ConcurrentSkipListMapBuilder {

    public EntriesBuilder entries(Map entries) {
        return new EntriesBuilder(entries);
    }

    public SortedBuilder entries(SortedMap entries) {
        return new SortedBuilder(entries);
    }

    public ComparatorBuilder comparator(Comparator comparator) {
        return new ComparatorBuilder(comparator);
    }

    public ConcurrentSkipListMap build() {
        return new ConcurrentSkipListMap();
    }

    @PerLookup
    @Service
    public class EntriesBuilder {

        private final Map entries;

        private EntriesBuilder(Map entries) {
            this.entries = entries;
        }

        public ConcurrentSkipListMap build() {
            return new ConcurrentSkipListMap(entries);
        }

    }

    @PerLookup
    @Service
    public class SortedBuilder {

        private final SortedMap entries;

        private SortedBuilder(SortedMap entries) {
            this.entries = entries;
        }

        public ConcurrentSkipListMap build() {
            return new ConcurrentSkipListMap(entries);
        }

    }

    @PerLookup
    @Service
    public class ComparatorBuilder {

        private final Comparator comparator;

        private ComparatorBuilder(Comparator comparator) {
            this.comparator = comparator;
        }

        public ConcurrentSkipListMap build() {
            return new ConcurrentSkipListMap(comparator);
        }

    }
}
