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
package com.fitbur.core.jdk.util.builder;

import java.util.Locale;
import org.glassfish.hk2.api.PerLookup;
import org.jvnet.hk2.annotations.Service;

/**
 *
 * @author Sharmarke Aden
 */
@PerLookup
@Service
public class LocaleBuilder {

    public LanguageBuilder language(String language) {
        return new LanguageBuilder(language);
    }

    public class LanguageBuilder {

        public static final String DEFAULT_COUNTRY = "";
        public static final String DEFAULT_VARIANT = "";
        private final String language;
        private String country = DEFAULT_COUNTRY;
        private String variant = DEFAULT_VARIANT;

        private LanguageBuilder(String language) {
            this.language = language;
        }

        public LanguageBuilder country(String country) {
            this.country = country;

            return this;
        }

        public LanguageBuilder variant(String variant) {
            this.variant = variant;

            return this;
        }

        public Locale build() {
            return new Locale(language, country, variant);
        }
    }
}
