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
import java.util.concurrent.CopyOnWriteArrayList;
import org.glassfish.hk2.api.PerLookup;
import org.jvnet.hk2.annotations.Service;

/**
 *
 * @author Sharmarke Aden
 */
@PerLookup
@Service
public class CopyOnWriteArrayListBuilder {

    public ElementsBuilder elements(Collection elements) {
        return new ElementsBuilder(elements);
    }

    public ArrayBuilder elements(Object[] elements) {
        return new ArrayBuilder(elements);
    }

    public CopyOnWriteArrayList build() {
        return new CopyOnWriteArrayList();
    }

    public class ArrayBuilder {

        private final Object[] elements;

        private ArrayBuilder(Object[] elements) {
            this.elements = elements;
        }

        public CopyOnWriteArrayList build() {
            return new CopyOnWriteArrayList(elements);
        }
    }

    public class ElementsBuilder {

        private final Collection elements;

        private ElementsBuilder(Collection elements) {
            this.elements = elements;
        }

        public CopyOnWriteArrayList build() {
            return new CopyOnWriteArrayList(elements);
        }
    }

}
