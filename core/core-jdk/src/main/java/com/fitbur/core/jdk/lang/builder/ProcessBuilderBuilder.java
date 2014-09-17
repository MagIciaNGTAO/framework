/*
 * Copyright 2013 saden.
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

import java.util.List;
import org.glassfish.hk2.api.PerLookup;
import org.jvnet.hk2.annotations.Service;

/**
 *
 * @author saden
 */
@PerLookup
@Service
public class ProcessBuilderBuilder {

    public ListBuilder commands(List<String> commands) {
        return new ListBuilder(commands);
    }

    public ArrayBuilder commands(String[] commands) {
        return new ArrayBuilder(commands);
    }

    public class ArrayBuilder {

        private final String[] commands;

        private ArrayBuilder(String[] commands) {
            this.commands = commands;
        }

        public ProcessBuilder build() {
            return new ProcessBuilder(commands);
        }
    }

    public class ListBuilder {

        private final List<String> commands;

        private ListBuilder(List<String> commands) {
            this.commands = commands;
        }

        public ProcessBuilder build() {
            return new ProcessBuilder(commands);
        }
    }

}
