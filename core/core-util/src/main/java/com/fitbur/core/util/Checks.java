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
import javax.inject.Inject;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.jvnet.hk2.annotations.Service;

/**
 *
 * @author Sharmarke Aden
 */
@Service
public class Checks {

    private final Formats formats;

    @Inject
    Checks(Formats formats) {
        this.formats = formats;
    }

    public void checkArgument(boolean expr) {
        if (!expr) {
            throw new IllegalArgumentException();
        }
    }

    public void checkArgument(boolean expr, @Nullable Object msg) {
        if (!expr) {
            throw new IllegalArgumentException(String.valueOf(msg));
        }
    }

    public void checkArgument(boolean expr,
            @Nullable String template,
            @Nullable Object... args) {
        if (!expr) {
            throw new IllegalArgumentException(formats.format(template, args));
        }
    }

    public void checkState(boolean expr) {
        if (!expr) {
            throw new IllegalStateException();
        }
    }

    public void checkState(boolean expr, @Nullable Object msg) {
        if (!expr) {
            throw new IllegalStateException(String.valueOf(msg));
        }
    }

    public void checkState(boolean expr,
            @Nullable String template,
            @Nullable Object... args) {
        if (!expr) {
            throw new IllegalStateException(formats.format(template, args));
        }
    }

    public void checkState(Optional optional) {
        if (!optional.isPresent()) {
            throw new IllegalStateException();
        }
    }

    public void checkState(Optional optional, @Nullable Object msg) {
        if (!optional.isPresent()) {
            throw new IllegalStateException(String.valueOf(msg));
        }
    }

    public void checkState(Optional optional, @Nullable String template, @Nullable Object... args) {
        if (!optional.isPresent()) {
            throw new IllegalStateException(formats.format(template, args));
        }
    }

    public <T> T checkNotNull(T ref) {
        if (ref == null) {
            throw new NullPointerException();
        }
        return ref;
    }

    public <T> T checkNotNull(T ref, @Nullable Object msg) {
        if (ref == null) {
            throw new NullPointerException(String.valueOf(msg));
        }
        return ref;
    }

    public <T> T checkNotNull(T ref,
            @Nullable String template,
            @Nullable Object... args) {
        if (ref == null) {
            throw new NullPointerException(formats.format(template, args));
        }
        return ref;
    }

}
