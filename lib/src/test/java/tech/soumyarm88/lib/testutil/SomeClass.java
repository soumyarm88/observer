/*
 * This source file was generated by the Gradle 'init' task
 */
package tech.soumyarm88.lib.testutil;

import tech.soumyarm88.lib.observer.LogExecutionTime;

import static java.lang.Thread.sleep;

public class SomeClass {
    @LogExecutionTime
    public boolean someMethod() {
        try {
            sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @LogExecutionTime
    public void someOtherMethod() {
        try {
            sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        throw new RuntimeException("Something went wrong");
    }
}
