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
package com.fitbur.core.api.request;

import java.util.Optional;
import org.jvnet.hk2.annotations.Contract;

/**
 *
 * @author Sharmarke Aden
 * @param <T> the type of the input to the function
 * @param <R> the type of the result of the function
 */
@Contract
@FunctionalInterface
public interface Query<T, R> {

    Optional<R> execute(T input);
}
