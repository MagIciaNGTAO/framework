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

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;
import org.glassfish.hk2.api.PerLookup;
import org.jvnet.hk2.annotations.Service;

/**
 *
 * @author Sharmarke Aden
 */
@PerLookup
@Service
public class ObjectOutputStreamBuilder {

    private OutputStream output;

    @EnsuresNonNull("output")
    public ObjectOutputStreamBuilder output(OutputStream output) {
        this.output = output;

        return this;
    }

    @RequiresNonNull("output")
    public ObjectOutputStream build() throws IOException {
        return new ObjectOutputStream(output);
    }

}
