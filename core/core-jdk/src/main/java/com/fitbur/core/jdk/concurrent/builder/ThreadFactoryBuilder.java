/*
 * Copyright 2014 saden.
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
package com.fitbur.core.jdk.concurrent.builder;

import com.fitbur.core.util.Checks;
import com.fitbur.core.util.Optionals;
import com.fitbur.core.util.Strings;
import java.util.Optional;
import static java.util.Optional.empty;
import static java.util.concurrent.Executors.defaultThreadFactory;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;
import javax.inject.Inject;
import org.glassfish.hk2.api.PerLookup;
import org.jvnet.hk2.annotations.Service;

/**
 *
 * @author Sharmarke Aden
 */
@PerLookup
@Service
public class ThreadFactoryBuilder {

    private final Checks checks;
    private final Strings strings;
    private final Optionals optionals;

    private Optional<String> format = empty();
    private Optional<Boolean> daemon = empty();
    private Optional<Integer> priority = empty();
    private Optional<Thread.UncaughtExceptionHandler> handler = empty();

    @Inject
    ThreadFactoryBuilder(Checks checks, Strings strings, Optionals optionals) {
        this.checks = checks;
        this.strings = strings;
        this.optionals = optionals;
    }

    public ThreadFactoryBuilder format(String format) {
        this.format = strings.ofBlank(format);

        return this;
    }

    public ThreadFactoryBuilder daemon(boolean daemon) {
        this.daemon = optionals.of(daemon);

        return this;
    }

    public ThreadFactoryBuilder priority(int priority) {
        checks.checkState(priority >= Thread.MIN_PRIORITY,
                "priority [%s] must be >= %s", priority, Thread.MIN_PRIORITY);
        checks.checkArgument(priority <= Thread.MAX_PRIORITY,
                "priority [%s] must be <= %s", priority, Thread.MAX_PRIORITY);
        this.priority = optionals.of(priority);

        return this;
    }

    public ThreadFactoryBuilder handler(Thread.UncaughtExceptionHandler handler) {
        checks.checkNotNull(handler, "handler can not be null");
        this.handler = optionals.ofNullable(handler);

        return this;
    }

    public ThreadFactory build() {
        final AtomicLong count = new AtomicLong(0);
        final ThreadFactory threadFactory = defaultThreadFactory();

        return (Runnable runnable) -> {
            Thread thread = threadFactory.newThread(runnable);

            if (format.isPresent()) {
                thread.setName(String.format(format.get(), count.getAndIncrement()));
            }
            if (daemon.isPresent()) {
                thread.setDaemon(daemon.get());
            }
            if (priority.isPresent()) {
                thread.setPriority(priority.get());
            }
            if (handler.isPresent()) {
                thread.setUncaughtExceptionHandler(handler.get());
            }

            return thread;
        };
    }

}
