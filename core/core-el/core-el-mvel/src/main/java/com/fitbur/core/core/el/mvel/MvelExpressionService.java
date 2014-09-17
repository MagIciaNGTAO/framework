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

import com.fitbur.core.el.api.ExpressionService;
import com.google.common.cache.Cache;
import com.google.common.io.CharStreams;
import java.io.Reader;
import java.io.Writer;
import java.util.Map;
import static java.util.Objects.requireNonNull;
import java.util.concurrent.ExecutionException;
import javax.inject.Inject;
import org.jvnet.hk2.annotations.Service;
import org.mvel2.MVEL;
import org.mvel2.compiler.CompiledExpression;

/**
 *
 * @author saden
 */
@Service
public class MvelExpressionService implements ExpressionService {
    
    private final Cache<String, CompiledExpression> cache;
    
    @Inject
    MvelExpressionService(Cache<String, CompiledExpression> cache) {
        this.cache = cache;
    }
    
    @Override
    public <T> T eval(String name, Reader template, Object context, Class<T> resultType) {
        validate(name, template, context, resultType);
        CompiledExpression compiled = compile(name, template);
        
        return MVEL.executeExpression(compiled, context, resultType);
    }
    
    @Override
    public <T> T eval(String name, Reader template, Map<String, Object> context, Class<T> resultType) {
        validate(name, template, context, resultType);
        CompiledExpression compiled = compile(name, template);
        
        return MVEL.executeExpression(compiled, context, resultType);
    }
    
    private void validate(String name, Reader template, Object context, Class<?> resultType) {
        requireNonNull(name, "name must not be null");
        requireNonNull(template, "template must not be null");
        requireNonNull(context, "context must not be null");
        requireNonNull(resultType, "resultType must not be null");
    }
    
    private CompiledExpression compile(String name, Reader template) {
        CompiledExpression compiled = null;
        
        try {
            compiled = cache.get(name, () -> {
                return (CompiledExpression) MVEL.compileExpression(CharStreams.toString(template));
            });
        } catch (ExecutionException ex) {
        }
        
        return compiled;
    }
    
}
