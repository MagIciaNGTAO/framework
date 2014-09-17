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

import java.util.Optional;
import org.checkerframework.checker.nullness.qual.Nullable;
import javax.inject.Inject;
import org.jvnet.hk2.annotations.Service;

/**
 *
 * @author Sharmarke Aden
 */
@Service
public class Strings {

    private final Optionals optionals;

    @Inject
    Strings(Optionals optionals) {
        this.optionals = optionals;
    }

    public boolean isEmpty(@Nullable String ref) {
        return !optionals.ofNullable(ref)
                .filter(p -> !p.isEmpty())
                .isPresent();
    }

    public boolean isBlank(@Nullable String ref) {
         return !optionals.ofNullable(ref)
                .filter(p -> !p.trim().isEmpty())
                .isPresent();
    }

    public Optional<String> ofEmpty(@Nullable String ref) {
        return optionals.ofNullable(ref)
                .filter(p -> !isEmpty(p));
    }

    public Optional<String> ofBlank(@Nullable String ref) {
        return optionals.ofNullable(ref)
                .filter(p -> !isBlank(p));
    }

    

}
