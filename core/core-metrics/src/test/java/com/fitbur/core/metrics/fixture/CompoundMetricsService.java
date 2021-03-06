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
package com.fitbur.core.metrics.fixture;

import com.fitbur.core.metrics.Counted;
import com.fitbur.core.metrics.ExceptionMetered;
import com.fitbur.core.metrics.Metered;
import com.fitbur.core.metrics.Timed;

import org.jvnet.hk2.annotations.Service;

/**
 *
 * @author saden
 */
@Service
public class CompoundMetricsService {

	@Timed
	@Metered
	@Counted
	public void defaultCountedMeteredTimedMethod() {

	}

	@Timed(name = "timed.method")
	@Metered(name = "metered.method")
	@Counted(name = "counted.method")
	@ExceptionMetered(name = "exception.method")
	public void explictCountedMetertedTimedMethod() throws Exception {
		throw new Exception();
	}
}
