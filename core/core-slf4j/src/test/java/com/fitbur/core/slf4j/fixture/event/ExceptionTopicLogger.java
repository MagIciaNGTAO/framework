package com.fitbur.core.slf4j.fixture.event;

import com.fitbur.core.slf4j.event.ExceptionTopic;
import javax.inject.Inject;
import org.glassfish.hk2.api.messaging.SubscribeTo;
import org.jvnet.hk2.annotations.Service;
import org.slf4j.Logger;

/**
 *
 * @author Sharmarke Aden
 */
@Service
public class ExceptionTopicLogger {

    private final Logger logger;

    @Inject
    ExceptionTopicLogger(Logger logger) {
        this.logger = logger;
    }

    public void handle(@SubscribeTo ExceptionTopic event) {
        logger.error(event.getMessage(), event.getCause());
    }

}
