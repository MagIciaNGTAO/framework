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

import java.io.CharArrayReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.Charset;
import org.jvnet.hk2.annotations.Service;

/**
 *
 * @author saden
 */
@Service
public class Readables {

    public Reader toReader(char[] input) {
        return new CharArrayReader(input);
    }

    public Reader toReader(String input) {
        return new StringReader(input);
    }

    public Reader toReader(InputStream input) {
        return new InputStreamReader(input, Charset.defaultCharset());
    }

    public Reader toReader(InputStream input, Charset charset) {
        return new InputStreamReader(input, charset);
    }

    public Reader toReader(PipedWriter src) throws IOException {
        return new PipedReader(src);
    }
}
