/*
 * Copyright 2014 Fitbur.
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
package com.fitbur.core.mustache;

import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import java.io.Reader;
import java.io.StringReader;
import java.util.Optional;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import javax.inject.Inject;
import org.glassfish.hk2.api.PerLookup;
import org.glassfish.hk2.api.Rank;
import org.jvnet.hk2.annotations.Service;

/**
 *
 * @author saden
 */
@Rank(1000)
@PerLookup
@Service
public class MustacheBuilder {

    private final MustacheFactory factory;

    @Inject
    MustacheBuilder(MustacheFactory factory) {
        this.factory = factory;
    }

    public ResourceBuilder resource(String name) {
        return new ResourceBuilder(name);
    }

    public class ResourceBuilder {

        private final String resource;
        private Optional<Reader> template = empty();

        ResourceBuilder(String resource) {
            this.resource = resource;
        }

        public ResourceBuilder template(String template) {
            this.template = of(new StringReader(template));

            return this;
        }

        public ResourceBuilder template(Reader template) {
            this.template = of(template);

            return this;
        }

        public Mustache build() {

            if (template.isPresent()) {
                return factory.compile(template.get(), resource);
            }

            return factory.compile(resource);
        }

    }

}
