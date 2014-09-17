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

import java.io.CharArrayReader;
import org.glassfish.hk2.api.PerLookup;
import org.jvnet.hk2.annotations.Service;

/**
 *
 * @author Sharmarke Aden
 */
@PerLookup
@Service
public class CharArrayReaderBuilder {

    public BufferBuilder buffer(char[] buffer) {
        return new BufferBuilder(buffer);
    }

    public BufferBuilder buffer(String buffer) {
        return new BufferBuilder(buffer.toCharArray());
    }

    public class BufferBuilder {

        private final char[] buffer;

        private BufferBuilder(char[] buffer) {
            this.buffer = buffer;
        }

        public OffsetBuilder offset(int offset) {
            return new OffsetBuilder(buffer, offset);
        }

        public CharArrayReader build() {
            return new CharArrayReader(buffer);
        }

    }

    public class OffsetBuilder {

        private final char[] buffer;
        private final int offset;
        private Integer length;

        private OffsetBuilder(char[] buffer, int offset) {
            this.buffer = buffer;
            this.offset = offset;
        }

        public OffsetBuilder length(int length) {
            this.length = length;

            return this;

        }

        public CharArrayReader build() {
            if (length == null) {
                length = buffer.length;
            }

            return new CharArrayReader(buffer, offset, length);
        }

    }

}
