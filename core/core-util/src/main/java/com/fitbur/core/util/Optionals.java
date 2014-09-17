/*
 * Copyright 2014 saden.
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
package com.fitbur.core.util;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Stream;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.jvnet.hk2.annotations.Service;

/**
 *
 * @author Sharmarke Aden
 */
@Service
public class Optionals {

    public <T> Optional<T> of(T ref) {
        return Optional.of(ref);
    }

    @SafeVarargs
    public final <T> Optional<T[]> ofArray(T... ref) {
        return of(ref);
    }

    public <T> Optional<T> ofNullable(@Nullable T ref) {
        return Optional.ofNullable(ref);
    }

    public <T> Optional<T> empty() {
        return Optional.empty();
    }

    public <T> Optional<T> toEmpty(boolean expr, T ref) {
        return ofNullable(ref).filter(p -> expr);
    }

    public <K, V> Optional<Map<K, V>> ofEmpty(@Nullable Map<K, V> ref) {
        return ofNullable(ref).filter(p -> !p.isEmpty());
    }

    public Optional<Properties> ofEmpty(@Nullable Properties ref) {
        return ofNullable(ref).filter(p -> !p.isEmpty());
    }

    public <E, T extends Collection<E>> Optional<T> ofEmpty(@Nullable T ref) {
        return ofNullable(ref).filter(p -> !p.isEmpty());
    }

    @SafeVarargs
    public final <T> Optional<Stream<T>> ofStream(T... ref) {
        return of(Stream.of(ref));
    }

}
