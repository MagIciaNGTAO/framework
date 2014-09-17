/*
 * Copyright 2013 saden.
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

import java.util.Map;
import java.util.Properties;
import org.glassfish.hk2.api.PerLookup;
import org.jvnet.hk2.annotations.Service;

/**
 *
 * @author saden
 */
@PerLookup
@Service
public class PropertiesBuilder {

    public DefaultsBuilder defaults(Properties defaults) {
        return new DefaultsBuilder(defaults);
    }

    public EntriesBuilder entries(Map entries) {
        return new EntriesBuilder(entries);
    }

    public Properties build() {
        return new Properties();
    }

    public class DefaultsBuilder {

        private final Properties defaults;

        private DefaultsBuilder(Properties defaults) {
            this.defaults = defaults;
        }

        public DefaultEntriesBuilder entries(Map entries) {
            return new DefaultEntriesBuilder(defaults, entries);
        }

        public Properties build() {
            return new Properties(defaults);
        }
    }

    public class EntriesBuilder {

        private final Map entries;

        private EntriesBuilder(Map entries) {
            this.entries = entries;
        }

        public Properties build() {
            Properties props = new Properties();

            props.putAll(entries);

            return props;
        }
    }

    public class DefaultEntriesBuilder {

        private final Properties defaults;
        private final Map entries;

        public DefaultEntriesBuilder(Properties defaults, Map entries) {
            this.defaults = defaults;
            this.entries = entries;
        }

        public Properties build() {
            Properties props = new Properties(defaults);

            props.putAll(entries);

            return props;

        }
    }
}
