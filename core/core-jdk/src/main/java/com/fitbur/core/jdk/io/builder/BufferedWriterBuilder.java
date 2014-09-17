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

import java.io.BufferedWriter;
import java.io.Writer;
import org.glassfish.hk2.api.PerLookup;
import org.jvnet.hk2.annotations.Service;

/**
 *
 * @author Sharmarke Aden
 */
@PerLookup
@Service
public class BufferedWriterBuilder {

    public OutputBuilder output(Writer output) {
        return new OutputBuilder(output);
    }

    public class OutputBuilder {

        private final Writer output;

        private OutputBuilder(Writer output) {
            this.output = output;
        }

        public SizeBuilder size(int size) {
            return new SizeBuilder(output, size);
        }

        public BufferedWriter build() {
            return new BufferedWriter(output);
        }

    }

    public class SizeBuilder {

        private final Writer output;
        private final Integer size;

        private SizeBuilder(Writer output, int size) {
            this.output = output;
            this.size = size;
        }

        public BufferedWriter build() {
            return new BufferedWriter(output, size);
        }

    }

}
