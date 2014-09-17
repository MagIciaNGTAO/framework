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

import java.net.URI;
import java.net.URISyntaxException;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;
import org.glassfish.hk2.api.PerLookup;
import org.jvnet.hk2.annotations.Service;

/**
 *
 * @author Sharmarke Aden
 */
@PerLookup
@Service
public class URIBuilder {

    public static final Integer DEFAULT_PORT = -1;

    public StringBuilder uri(String uri) {
        return new StringBuilder(uri);
    }

    public SchemaBuilder schema(String scheme) {
        return new SchemaBuilder(scheme);
    }

    public class StringBuilder {

        private final String uri;

        private StringBuilder(String uri) {
            this.uri = uri;
        }

        public URI build() throws URISyntaxException {
            return new URI(uri);
        }
    }

    public class SpecificBuilder {

        private final String scheme;
        private String part;
        private String fragment;

        private SpecificBuilder(String scheme) {
            this.scheme = scheme;
        }

        @EnsuresNonNull("part")
        public SpecificBuilder part(String part) {
            this.part = part;

            return this;
        }

        @EnsuresNonNull("fragment")
        public SpecificBuilder fragment(String fragment) {
            this.fragment = fragment;

            return this;
        }

        @RequiresNonNull({"part", "fragment"})
        public URI build() throws URISyntaxException {
            return new URI(scheme, part, fragment);
        }

    }

    public class ComponentBuilder {

        private final String scheme;

        private String userInfo;
        private String host;
        private int port = DEFAULT_PORT;
        private String path;
        private String query;
        private String fragment;

        private ComponentBuilder(String scheme) {
            this.scheme = scheme;
        }

        public ComponentBuilder userInfo(String userInfo) {
            this.userInfo = userInfo;

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

        @EnsuresNonNull("path")
        public ComponentBuilder path(String path) {
            this.path = path;

            return this;
        }

        public ComponentBuilder query(String query) {
            this.query = query;

            return this;
        }

        @EnsuresNonNull("fragment")
        public ComponentBuilder fragment(String fragment) {
            this.fragment = fragment;

            return this;
        }

        @RequiresNonNull({"host", "path", "fragment"})
        public URI build() throws URISyntaxException {
            return new URI(scheme, userInfo, host, port, path, query, fragment);

        }

    }

    public class AuthorityBuilder {

        private final String scheme;
        private String authority;
        private String path;
        private String query;
        private String fragment;

        private AuthorityBuilder(String scheme) {
            this.scheme = scheme;
        }

        @EnsuresNonNull("authority")
        public AuthorityBuilder authority(String authority) {
            this.authority = authority;

            return this;
        }

        public AuthorityBuilder path(String path) {
            this.path = path;

            return this;
        }

        public AuthorityBuilder query(String query) {
            this.query = query;

            return this;
        }

        public AuthorityBuilder fragment(String fragment) {
            this.fragment = fragment;

            return this;
        }

        @RequiresNonNull({"authority"})
        public URI build() throws URISyntaxException {
            return new URI(scheme, authority, path, query, fragment);
        }
    }

    public class SchemaBuilder {

        private final String scheme;

        private SchemaBuilder(String scheme) {
            this.scheme = scheme;
        }

        public SpecificBuilder sepecific() {
            return new SpecificBuilder(scheme);
        }

        public ComponentBuilder component() {
            return new ComponentBuilder(scheme);
        }

        public AuthorityBuilder authority() {
            return new AuthorityBuilder(scheme);
        }
    }

}
