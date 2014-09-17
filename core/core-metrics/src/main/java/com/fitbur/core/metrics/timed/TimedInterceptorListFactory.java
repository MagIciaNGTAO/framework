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
package com.fitbur.core.metrics.timed;

import com.fitbur.core.hk2.UndisposableFactory;
import static java.util.Collections.singletonList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.jvnet.hk2.annotations.Service;

/**
 *
 * @author Sharmarke Aden
 */
@Service
public class TimedInterceptorListFactory implements UndisposableFactory<List<TimedInterceptor>> {

    private final TimedInterceptor interceptor;

    @Inject
    TimedInterceptorListFactory(TimedInterceptor interceptor) {
        this.interceptor = interceptor;
    }

    @Singleton
    @Override
    public List<TimedInterceptor> provide() {
        return singletonList(interceptor);
    }

}
