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

import com.fitbur.core.hk2.DisposableFactory;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import javax.inject.Inject;
import org.jvnet.hk2.annotations.Service;
import org.springframework.expression.Expression;

/**
 *
 * @author saden
 */
@Service
public class ExpressionCacheProvider implements DisposableFactory<Cache<String, Expression>> {

    private final CacheBuilder<String, Expression> builder;

    @Inject
    @SuppressWarnings("unchecked")
    ExpressionCacheProvider(CacheBuilder builder) {
        this.builder = builder;
    }

    @Override
    public Cache<String, Expression> provide() {
        return builder.initialCapacity(512)
                .maximumSize(8192)
                .concurrencyLevel(4)
                .build();
    }

    @Override
    public void dispose(Cache<String, Expression> instance) {
        instance.cleanUp();
    }

}
