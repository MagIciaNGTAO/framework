/*
 * Copyright 2014 saden.
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
package com.fitbur.core.jdk.text;

import java.text.Annotation;
import org.glassfish.hk2.api.PerLookup;
import org.jvnet.hk2.annotations.Service;

/**
 *
 * @author saden
 */
@PerLookup
@Service
public class AnnotationBuilder {

    public ValueBuilder value(Object value) {
        return new ValueBuilder(value);
    }

    public class ValueBuilder {

        private final Object value;

        private ValueBuilder(Object value) {
            this.value = value;
        }

        public Annotation build() {
            return new Annotation(value);
        }
    }

}
