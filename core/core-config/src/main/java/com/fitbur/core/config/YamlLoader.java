/*
 * Copyright 2014 Fitbur.
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
package com.fitbur.core.config;

import com.fitbur.core.config.util.ResourceService;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.function.Function;
import javax.inject.Inject;
import org.glassfish.hk2.api.PerLookup;
import org.jvnet.hk2.annotations.Service;
import org.yaml.snakeyaml.Yaml;

/**
 *
 * @author Sharmarke Aden
 */
@PerLookup
@Service
public class YamlLoader {

    private final ResourceService loader;
    private final Yaml yaml;

    @Inject
    YamlLoader(ResourceService loader, Yaml yaml) {
        this.loader = loader;
        this.yaml = yaml;
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> load(String resource) {
        try {
            InputStream stream = loader.getResourceAsStream(resource);
            Map<String, Object> config = yaml.loadAs(stream, Map.class);

            return config;
        }
        catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
