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

import java.io.LineNumberReader;
import java.io.Reader;
import org.glassfish.hk2.api.PerLookup;
import org.jvnet.hk2.annotations.Service;

/**
 *
 * @author Sharmarke Aden
 */
@PerLookup
@Service
public class LineNumberReaderBuilder {

    public InputBuilder input(Reader input) {
        return new InputBuilder(input);
    }

    public class InputBuilder {

        private final Reader input;

        private InputBuilder(Reader input) {
            this.input = input;
        }

        public SizeBuilder size(int size) {
            return new SizeBuilder(input, size);
        }

        public LineNumberReader build() {
            return new LineNumberReader(input);
        }

    }

    public class SizeBuilder {

        private final Reader input;
        private final Integer size;

        public SizeBuilder(Reader input, Integer size) {
            this.input = input;
            this.size = size;
        }

        public LineNumberReader build() {
            return new LineNumberReader(input, size);
        }
    }

}
