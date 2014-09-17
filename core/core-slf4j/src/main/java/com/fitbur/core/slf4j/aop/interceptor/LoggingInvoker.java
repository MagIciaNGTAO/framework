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

import com.fitbur.core.mustache.MustacheService;
import com.fitbur.core.slf4j.aop.service.LogExceptionService;
import com.fitbur.core.slf4j.aop.service.LogExitService;
import com.fitbur.core.slf4j.aop.service.LogEntranceService;
import java.lang.reflect.Executable;
import javax.inject.Inject;
import org.aopalliance.intercept.Invocation;
import org.jvnet.hk2.annotations.Service;

/**
 *
 * @author Sharmarke Aden
 */
@Service
public class LoggingInvoker {

    private final LogEntranceService preLogger;
    private final LogExitService postLogger;
    private final LogExceptionService exceptionLogger;

    @Inject
    LoggingInvoker(LogEntranceService preLogger,
            LogExitService postLogger,
            LogExceptionService exceptionLogger) {
        this.preLogger = preLogger;
        this.postLogger = postLogger;
        this.exceptionLogger = exceptionLogger;
    }

    public Object invoke(Invocation invocation, Executable executable) throws Throwable {
        Object result = null;

        try {
            preLogger.log(invocation, executable);
            result = invocation.proceed();
            postLogger.log(invocation, executable, result);
        }
        catch (Throwable t) {
            exceptionLogger.log(invocation, executable, t);
            throw t;
        }

        return result;

    }

}
