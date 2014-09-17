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

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import static java.nio.charset.Charset.forName;
import java.nio.charset.CharsetEncoder;
import org.glassfish.hk2.api.PerLookup;
import org.jvnet.hk2.annotations.Service;

/**
 *
 * @author Sharmarke Aden
 */
@PerLookup
@Service
public class OutputStreamWriterBuilder {

    public OutputBuilder output(OutputStream output) {
        return new OutputBuilder(output);
    }

    public class OutputBuilder {

        private final OutputStream output;

        public OutputBuilder(OutputStream output) {
            this.output = output;
        }

        public CharsetBuilder charset(String charset) {
            return new CharsetBuilder(output, forName(charset));
        }

        public CharsetBuilder charset(Charset charset) {
            return new CharsetBuilder(output, charset);
        }

        public EncoderBuilder charset(CharsetEncoder encoder) {
            return new EncoderBuilder(output, encoder);
        }

        public OutputStreamWriter build() throws Exception {
            return new OutputStreamWriter(output);
        }

    }

    public class CharsetBuilder {

        private final OutputStream output;
        private final Charset charset;

        public CharsetBuilder(OutputStream output, Charset charset) {
            this.output = output;
            this.charset = charset;
        }

        public OutputStreamWriter build() {
            return new OutputStreamWriter(output, charset);
        }
    }

    public class EncoderBuilder {

        private final OutputStream output;
        private final CharsetEncoder encoder;

        public EncoderBuilder(OutputStream output, CharsetEncoder encoder) {
            this.output = output;
            this.encoder = encoder;
        }

        public OutputStreamWriter build() {
            return new OutputStreamWriter(output, encoder);
        }
    }

}
