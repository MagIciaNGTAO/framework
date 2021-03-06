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
package com.fitbur.core.jdk.util.provider;

import com.fitbur.core.hk2.UndisposableFactory;
import java.util.Optional;
import static java.util.Optional.empty;
import org.glassfish.hk2.api.PerLookup;
import org.glassfish.hk2.api.Rank;
import org.jvnet.hk2.annotations.Service;

/**
 *
 * @author Sharmarke Aden
 */
@Service
public class EmptyOptionalProvider implements UndisposableFactory<Optional> {

    @Rank(Integer.MIN_VALUE)
    @PerLookup
    @Override
    public Optional provide() {
        return empty();
    }

}
