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
package com.fitbur.core.slf4j.aop.interceptor;

import static java.util.Arrays.asList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import org.glassfish.hk2.api.Factory;
import org.jvnet.hk2.annotations.Service;

/**
 *
 * @author Sharmarke Aden
 */
@Service
public class LoggingInterceptorFactory implements Factory<List> {

    private final LoggingInterceptor interceptor;

    @Inject
    LoggingInterceptorFactory(LoggingInterceptor interceptor) {
        this.interceptor = interceptor;
    }

    @Named("slf4j")
    @Singleton
    @Override
    public List provide() {
        return asList(interceptor);
    }

    @Override
    public void dispose(List interceptors) {
        interceptors.clear();
    }

}
