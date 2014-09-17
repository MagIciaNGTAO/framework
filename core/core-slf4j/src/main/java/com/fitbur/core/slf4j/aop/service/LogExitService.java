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
package com.fitbur.core.slf4j.aop.service;

import com.fitbur.core.mustache.MustacheService;
import com.fitbur.core.slf4j.aop.LogEntrance;
import com.fitbur.core.slf4j.aop.LogExit;
import java.lang.reflect.Executable;
import java.util.Map;
import java.util.function.BiConsumer;
import javax.inject.Inject;
import org.aopalliance.intercept.Invocation;
import org.jvnet.hk2.annotations.Service;

/**
 *
 * @author saden
 */
@Service
public class LogExitService {

    private final ScopeService scopeService;
    private final MustacheService mustacheService;
    private final LoggingService loggerService;

    @Inject
    LogExitService(ScopeService scopeService,
            MustacheService mustacheService,
            LoggingService loggerService) {
        this.scopeService = scopeService;
        this.mustacheService = mustacheService;
        this.loggerService = loggerService;
    }

    public void log(Invocation invocation, Executable executable, Object result) {
        LogEntrance preLogger = executable.getDeclaredAnnotation(LogEntrance.class);

        if (preLogger == null) {
            return;
        }
        String prefix = executable.getName();
        String loggerName = prefix + ".logger";
        String loggerTemplate = preLogger.value();
        String messageName = prefix + ".message";
        String messageTemplate = preLogger.message();
        Map<String, Object> scopes = scopeService.createScope(invocation, executable);

        String logger = mustacheService.expand(loggerName, loggerTemplate, scopes);
        String message = mustacheService.expand(messageName, messageTemplate, scopes);
        loggerService.log(logger, message, preLogger.level());
    }

}
