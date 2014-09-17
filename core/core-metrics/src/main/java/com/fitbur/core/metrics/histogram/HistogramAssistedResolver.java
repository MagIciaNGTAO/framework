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
package com.fitbur.core.metrics.histogram;

import com.codahale.metrics.Histogram;
import com.codahale.metrics.MetricRegistry;
import com.fitbur.core.hk2.AssistedResolver;
import com.fitbur.core.hk2.qualifier.Typed;
import com.fitbur.core.metrics.util.MetricsUtil;
import java.util.Optional;
import static java.util.Optional.of;
import javax.inject.Inject;
import org.glassfish.hk2.api.Injectee;
import org.glassfish.hk2.api.ServiceHandle;
import org.jvnet.hk2.annotations.Service;

/**
 *
 * @author Sharmarke Aden
 */
@Typed(Histogram.class)
@Service
public class HistogramAssistedResolver implements AssistedResolver {

    private final MetricRegistry registry;
    private final MetricsUtil metricsUtil;

    @Inject
    HistogramAssistedResolver(MetricRegistry registry,
            MetricsUtil metricsUtil) {
        this.registry = registry;
        this.metricsUtil = metricsUtil;
    }

    @Override
    public Optional resolve(Injectee injectee, ServiceHandle<?> root) {
        String name = metricsUtil.computeName(injectee);

        return of(registry.histogram(name));
    }

}
