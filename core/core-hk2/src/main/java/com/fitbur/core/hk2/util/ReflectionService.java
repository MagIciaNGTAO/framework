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
package com.fitbur.core.hk2.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Executable;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;
import org.glassfish.hk2.api.Injectee;
import org.jvnet.hk2.annotations.Service;

/**
 *
 * @author Sharmarke Aden
 */
@Service
public class ReflectionService {

    public <T extends Annotation> Optional<T> findParameter(Injectee injectee, Class<T> type) {
        AnnotatedElement parent = injectee.getParent();

        Optional<T> annotation;

        if (parent instanceof Field) {
            Field field = (Field) parent;
            annotation = Optional.ofNullable(field.getAnnotation(type));
        } else {
            Executable executable = (Executable) parent;
            Annotation[][] annotations = executable.getParameterAnnotations();
            Annotation[] params = annotations[injectee.getPosition()];
            annotation = Stream.of(params)
                    .parallel()
                    .filter(p -> type.equals(p.annotationType()))
                    .map(p -> (T) p)
                    .findFirst();
        }

        return annotation;
    }

    public <T extends Annotation> Optional<T> findQualifier(Injectee injectee, Class<T> type) {
        Set<Annotation> qualifiers = injectee.getRequiredQualifiers();

        return qualifiers.parallelStream()
                .filter(p -> type.equals(p.annotationType()))
                .map(p -> (T) p)
                .findFirst();
    }

}
