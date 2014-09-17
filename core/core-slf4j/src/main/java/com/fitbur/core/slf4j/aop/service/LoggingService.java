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

import com.fitbur.core.slf4j.aop.Level;
import org.jvnet.hk2.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author saden
 */
@Service
public class LoggingService {

    public void log(String name, Throwable cause, Level level) {
        Logger logger = LoggerFactory.getLogger(name);
        String message = cause.getMessage();
        switch (level) {
            case TRACE:
                logger.trace(message, cause);
                break;
            case DEBUG:
                logger.debug(message, cause);
                break;
            case INFO:
                logger.info(message, cause);
                break;
            case WARN:
                logger.warn(message, cause);
                break;
            case ERROR:
                logger.error(message, cause);
                break;
            default:
                throw new AssertionError(level + " not recognized");
        }
    }

    public void log(String name, String message, Level level) {
        Logger logger = LoggerFactory.getLogger(name);
        switch (level) {
            case TRACE:
                logger.trace(message);
                break;
            case DEBUG:
                logger.debug(message);
                break;
            case INFO:
                logger.info(message);
                break;
            case WARN:
                logger.warn(message);
                break;
            case ERROR:
                logger.error(message);
                break;
            default:
                throw new AssertionError(level + " not recognized");
        }

    }

}
