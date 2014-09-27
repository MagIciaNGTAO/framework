package com.fitbur.core.metrics;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * An annotation for marking a method of an annotated object as counted.
 *
 * <p>
 * Given a method like this:
 * <pre>{@code
 *     {@literal @}Counted(name = "fancyName")
 *     public String fancyName(String name) {
 *         return "Sir Captain " + name;
 *     }
 * }</pre>
 * <p>
 * A counter for the defining class with the name {@code fancyName} will be
 * created and each time the {@code #fancyName(String)} method is invoked, the
 * counter will be marked.
 *
 * @since 4.0.0
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR})
public @interface Counted {

    /**
     * @return The name of the counter.
     */
    String name() default "";

    /**
     * @return If {@code true}, use the given name as an absolute name. If
     * {@code false}, use the given name relative to the annotated class.
     */
    boolean absolute() default false;

    /**
     * @return the amount by which the counter will be increased or decreased
     */
    long value() default 1L;

}
