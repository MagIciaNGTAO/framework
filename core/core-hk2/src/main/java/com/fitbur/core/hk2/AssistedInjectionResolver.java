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
package com.fitbur.core.hk2;

import com.fitbur.core.hk2.qualifier.impl.TypedImpl;
import java.lang.reflect.Type;
import java.util.Optional;
import javax.inject.Inject;
import javax.inject.Named;
import org.glassfish.hk2.api.Injectee;
import org.glassfish.hk2.api.InjectionResolver;
import org.glassfish.hk2.api.IterableProvider;
import org.glassfish.hk2.api.Rank;
import org.glassfish.hk2.api.ServiceHandle;
import org.jvnet.hk2.annotations.Service;

/**
 *
 * @author Sharmarke Aden
 */
@Rank(50000)
@Service
public class AssistedInjectionResolver implements InjectionResolver<Inject> {

    private final InjectionResolver<Inject> systemResolver;
    private final IterableProvider<AssistedResolver> assistedResolvers;

    @Inject
    public AssistedInjectionResolver(IterableProvider<AssistedResolver> assistedResolvers,
            @Named(InjectionResolver.SYSTEM_RESOLVER_NAME) InjectionResolver<Inject> systemResolver) {
        this.assistedResolvers = assistedResolvers;
        this.systemResolver = systemResolver;
    }

    @Override
    public Object resolve(Injectee injectee, ServiceHandle<?> root) {
        Type type = injectee.getRequiredType();
        AssistedResolver resolver = null;

        if (type instanceof Class) {
            TypedImpl namedType = new TypedImpl((Class) type);
            resolver = assistedResolvers.qualifiedWith(namedType).get();
        }

        if (resolver == null) {
            return systemResolver.resolve(injectee, root);
        }

        return resolver.resolve(injectee, root)
                .orElseGet(() -> systemResolver.resolve(injectee, root));

    }

    @Override
    public boolean isConstructorParameterIndicator() {
        return false;
    }

    @Override
    public boolean isMethodParameterIndicator() {
        return false;
    }

}
