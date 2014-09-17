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

import org.glassfish.hk2.api.Factory;
import org.jvnet.hk2.annotations.Contract;

/**
 * Generic contract for disposable factory objects. Factory classes that create
 * create {@code Singleton} scoped object requiring cleanup upon locator
 * shutdown of should implement this contract.
 *
 * @author Sharmarke Aden
 * @param <T> This must be the type of entity for which this is a factory. For
 * example, if this were a factory for Foo, then your factory must implement
 * DisposableFactory&lt;Foo&gt;.
 */
@Contract
public interface DisposableFactory<T> extends Factory<T> {
}
