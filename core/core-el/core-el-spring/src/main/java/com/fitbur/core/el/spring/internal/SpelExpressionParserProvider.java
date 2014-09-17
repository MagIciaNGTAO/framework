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
package com.fitbur.core.el.spring.internal;

import com.fitbur.core.hk2.UndisposableFactory;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.glassfish.hk2.api.Rank;
import org.jvnet.hk2.annotations.Service;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 *
 * @author saden
 */
@Service
public class SpelExpressionParserProvider implements UndisposableFactory<ExpressionParser> {

    private final SpelParserConfiguration config;

    @Inject
    SpelExpressionParserProvider(SpelParserConfiguration config) {
        this.config = config;
    }

    @Rank(Integer.MIN_VALUE)
    @Singleton
    @Override
    public ExpressionParser provide() {
        return new SpelExpressionParser(config);
    }

}
