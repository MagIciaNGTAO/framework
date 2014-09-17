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

import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandlerFactory;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.glassfish.hk2.api.PerLookup;
import org.jvnet.hk2.annotations.Service;

/**
 *
 * @author Sharmarke Aden
 */
@PerLookup
@Service
public class URLClassLoaderBuilder {

    public UrlsBuilder urls(URL[] urls) {
        return new UrlsBuilder(urls);
    }

    public class UrlsBuilder {

        private final URL[] urls;

        private UrlsBuilder(URL[] urls) {
            this.urls = urls;
        }

        public LoaderBuilder loader(ClassLoader loader) {
            return new LoaderBuilder(urls, loader);
        }

        public URLClassLoader build() {
            return new URLClassLoader(urls);
        }

    }

    public class LoaderBuilder {

        private final URL[] urls;
        private final ClassLoader loader;
        @MonotonicNonNull
        private URLStreamHandlerFactory factory;

        private LoaderBuilder(URL[] urls, ClassLoader loader) {
            this.urls = urls;
            this.loader = loader;
        }

        public LoaderBuilder factory(URLStreamHandlerFactory factory) {
            this.factory = factory;

            return this;
        }

        public URLClassLoader build() {
            if (factory == null) {
                return new URLClassLoader(urls, loader);
            }

            return new URLClassLoader(urls, loader, factory);
        }
    }

}
