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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import static java.nio.charset.Charset.defaultCharset;
import org.glassfish.hk2.api.PerLookup;
import org.jvnet.hk2.annotations.Service;

/**
 *
 * @author Sharmarke Aden
 */
@PerLookup
@Service
public class PrintStreamBuilder {

    public static final Boolean DEFAULT_AUTOFLUSH = false;

    public FileBuilder file(File file) {
        return new FileBuilder(file);
    }

    public FileBuilder file(String name) {
        return new FileBuilder(new File(name));
    }

    public OutputBuilder output(OutputStream output) {
        return new OutputBuilder(output);
    }

    public class OutputBuilder {

        private final OutputStream output;
        private Boolean autoflush = DEFAULT_AUTOFLUSH;

        private OutputBuilder(OutputStream output) {
            this.output = output;
        }

        public OutputBuilder autoFlush() {
            this.autoflush = true;

            return this;
        }

        public PrintStream build() {
            return new PrintStream(output, autoflush);
        }
    }

    public class FileBuilder {

        private final File file;
        private String charset = defaultCharset().name();

        private FileBuilder(File file) {
            this.file = file;
        }

        public FileBuilder charset(Charset charset) {
            this.charset = charset.name();

            return this;
        }

        public FileBuilder charset(String charset) {
            this.charset = charset;

            return this;
        }

        public PrintStream build() throws FileNotFoundException,
                UnsupportedEncodingException {
            return new PrintStream(file, charset);
        }
    }

}
