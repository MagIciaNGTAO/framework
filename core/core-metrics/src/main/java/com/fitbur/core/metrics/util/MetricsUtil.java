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
package com.fitbur.core.metrics.util;

import static com.codahale.metrics.MetricRegistry.name;
import com.fitbur.core.hk2.util.ReflectionService;
import com.fitbur.core.metrics.Metric;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.Optional;
import javax.inject.Inject;
import org.glassfish.hk2.api.Injectee;
import org.jvnet.hk2.annotations.Service;

/**
 *
 * @author Sharmarke Aden
 */
@Service
public class MetricsUtil {

    private final ReflectionService reflectionService;

    @Inject
    MetricsUtil(ReflectionService reflectionService) {
        this.reflectionService = reflectionService;
    }

    public String computeName(Member member, boolean absolute, String name) {
        Class<?> type = member.getDeclaringClass();
        String metricName = name;

        if (metricName.isEmpty() && member instanceof Method) {
            metricName = member.getName();
        }

        if (!absolute) {
            metricName = name(type, metricName);
        }

        return metricName;
    }

    public String computeName(Injectee injectee) {
        AnnotatedElement parent = injectee.getParent();
        Member member = (Member) parent;
        Class<?> type = member.getDeclaringClass();

        Optional<Metric> annotation = reflectionService.findParameter(injectee, Metric.class);
        String name;

        if (annotation.isPresent()) {
            Metric metric = annotation.get();

            if (metric.absolute()) {
                name = name(metric.value());
            } else {
                name = name(type, metric.value());
            }
        } else {
            name = name(type);
        }

        return name;
    }

}
