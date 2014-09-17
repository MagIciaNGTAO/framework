package com.fitbur.core.slf4j.event;

import javax.inject.Inject;
import org.glassfish.hk2.api.messaging.Topic;
import org.jvnet.hk2.annotations.Service;

/**
 *
 * @author Sharmarke Aden
 */
@Service
public class ExceptionTopicService {

    private final Topic<ExceptionTopic> topic;

    @Inject
    ExceptionTopicService(Topic<ExceptionTopic> topic) {
        this.topic = topic;
    }

    public void publish(String message) {
        publish(message, null);
    }

    public void publish(Throwable cause) {
        publish(cause.getMessage(), cause);
    }

    public void publish(String message, Throwable cause) {
        topic.publish(new ExceptionTopic(message, cause));
    }

}
