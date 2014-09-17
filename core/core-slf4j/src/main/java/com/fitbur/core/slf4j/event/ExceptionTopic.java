package com.fitbur.core.slf4j.event;

import java.util.Objects;

/**
 *
 * @author Sharmarke Aden
 */
public class ExceptionTopic {

    final private String message;
    final private Throwable cause;

    public ExceptionTopic(String message) {
        this(message, null);
    }

    public ExceptionTopic(String message, Throwable cause) {
        this.message = message;
        this.cause = cause;
    }

    public Throwable getCause() {
        return cause;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.message);
        hash = 83 * hash + Objects.hashCode(this.cause);
        return hash;
    }

    @Override
    public String toString() {
        return "ExceptionEvent{" + "message=" + message + ", cause=" + cause + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ExceptionTopic other = (ExceptionTopic) obj;
        if (!Objects.equals(this.message, other.message)) {
            return false;
        }
        return Objects.equals(this.cause, other.cause);
    }

}
