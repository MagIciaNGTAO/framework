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

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import org.glassfish.hk2.api.PerLookup;
import org.jvnet.hk2.annotations.Service;

/**
 *
 * @author Sharmarke Aden
 */
@PerLookup
@Service
public class PipedReaderBuilder {

    public static final Integer DEFAULT_SIZE = 1_024;

    public OutputBuilder connect(PipedWriter output) {
        return new OutputBuilder(output);
    }

    public SizeBuilder size(int size) {
        return new SizeBuilder(size);
    }

    public PipedReader build() throws IOException {
        return new PipedReader();
    }

    public class SizeBuilder {

        private final Integer size;
        private PipedWriter output;

        private SizeBuilder(int size) {
            this.size = size;
        }

        public SizeBuilder connect(PipedWriter output) {
            this.output = output;

            return this;
        }

        public PipedReader build() throws IOException {
            if (output == null) {
                return new PipedReader(size);
            }

            return new PipedReader(output, size);
        }

    }

    public class OutputBuilder {

        private final PipedWriter output;
        private Integer size = DEFAULT_SIZE;

        private OutputBuilder(PipedWriter output) {
            this.output = output;
        }

        public OutputBuilder size(int size) {
            this.size = size;

            return this;
        }

        public PipedReader build() throws IOException {
            return new PipedReader(output, size);
        }

    }

}
