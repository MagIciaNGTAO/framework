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
package com.fitbur.core.core.el.mvel;

import com.fitbur.core.el.api.TemplateService;
import com.google.common.cache.Cache;
import com.google.common.io.CharStreams;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Map;
import java.util.Objects;
import static java.util.Objects.requireNonNull;
import java.util.concurrent.ExecutionException;
import javax.inject.Inject;
import org.apache.commons.io.output.WriterOutputStream;
import org.jvnet.hk2.annotations.Service;
import org.mvel2.templates.CompiledTemplate;
import org.mvel2.templates.TemplateCompiler;
import org.mvel2.templates.TemplateRuntime;

/**
 *
 * @author saden
 */
@Service
public class MvelTemplateService implements TemplateService {

    private final Cache<String, CompiledTemplate> cache;

    @Inject
    MvelTemplateService(Cache<String, CompiledTemplate> cache) {
        this.cache = cache;
    }

    @Override
    public void eval(String name, Reader template, Writer output, Object context) {
        try {
            validate(name, template, output, context);
            CompiledTemplate compiled = compile(name, template);
            WriterOutputStream stream = new WriterOutputStream(output);
            TemplateRuntime.execute(compiled, context, stream);
            stream.flush();
        } catch (IOException e) {
        }
    }

    @Override
    public void eval(String name, Reader template, Writer output, Map<String, Object> context) {
        validate(name, template, output, context);
        CompiledTemplate compiled = compile(name, template);
        TemplateRuntime.execute(compiled, context, new WriterOutputStream(output));
    }

    private void validate(String name, Reader template, Writer output, Object context) {
        requireNonNull(name, "name must not be null");
        requireNonNull(template, "template must not be null");
        requireNonNull(output, "output must not be null");
        requireNonNull(context, "context must not be null");
    }

    private CompiledTemplate compile(String name, Reader template) {
        CompiledTemplate comiledTemplate = null;

        try {
            comiledTemplate = cache.get(name, () -> {
                return TemplateCompiler.compileTemplate(CharStreams.toString(template));
            });
        } catch (ExecutionException e) {
        }

        return comiledTemplate;
    }

}
