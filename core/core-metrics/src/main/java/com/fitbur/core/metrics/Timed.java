package com.fitbur.core.metrics;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * An annotation for marking a method of an annotated object as timed. Given a
 * method like this:
 *
 * <pre>{@code
 * &#064;Timed(name = "fancyName")
 * public String fancyName(String name) {
 *   return "Sir Captain " + name;
 * }
 * }</pre>
 * <p>
 * A timer for the defining class with the name {@code fancyName} will be
 * created and each time the {@code #fancyName(String)} method is invoked, the
 * method's execution will be timed.
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR})
public @interface Timed {

    /**
     * @return The name of the timer.
     */
    String name() default "";

    /**
     * @return If {@code true}, use the given name as an absolute name. If
     * {@code false}, use the given name relative to the annotated class.
     */
    boolean absolute() default false;
}
