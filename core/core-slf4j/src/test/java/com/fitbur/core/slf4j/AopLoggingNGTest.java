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
package com.fitbur.core.slf4j;

import com.fitbur.core.slf4j.fixture.aop.ConstructorException;
import com.fitbur.core.slf4j.fixture.aop.EntranceExitLogging;
import com.fitbur.core.slf4j.fixture.aop.MethodException;
import com.fitbur.core.slf4j.fixture.CustomException;
import javax.inject.Inject;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import org.glassfish.hk2.api.MultiException;
import org.glassfish.hk2.api.ServiceLocator;
import org.jvnet.testing.hk2testng.HK2;
import org.testng.annotations.Test;
import static uk.org.lidalia.slf4jtest.LoggingEvent.debug;
import uk.org.lidalia.slf4jtest.TestLogger;
import uk.org.lidalia.slf4jtest.TestLoggerFactory;

/**
 *
 * @author Sharmarke Aden
 */
@HK2
public class AopLoggingNGTest {

    @Inject
    ServiceLocator locator;

    @Test
    public void assertConstructorExceptionLogging() {
        try {
            locator.getService(ConstructorException.class);
            fail("exception not thrown");
        }
        catch (MultiException t) {
            TestLogger logger = TestLoggerFactory.getTestLogger(ConstructorException.class);
            assertThat(logger).isNotNull();
            assertThat(logger.getLoggingEvents())
                    .contains(debug(new CustomException("Constructor Failed"), "Constructor Failed"));
            return;

        }
        fail("exception not thrown");
    }

    @Test
    public void assertMethodExceptionLogging() {
        try {
            MethodException result = locator.getService(MethodException.class);
            result.throwException();
            fail("exception not thrown");
        }
        catch (CustomException t) {
            TestLogger logger = TestLoggerFactory.getTestLogger(MethodException.class);
            assertThat(logger).isNotNull();
            assertThat(logger.getLoggingEvents())
                    .contains(debug(new CustomException("Method Failed"), "Method Failed"));
            return;

        }
        fail("exception not thrown");
    }

    @Test
    public void assertEntranceLogging() {
        EntranceExitLogging result = locator.getService(EntranceExitLogging.class);
        result.loggedMethod();
        TestLogger logger = TestLoggerFactory.getTestLogger(EntranceExitLogging.class);
        assertThat(logger).isNotNull();
        assertThat(logger.getLoggingEvents())
                .contains(debug("Entering com.fitbur.core.slf4j.fixture.aop.EntranceExitLogging"),
                        debug("Entering loggedMethod"));
    }

    @Test
    public void assertExitLogging() {
        EntranceExitLogging result = locator.getService(EntranceExitLogging.class);
        result.loggedMethod();
        TestLogger logger = TestLoggerFactory.getTestLogger(EntranceExitLogging.class);
        assertThat(logger).isNotNull();
        assertThat(logger.getLoggingEvents())
                .contains(debug("Exiting com.fitbur.core.slf4j.fixture.aop.EntranceExitLogging"),
                        debug("Exiting loggedMethod"));
    }
}
