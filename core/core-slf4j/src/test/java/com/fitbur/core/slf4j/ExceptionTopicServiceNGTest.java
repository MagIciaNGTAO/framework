package com.fitbur.core.slf4j;

import com.fitbur.core.slf4j.event.ExceptionTopicService;
import com.fitbur.core.slf4j.fixture.event.ExceptionTopicLogger;
import com.fitbur.core.slf4j.fixture.CustomException;
import javax.inject.Inject;
import static org.assertj.core.api.Assertions.assertThat;
import org.jvnet.testing.hk2testng.HK2;
import org.testng.annotations.Test;
import uk.org.lidalia.slf4jtest.TestLogger;
import uk.org.lidalia.slf4jtest.TestLoggerFactory;
import static uk.org.lidalia.slf4jtest.LoggingEvent.error;

/**
 *
 * @author Sharmarke Aden
 */
@HK2
public class ExceptionTopicServiceNGTest {

    @Inject
    ExceptionTopicService sut;
    @Inject
    ExceptionTopicLogger exceptionTopicLogger;

    @Test
    public void testPublish() {
        sut.publish(new CustomException("Something Failed"));
    }

    @Test(dependsOnMethods = "testPublish")
    public void testSubscribtion() {
        TestLogger logger = TestLoggerFactory.getTestLogger(ExceptionTopicLogger.class);
        assertThat(logger).isNotNull();
        assertThat(logger.getLoggingEvents())
                .contains(error(new CustomException("Something Failed"), "Something Failed"));

    }

}
