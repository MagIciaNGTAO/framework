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
 * TODO: enhance actions permissions
 *
 * @author Sharmarke Aden
 */
@PerLookup
@Service
public class RuntimePermissionBuilder {

    @MonotonicNonNull
    private String name;

    @EnsuresNonNull("name")
    public RuntimePermissionBuilder name(String name) {
        this.name = name;

        return this;
    }

    @RequiresNonNull("name")
    public RuntimePermission build() {
        return new RuntimePermission(name);
    }

}
