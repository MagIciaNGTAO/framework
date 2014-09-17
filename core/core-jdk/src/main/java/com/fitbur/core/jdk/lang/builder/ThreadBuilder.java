/*
 * Copyright 2014 Sharmarke Aden (saden1).
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
package com.fitbur.core.jdk.lang.builder;

import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;
import org.glassfish.hk2.api.PerLookup;
import org.jvnet.hk2.annotations.Service;

/**
 *
 * @author Sharmarke Aden
 */
@PerLookup
@Service
public class ThreadBuilder {

    private static final Long DEFAULT_SIZE = 0L;

    public NameBuilder name(String name) {
        return new NameBuilder(name);
    }

    public RunnableBuilder runnable(Runnable runnable) {
        return new RunnableBuilder(runnable);
    }

    public Thread build() {
        return new Thread();
    }

    public class NameBuilder {

        private final String name;
        @MonotonicNonNull
        private Runnable runnable;

        private NameBuilder(String name) {
            this.name = name;
        }

        @EnsuresNonNull("runnable")
        public NameBuilder runnable(Runnable runnable) {
            this.runnable = runnable;

            return this;
        }

        @RequiresNonNull("runnable")
        public GroupBuilder group(ThreadGroup group) {
            return new GroupBuilder(group, runnable, name);
        }

        public Thread build() {
            return new Thread(runnable, name);
        }

    }

    public class RunnableBuilder {

        private final Runnable runnable;
        @MonotonicNonNull
        private String name;

        private RunnableBuilder(Runnable runnable) {
            this.runnable = runnable;
        }

        @EnsuresNonNull("name")
        public RunnableBuilder name(String name) {
            this.name = name;

            return this;
        }

        @RequiresNonNull("name")
        public GroupBuilder group(ThreadGroup group) {
            return new GroupBuilder(group, runnable, name);
        }

        public Thread build() {
            if (name == null) {
                return new Thread(runnable);
            }

            return new Thread(runnable, name);
        }
    }

    public class GroupBuilder {

        private final Runnable runnable;
        private final String name;
        private final ThreadGroup group;
        private Long size = DEFAULT_SIZE;

        private GroupBuilder(ThreadGroup group, Runnable runnable, String name) {
            this.group = group;
            this.runnable = runnable;
            this.name = name;
        }

        @EnsuresNonNull("size")
        public GroupBuilder size(long size) {
            this.size = size;

            return this;
        }

        public Thread build() {
            return new Thread(group, runnable, name, size);
        }
    }
}
