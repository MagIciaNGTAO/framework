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
import java.net.URI;
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
public class FileBuilder {

    private String path;

    public NameBuilder name(String name) {
        return new NameBuilder(name);
    }

    public UriBuilder uri(URI uri) {
        return new UriBuilder(uri);
    }

    public PathBuilder path(String parent, String child) {
        return new PathBuilder(new File(parent), child);
    }

    public PathBuilder path(File parent, String child) {
        return new PathBuilder(parent, child);
    }

    @EnsuresNonNull("path")
    public FileBuilder path(String path) {
        this.path = path;

        return this;
    }

    @RequiresNonNull("path")
    public File build() {
        return new File(path);
    }

    public class PathBuilder {

        private final File file;
        private final String child;

        private PathBuilder(File file, String child) {
            this.file = file;
            this.child = child;
        }

        public File build() {
            return new File(file, child);
        }
    }

    public class NameBuilder {

        private final String name;

        private NameBuilder(String name) {
            this.name = name;
        }

        public File build() {
            return new File(name);
        }

    }

    public class UriBuilder {

        private final URI uri;

        private UriBuilder(URI uri) {
            this.uri = uri;
        }

        public File build() {
            return new File(uri);
        }

    }

}
