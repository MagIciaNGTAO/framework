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
package com.fitbur.core.metrics.exception;

import com.codahale.metrics.MetricRegistry;
import com.fitbur.core.metrics.ExceptionMetered;
import com.fitbur.core.metrics.util.MetricsUtil;
import java.lang.reflect.Executable;
import javax.inject.Inject;
import org.aopalliance.intercept.ConstructorInterceptor;
import org.aopalliance.intercept.ConstructorInvocation;
import org.aopalliance.intercept.Invocation;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.jvnet.hk2.annotations.Service;

/**
 *
 * @author Sharmarke Aden
 */
@Service
public class ExceptionMeteredInterceptor implements MethodInterceptor, ConstructorInterceptor {

    private final MetricRegistry registery;
    private final MetricsUtil metricsUtil;

    @Inject
    ExceptionMeteredInterceptor(MetricRegistry registery, MetricsUtil metricsUtil) {
        this.registery = registery;
        this.metricsUtil = metricsUtil;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        return execute(invocation, invocation.getMethod());
    }

    @Override
    public Object construct(ConstructorInvocation invocation) throws Throwable {
        return execute(invocation, invocation.getConstructor());
    }

    private Object execute(Invocation invocation, Executable executable) throws Throwable {
        try {
            return invocation.proceed();
        }
        catch (Throwable t) {
            ExceptionMetered metric = executable.getAnnotation(ExceptionMetered.class);
            if (metric.cause().isAssignableFrom(t.getClass())) {
                String name = metricsUtil.computeName(executable, metric.absolute(), metric.name());
                registery.meter(name).mark();
            }
            throw t;
        }
    }

}
