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
package com.fitbur.core.el.spring;

import com.fitbur.core.el.api.ExpressionService;
import com.fitbur.core.el.spring.internal.MapPropertyAccessor;
import com.google.common.cache.Cache;
import com.google.common.io.CharStreams;
import java.io.Reader;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import javax.inject.Inject;
import javax.inject.Provider;
import org.jvnet.hk2.annotations.Service;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 *
 * @author saden
 */
@Service
public class SpringExpressionService implements ExpressionService {

    private final MapPropertyAccessor mapAccessor;
    private final ExpressionParser parser;
    private final Cache<String, Expression> cache;
    private final Provider<StandardEvaluationContext> contextProvider;

    @Inject
    SpringExpressionService(MapPropertyAccessor mapAccessor,
            ExpressionParser parser,
            Cache<String, Expression> cache,
            Provider<StandardEvaluationContext> contextProvider) {
        this.mapAccessor = mapAccessor;
        this.parser = parser;
        this.cache = cache;
        this.contextProvider = contextProvider;
    }

    @Override
    public <T> T eval(String name, Reader template, Object context, Class<T> resultType) {
        Expression expression = compile(name, template);
        StandardEvaluationContext ctx = contextProvider.get();
        ctx.setRootObject(context);
        return expression.getValue(ctx, resultType);
    }

    @Override
    public <T> T eval(String name, Reader template, Map<String, Object> context, Class<T> resultType) {
        Expression expression = compile(name, template);
        StandardEvaluationContext ctx = contextProvider.get();
        ctx.addPropertyAccessor(mapAccessor);
        ctx.setRootObject(context);
        return expression.getValue(ctx, resultType);
    }

    private Expression compile(String name, Reader template) {
        Expression expression = null;

        try {
            expression = cache.get(name, () -> {
                return parser.parseExpression(CharStreams.toString(template));
            });
        }
        catch (ExecutionException e) {
        }

        return expression;

    }

}
