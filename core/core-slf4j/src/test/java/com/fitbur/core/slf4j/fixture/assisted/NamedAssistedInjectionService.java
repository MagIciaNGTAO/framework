/*
 * Copyright 2014 Sharmarke Aden.
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
package com.fitbur.core.slf4j.fixture.assisted;

import javax.inject.Inject;
import javax.inject.Named;
import org.jvnet.hk2.annotations.Service;
import org.slf4j.Logger;

/**
 *
 * @author Sharmarke Aden
 */
@Service
public class NamedAssistedInjectionService {

    @Named("field")
    @Inject
    private Logger field;
    private Logger method;
    private final Logger constructor;

    @Inject
    NamedAssistedInjectionService(@Named("constructor") Logger constructor) {
        this.constructor = constructor;
    }

    @Inject
    public void setMethod(@Named("method") Logger logger) {
        this.method = logger;
    }

    public Logger getMethod() {
        return method;
    }

    public Logger getField() {
        return field;
    }

    public Logger getConstructor() {
        return constructor;
    }

}
