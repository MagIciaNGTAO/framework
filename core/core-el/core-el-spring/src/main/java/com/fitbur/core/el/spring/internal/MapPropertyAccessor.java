/*
 * Copyright 2014 Fitbur, Inc..
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
package com.fitbur.core.el.spring.internal;

import java.util.Map;
import org.jvnet.hk2.annotations.Service;
import org.springframework.expression.AccessException;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.PropertyAccessor;
import org.springframework.expression.TypedValue;

/**
 *
 * @author saden
 */
@Service
public class MapPropertyAccessor implements PropertyAccessor {

    @Override
    public Class<?>[] getSpecificTargetClasses() {
        return new Class<?>[]{Map.class};
    }

    @Override
    public boolean canRead(EvaluationContext context, Object target, String name) 
            throws AccessException {
        Map<?, ?> map = (Map<?, ?>) target;
        
        return map.containsKey(name);
    }

    @Override
    public TypedValue read(EvaluationContext context, Object target, String name) 
            throws AccessException {
        Map<?, ?> map = (Map<?, ?>) target;
        Object value = map.get(name);
        
        if (value == null && !map.containsKey(name)) {
            throw new MapAccessException(name);
        }
        return new TypedValue(value);
    }

    @Override
    public boolean canWrite(EvaluationContext context, Object target, String name) throws AccessException {
        return true;
    }

    @Override
    @SuppressWarnings({"unchecked", "unchecked"})
    public void write(EvaluationContext context, Object target, String name, Object newValue) throws AccessException {
        Map map = (Map) target;
        
        map.put(name, newValue);
    }

    @SuppressWarnings("serial")
    private static class MapAccessException extends AccessException {

        private final String key;

        public MapAccessException(String key) {
            super(null);
            this.key = key;
        }

        @Override
        public String getMessage() {
            return "Map does not contain a value for key '" + this.key + "'";
        }
    }

}
