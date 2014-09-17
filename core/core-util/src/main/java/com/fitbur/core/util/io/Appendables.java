/*
 * Copyright 2014 Fitbur, Inc..
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
package com.fitbur.core.util.io;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.io.PipedWriter;
import java.io.Reader;
import java.io.Writer;
import java.io.StringWriter;
import java.nio.charset.Charset;
import org.jvnet.hk2.annotations.Service;

/**
 *
 * @author saden
 */
@Service
public class Appendables {

    public CharArrayWriter newCharArrayWriter() {
        return new CharArrayWriter();
    }

    public StringWriter newStringWriter() {
        return new StringWriter();
    }

    public Writer toWriter(OutputStream input) {
        return new OutputStreamWriter(input, Charset.defaultCharset());
    }

    public Writer toWriter(OutputStream input, Charset charset) {
        return new OutputStreamWriter(input, charset);
    }

    public Writer toWriter(PipedReader src) throws IOException {
        return new PipedWriter(src);
    }
}
