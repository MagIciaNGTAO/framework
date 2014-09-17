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
import javax.inject.Singleton;
import org.glassfish.hk2.api.Rank;
import org.jvnet.hk2.annotations.Service;
import org.springframework.expression.ParserContext;
import org.springframework.expression.common.TemplateParserContext;

/**
 *
 * @author saden
 */
@Service
public class TemplateParserContextProvider implements UndisposableFactory<ParserContext> {

    @Rank(Integer.MIN_VALUE)
    @Singleton
    @Override
    public ParserContext provide() {
        return new TemplateParserContext();
    }

}
