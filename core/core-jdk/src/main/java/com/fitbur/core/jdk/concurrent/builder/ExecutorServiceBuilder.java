/*
 * Copyright (c) 2013, 2014, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fitbur.core.jdk.concurrent.builder;

import com.fitbur.core.jdk.concurrent.QueueFactory;
import com.fitbur.core.util.Checks;
import com.fitbur.core.util.Optionals;
import com.fitbur.core.util.Strings;
import static java.lang.Math.ceil;
import static java.lang.Runtime.getRuntime;
import java.util.Optional;
import static java.util.Optional.empty;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import org.glassfish.hk2.api.PerLookup;
import org.jvnet.hk2.annotations.Service;

/**
 *
 * @author Sharmarke Aden
 */
@PerLookup
@Service
public class ExecutorServiceBuilder {

    public static final String DEFAULT_NAME = "thread";
    public static final Long DEFAULT_KEEP_ALIVE_TIME = 0L;
    public static final TimeUnit DEFAULT_TIME_UNIT = TimeUnit.MILLISECONDS;

    private Optional<String> name = empty();
    private Optional<Integer> corePoolSize = empty();
    private Optional<Integer> maxPoolSize = empty();
    private Optional<Long> keepAlive = empty();
    private Optional<TimeUnit> timeUnit = empty();
    private Optional<BlockingQueue<Runnable>> workQueue = empty();
    private Optional<ThreadFactory> threadFactory = empty();
    private Optional<RejectedExecutionHandler> handler = empty();

    private final Checks checks;
    private final Optionals optionals;
    private final Strings strings;
    private final QueueFactory queueFactory;
    private final ThreadFactoryBuilder threadFactoryBuilder;

    @Inject
    ExecutorServiceBuilder(Checks checks,
            Optionals optionals,
            Strings strings,
            QueueFactory queueFactory,
            ThreadFactoryBuilder threadFactoryBuilder) {
        this.checks = checks;
        this.optionals = optionals;
        this.strings = strings;
        this.queueFactory = queueFactory;
        this.threadFactoryBuilder = threadFactoryBuilder;
    }

    public ExecutorServiceBuilder name(String name) {
        this.name = strings.ofBlank(name);

        return this;
    }

    public ExecutorServiceBuilder corePoolSize(int corePoolSize) {
        checks.checkArgument(corePoolSize >= 1,
                "corePoolSize [%s] must be >= 1", corePoolSize);
        this.corePoolSize = optionals.of(corePoolSize);

        return this;
    }

    public ExecutorServiceBuilder maxPoolSize(int maxPoolSize) {
        this.maxPoolSize = optionals.of(maxPoolSize);

        return this;
    }

    public ExecutorServiceBuilder keepAlive(long keepAlive) {
        checks.checkArgument(keepAlive >= 0,
                "keepAlive [%s] must be >= 0", keepAlive);
        this.keepAlive = optionals.of(keepAlive);

        return this;
    }

    public ExecutorServiceBuilder timeUnit(TimeUnit timeUnit) {
        checks.checkArgument(timeUnit != null, "timeUnit must not be null");
        this.timeUnit = optionals.of(timeUnit);

        return this;
    }

    public ExecutorServiceBuilder workerQueue(BlockingQueue<Runnable> workQueue) {
        checks.checkArgument(workQueue != null, "workQueue must not be null");

        this.workQueue = optionals.of(workQueue);

        return this;
    }

    public ExecutorServiceBuilder threadFactory(ThreadFactory threadFactory) {
        checks.checkArgument(threadFactory != null, "threadFactory must not be null");
        this.threadFactory = optionals.of(threadFactory);

        return this;
    }

    public ExecutorServiceBuilder handler(RejectedExecutionHandler handler) {
        checks.checkArgument(handler != null, "threadFactory must not be null");

        this.handler = optionals.of(handler);

        return this;
    }

    public ExecutorService build() {
        int cores = getRuntime().availableProcessors();
        String format = name.orElse(DEFAULT_NAME + "-pool-%d");

        //the optimal max poolsize is the number of cores + 1
        int maxSize = maxPoolSize.orElse(cores + 1);
        //the optimal core pool size is half the max pool size rounded up
        int coreSize = corePoolSize.orElse((int) ceil(maxSize / 2d));

        ThreadPoolExecutor executorService = new ThreadPoolExecutor(
                coreSize,
                maxSize,
                keepAlive.orElse(DEFAULT_KEEP_ALIVE_TIME),
                timeUnit.orElse(DEFAULT_TIME_UNIT),
                workQueue.orElse(queueFactory.newLinkedBlockingQueue()),
                threadFactory.orElse(threadFactoryBuilder.format(format).build()));

        if (handler.isPresent()) {
            executorService.setRejectedExecutionHandler(handler.get());
        }

        return executorService;
    }

}
