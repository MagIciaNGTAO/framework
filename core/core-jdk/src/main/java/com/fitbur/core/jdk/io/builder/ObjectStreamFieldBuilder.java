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
package com.fitbur.core.jdk.io.builder;

import java.io.ObjectStreamField;
import org.glassfish.hk2.api.PerLookup;
import org.jvnet.hk2.annotations.Service;

/**
 *
 * @author Sharmarke Aden
 */
@PerLookup
@Service
public class ObjectStreamFieldBuilder {

    public FieldBuilder field(String name, Class<?> type) {
        return new FieldBuilder(name, type);
    }

    public class FieldBuilder {

        private final String name;
        private final Class<?> type;

        private FieldBuilder(String name, Class<?> type) {
            this.name = name;
            this.type = type;
        }

        public UnsharedBuilder unshared() {
            return new UnsharedBuilder(name, type, true);
        }

        public ObjectStreamField build() {
            return new ObjectStreamField(name, type);
        }

    }

    public class UnsharedBuilder {

        private final String name;
        private final Class<?> type;
        private final Boolean unshared;

        private UnsharedBuilder(String name, Class<?> type, Boolean unshared) {
            this.name = name;
            this.type = type;
            this.unshared = unshared;
        }

        public ObjectStreamField build() {
            return new ObjectStreamField(name, type, unshared);
        }
    }

}
