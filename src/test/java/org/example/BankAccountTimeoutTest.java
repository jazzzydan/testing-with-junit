package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

//for all tests in the class
//@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
@ExtendWith(BankAccountParameterResolver.class)
public class BankAccountTimeoutTest {

    @Test
    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS) // Specifies a timeout limit for the test. The test must complete within 500 milliseconds (0.5 seconds) or it will fail.
    public void testDepositTimeoutAnnotation(BankAccount bankAccount) {

        // The try-catch block is used to pause the current thread for 100 milliseconds (0.1 seconds).
        // This simulates a delay in the test execution, which is useful to test if the timeout limit is working correctly.
        try {
            Thread.sleep(100); // Pauses the execution for 100 milliseconds.
        } catch (InterruptedException e) {
            e.printStackTrace(); // Handles the InterruptedException if the sleep is interrupted.
        }

        // Now, we perform a deposit of 300 into the bank account after the 100ms delay.
        bankAccount.deposit(300);

        // This asserts that after depositing 300, the balance of the bank account should be exactly 300.
        // If the balance is not 300, the test will fail.
        assertEquals(300, bankAccount.getBalance());
    }

    @Test
    public void testDepositTimeoutAssertion(BankAccount bankAccount) {
        try {
            // Pauses the execution for 1 second (1000ms), which exceeds the 500ms limit of assertTimeout,
            // but this is not relevant for the timeout check as the timeout applies only to the lambda block.
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Deposits 300 into the bank account.
        bankAccount.deposit(300);

        // assertTimeout only checks the specific block inside the lambda, not the entire test.
        // This allows more control because only the specific block inside the lambda is subjected to the timeout.
        assertTimeout(Duration.ofMillis(500), () -> {
            // Instead of sleep, an actual task could be added here for testing.
            // This block is timed, and the sleep of 100ms simulates a task that should complete within 500ms.
            Thread.sleep(100);
        });

        // Only the code block inside assertTimeout is timed.
        // In this case, it’s the block Thread.sleep(100), which simulates a task.
        // This means the test itself can take longer, but JUnit only checks if this particular block completes within the specified time.
    }
}
