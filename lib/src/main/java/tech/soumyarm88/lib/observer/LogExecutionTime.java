package tech.soumyarm88.lib.observer;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to mark methods for execution time logging.
 * When applied to a method, the execution time of that method will be logged
 * using the {@link tech.soumyarm88.lib.observer.ExecutionTimeLogger} aspect.
 *
 * <p>This annotation is processed at runtime and can only be applied to methods.
 * The execution time is logged in milliseconds using SLF4J logging framework.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 * {@code
 *     @LogExecutionTime
 *     public void someMethod() {
 *         // Method implementation
 *     }
 * }
 * </pre>
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogExecutionTime {}