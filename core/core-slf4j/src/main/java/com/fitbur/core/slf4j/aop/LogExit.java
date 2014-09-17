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
package com.fitbur.core.slf4j.aop;

import static com.fitbur.core.slf4j.aop.Level.DEBUG;
import java.lang.annotation.Documented;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.METHOD;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;

/**
 * AOP Annotation that enables method and constructor pre execution logging.
 *
 * @author Sharmarke Aden
 */
@Documented
@Inherited
@Retention(RUNTIME)
@Target({METHOD, CONSTRUCTOR})
public @interface LogExit {

    public static String DEFAULT_LOGGER = "{{class.name}}";
    public static String DEFAULT_MESSAGE = "Exiting {{member.name}}";

    /**
     * Set the logger name. If not set it defaults to
     * {@link LogExit#DEFAULT_LOGGER}.
     *
     * @return the logger name
     */
    String value() default DEFAULT_LOGGER;

    /**
     * Sets the log message template. If not set it defaults to
     * {@link LogExit#DEFAULT_MESSAGE}.
     *
     * @return log message.
     */
    String message() default DEFAULT_MESSAGE;

    /**
     * Message logging level. Defaults to {@link Level#DEBUG}
     *
     * @return log level.
     */
    Level level() default DEBUG;
}
