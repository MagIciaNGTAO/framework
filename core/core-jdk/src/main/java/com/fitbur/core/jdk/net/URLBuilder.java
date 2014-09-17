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
package com.fitbur.core.jdk.net;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLStreamHandler;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;
import org.glassfish.hk2.api.PerLookup;
import org.jvnet.hk2.annotations.Service;

/**
 *
 * @author Sharmarke Aden
 */
@PerLookup
@Service
public class URLBuilder {

    public static final Integer DEFAULT_PORT = -1;

    public StringBuilder uri(String uri) {
        return new StringBuilder(uri);
    }

    public ComponentBuilder component() {
        return new ComponentBuilder();
    }

    public class StringBuilder {

        private final String uri;
        @MonotonicNonNull
        private URL context;
        @MonotonicNonNull
        private URLStreamHandler handler;

        private StringBuilder(String uri) {
            this.uri = uri;
        }

        public StringBuilder context(URL context) {
            this.context = context;

            return this;
        }

        public StringBuilder handler(URLStreamHandler handler) {
            this.handler = handler;

            return this;
        }

        public URL build() throws MalformedURLException {
            return new URL(context, uri, handler);
        }
    }

    public class ComponentBuilder {

        private String protocol;
        @MonotonicNonNull
        private String host;
        @MonotonicNonNull
        private int port = DEFAULT_PORT;
        @MonotonicNonNull
        private String file;
        @MonotonicNonNull
        private URLStreamHandler handler;

        @EnsuresNonNull("protocol")
        public ComponentBuilder protocol(String protocol) {
            this.protocol = protocol;

            return this;
        }

        @EnsuresNonNull("host")
        public ComponentBuilder host(String host) {
            this.host = host;

            return this;
        }

        public ComponentBuilder port(int port) {
            this.port = port;

            return this;
        }

        @EnsuresNonNull("file")
        public ComponentBuilder file(String file) {
            this.file = file;

            return this;
        }

        public ComponentBuilder handler(URLStreamHandler handler) {
            this.handler = handler;

            return this;
        }

        @RequiresNonNull({"protocol", "host", "file"})
        public URL build() throws MalformedURLException {
            return new URL(protocol, host, port, file, handler);
        }

    }

}
