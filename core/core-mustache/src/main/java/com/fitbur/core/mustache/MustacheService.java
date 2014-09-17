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
import java.io.StringWriter;
import java.io.Writer;
import javax.inject.Inject;
import org.jvnet.hk2.annotations.Service;

/**
 *
 * @author saden
 */
@Service
public class MustacheService {

    private final MustacheFactory factory;

    @Inject
    MustacheService(MustacheFactory factory) {
        this.factory = factory;
    }

    public Mustache newMustache(String resource) {
        return factory.compile(resource);
    }

    public Mustache newMustache(String name, String template) {
        return factory.compile(new StringReader(template), name);
    }

    public Mustache newMustache(String name, Reader template) {
        return factory.compile(template, name);
    }

    public void expand(Mustache mustache, Writer writer, Object... scopes) {
        mustache.execute(writer, scopes);
    }

    public String expand(Mustache mustache, Object... scopes) {
        StringWriter writer = new StringWriter();
        mustache.execute(writer, scopes);
        return writer.toString();
    }

    public String expand(String name, String template, Object... scopes) {
        Mustache mustache = newMustache(name, template);

        return expand(mustache, scopes);
    }

}
