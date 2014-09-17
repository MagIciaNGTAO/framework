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
package com.fitbur.core.jdk.util.builder.concurrent;

import java.util.concurrent.SynchronousQueue;
import org.glassfish.hk2.api.PerLookup;
import org.jvnet.hk2.annotations.Service;

/**
 *
 * @author Sharmarke Aden
 */
@PerLookup
@Service
public class SynchronousQueueBuilder {

    public FairBuilder fair() {
        return new FairBuilder(true);
    }

    public SynchronousQueue build() {
        return new SynchronousQueue();
    }

    public class FairBuilder {

        private final Boolean fair;

        private FairBuilder(Boolean fair) {
            this.fair = fair;
        }

        public SynchronousQueue build() {
            return new SynchronousQueue(fair);
        }

    }

}