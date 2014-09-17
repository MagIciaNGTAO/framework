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
package com.fitbur.core.hk2;

import java.util.Optional;
import org.glassfish.hk2.api.Injectee;
import org.glassfish.hk2.api.ServiceHandle;
import org.jvnet.hk2.annotations.Contract;

/**
 * AssistedResolver defines a contract for resolving Include injection.
 *
 * @author Sharmarke Aden
 */
@Contract
public interface AssistedResolver {

    /**
     * This method will return the object that should be injected into the given
     * injection point. It is the responsibility of the implementation to ensure
     * that the object returned can be safely injected into the injection point.
     * <p>
     * This method should not do the injection themselves
     *
     * @param injectee The injection point this value is being injected into
     * @param root The service handle of the root class being created, which
     * should be used in order to ensure proper destruction of associated
     * {@code PerLookup} scoped objects. This can be null in the case that this is
     * being used for an object not managed by HK2. This will only happen if
     * this object is being created with the create method of ServiceLocator.
     * @return the object that will be injected or null if the resolver does not
     * handle the service being injected.
     */
    public Optional resolve(Injectee injectee, ServiceHandle<?> root);
}
