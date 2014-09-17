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
package com.fitbur.core.slf4j.assisted;

import com.fitbur.core.hk2.AssistedResolver;
import com.fitbur.core.hk2.qualifier.Typed;
import com.fitbur.core.hk2.util.ReflectionService;
import java.util.Optional;
import static java.util.Optional.of;
import javax.inject.Inject;
import javax.inject.Named;
import org.glassfish.hk2.api.Injectee;
import org.glassfish.hk2.api.ServiceHandle;
import org.jvnet.hk2.annotations.Service;
import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;

/**
 *
 * @author Sharmarke Aden
 */
@Typed(Logger.class)
@Service
public class LoggerResolver implements AssistedResolver {

    private final ReflectionService reflectionService;

    @Inject
    LoggerResolver(ReflectionService reflectionService) {
        this.reflectionService = reflectionService;
    }

    @Override
    public Optional resolve(Injectee injectee, ServiceHandle<?> root) {
        Optional<Named> named = reflectionService.findQualifier(injectee, Named.class);
        
        String name = named.map(p -> p.value())
                .orElseGet(() -> injectee.getInjecteeClass().getName());
        
        return of(getLogger(name));
    }
}
