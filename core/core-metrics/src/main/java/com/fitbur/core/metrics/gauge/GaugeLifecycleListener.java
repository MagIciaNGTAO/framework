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
package com.fitbur.core.metrics.gauge;

import com.codahale.metrics.MetricRegistry;
import com.fitbur.core.metrics.Gauge;
import com.fitbur.core.metrics.util.MetricsUtil;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import javax.inject.Inject;
import org.glassfish.hk2.api.Filter;
import org.glassfish.hk2.api.InstanceLifecycleEvent;
import org.glassfish.hk2.api.InstanceLifecycleEventType;
import static org.glassfish.hk2.api.InstanceLifecycleEventType.POST_PRODUCTION;
import org.glassfish.hk2.api.InstanceLifecycleListener;
import static org.glassfish.hk2.utilities.BuilderHelper.allFilter;
import org.jvnet.hk2.annotations.Service;

/**
 *
 * @author Sharmarke Aden
 */
@Service
public class GaugeLifecycleListener implements InstanceLifecycleListener {

    private final MetricRegistry registery;
    private final MetricsUtil metricsUtil;
    private final GaugeFactory gaugeFactory;

    @Inject
    GaugeLifecycleListener(MetricRegistry registery,
            MetricsUtil metricsUtil,
            GaugeFactory gaugeFactory) {
        this.registery = registery;
        this.metricsUtil = metricsUtil;
        this.gaugeFactory = gaugeFactory;
    }

    @Override
    public Filter getFilter() {
        return allFilter();
    }

    @Override
    public void lifecycleEvent(InstanceLifecycleEvent lifecycleEvent) {
        InstanceLifecycleEventType eventType = lifecycleEvent.getEventType();
        if (eventType == POST_PRODUCTION) {
            Class<?> type = lifecycleEvent.getLifecycleObject().getClass();
            for (Method method : type.getMethods()) {
                Gauge metric = method.getAnnotation(Gauge.class);
                if (metric != null) {
                    if (method.getParameterTypes().length == 0) {
                        registery.register(
                                metricsUtil.computeName(method, metric.absolute(), metric.name()),
                                gaugeFactory.newMethodGauge(lifecycleEvent.getLifecycleObject(), method));
                    } else {
                        //TODO: need to warn user that a gauged method can't have parameters.
                    }
                }
            }
        }

    }

}
