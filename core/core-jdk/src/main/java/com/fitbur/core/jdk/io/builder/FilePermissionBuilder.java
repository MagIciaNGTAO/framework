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
package com.fitbur.core.jdk.io.builder;

import java.io.FilePermission;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.glassfish.hk2.api.PerLookup;
import org.jvnet.hk2.annotations.Service;

/**
 * TODO: enhance actions permissions
 *
 * @author Sharmarke Aden
 */
@PerLookup
@Service
public class FilePermissionBuilder {

    public PermissionBuilder path(String path) {
        return new PermissionBuilder(path);
    }

    public class PermissionBuilder {

        private final String path;
        private final Set actions = new HashSet(5);

        private PermissionBuilder(String path) {
            this.path = path;
        }

        public PermissionBuilder read() {
            this.actions.add("read");

            return this;
        }

        public PermissionBuilder write() {
            this.actions.add("write");

            return this;
        }

        public PermissionBuilder delete() {
            this.actions.add("delete");

            return this;
        }

        public PermissionBuilder execute() {
            this.actions.add("execute");

            return this;
        }

        public PermissionBuilder readlink() {
            this.actions.add("readlink");

            return this;
        }

        public FilePermission build() {
            StringBuilder builder = new StringBuilder();

            if (!actions.isEmpty()) {
                Iterator<String> iter = actions.iterator();

                char separator = ',';

                if (iter.hasNext()) {
                    builder.append(iter.next());

                    while (iter.hasNext()) {
                        builder.append(separator)
                                .append(iter.next());
                    }
                }
            }

            String result = builder.toString();

            return new FilePermission(path, result);
        }

    }

}
