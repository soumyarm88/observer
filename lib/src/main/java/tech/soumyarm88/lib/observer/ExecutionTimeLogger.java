package tech.soumyarm88.lib.observer;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A logging aspect that measures and logs the execution time of methods annotated with {@code @LogExecutionTime}.
 * The aspect intercepts the annotated methods and calculates the time taken for execution.
 * It logs the execution time in milliseconds using the SLF4J logging framework.
 *
 * This class leverages AspectJ AOP functionality, specifically the {@code @Around} advice.
 */
@Aspect
public class ExecutionTimeLogger {

    private ExecutionTimeLogger() { }

    private static final Logger logger = LoggerFactory.getLogger(ExecutionTimeLogger.class);

    /**
     * The @Around advice must intercept only the method execution and not the method invocation to avoid logging twice.
     * @see <a href="https://stackoverflow.com/a/65021309/1617765">https://stackoverflow.com/a/65021309/1617765</a>
     *
     * @param joinPoint the join point representing the intercepted method invocation
     * @return the result of the method execution
     * @throws Throwable if the intercepted method throws an exception during execution
     */
    @Around("@annotation(tech.soumyarm88.lib.observer.LogExecutionTime) && execution(* *(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Entering logExecutionTime");
        long startTimeNano = System.nanoTime();
        String methodName = joinPoint.getSignature().toShortString();
        try {
            return joinPoint.proceed();
        } finally {
            logTime(methodName, startTimeNano);
        }
    }

    private static void logTime(String methodName, long startTimeNano) {
        logger.info("{} executed in {} ms", methodName, (System.nanoTime() - startTimeNano)/1000000.0);
    }
}