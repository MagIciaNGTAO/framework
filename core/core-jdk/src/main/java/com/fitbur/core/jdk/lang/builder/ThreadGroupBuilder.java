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

import org.glassfish.hk2.api.PerLookup;
import org.jvnet.hk2.annotations.Service;

/**
 *
 * @author Sharmarke Aden
 */
@PerLookup
@Service
public class ThreadGroupBuilder {

    public NameBuilder name(String name) {
        return new NameBuilder(name);
    }

    public class NameBuilder {

        private final String name;

        private NameBuilder(String name) {
            this.name = name;
        }

        public GroupBuilder group(ThreadGroup group) {
            return new GroupBuilder(name, group);
        }

        public ThreadGroup build() throws Exception {
            return new ThreadGroup(name);

        }

    }

    public class GroupBuilder {

        private final String name;
        private final ThreadGroup group;

        private GroupBuilder(String name, ThreadGroup group) {
            this.name = name;
            this.group = group;
        }

        public ThreadGroup build() throws Exception {
            return new ThreadGroup(group, name);
        }

    }

}
