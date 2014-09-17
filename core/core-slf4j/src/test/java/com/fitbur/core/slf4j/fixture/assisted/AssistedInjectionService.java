package com.fitbur.core.slf4j.fixture.assisted;

import javax.inject.Inject;
import org.jvnet.hk2.annotations.Service;
import org.slf4j.Logger;

/**
 *
 * @author Sharmarke Aden
 */
@Service
public class AssistedInjectionService {

    @Inject
    Logger field;
    private final Logger constructor;
    private Logger method;

    @Inject
    AssistedInjectionService(Logger constructor) {
        this.constructor = constructor;
    }

    @Inject
    public void setMethod(Logger method) {
        this.method = method;
    }

    public Logger getField() {
        return field;
    }

    public Logger getConstructor() {
        return constructor;
    }

    public Logger getMethod() {
        return method;
    }

}
