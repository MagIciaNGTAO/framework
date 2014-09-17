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

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import static java.nio.charset.Charset.forName;
import java.nio.charset.CharsetDecoder;
import org.glassfish.hk2.api.PerLookup;
import org.jvnet.hk2.annotations.Service;

/**
 *
 * @author Sharmarke Aden
 */
@PerLookup
@Service
public class InputStreamReaderBuilder {

    public InputBuilder input(InputStream input) {
        return new InputBuilder(input);
    }

    public class InputBuilder {

        private final InputStream input;

        public InputBuilder(InputStream input) {
            this.input = input;
        }

        public CharsetBuilder charset(String charset) {
            return new CharsetBuilder(input, forName(charset));
        }

        public CharsetBuilder charset(Charset charset) {
            return new CharsetBuilder(input, charset);
        }

        public DecoderBuilder charset(CharsetDecoder decoder) {
            return new DecoderBuilder(input, decoder);
        }

        public InputStreamReader build() throws Exception {
            return new InputStreamReader(input);
        }

    }

    public class CharsetBuilder {

        private final InputStream input;
        private final Charset charset;

        public CharsetBuilder(InputStream input, Charset charset) {
            this.input = input;
            this.charset = charset;
        }

        public InputStreamReader build() {
            return new InputStreamReader(input, charset);
        }
    }

    public class DecoderBuilder {

        private final InputStream input;
        private final CharsetDecoder decoder;

        public DecoderBuilder(InputStream input, CharsetDecoder decoder) {
            this.input = input;
            this.decoder = decoder;
        }

        public InputStreamReader build() {
            return new InputStreamReader(input, decoder);
        }
    }

}
