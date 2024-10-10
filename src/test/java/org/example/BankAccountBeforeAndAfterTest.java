package org.example;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS) // This annotation allows @BeforeAll and @AfterAll to be non-static.
public class BankAccountBeforeAndAfterTest {

    static BankAccount bankAccount;

    // @BeforeAll is executed **once** before all tests in the class.
    // It is used for setting up resources that are expensive to create or shared across all tests, like database connections or initializing a service.
    // Since @BeforeAll runs once, it helps optimize resource setup when multiple tests need the same initial state.
    @BeforeAll
    public void prepTest() {
        bankAccount = new BankAccount(500, 0); // Initializes the bankAccount with a starting balance of 500.
    }

    // @BeforeEach is executed **before every test** in the class.
    // Use this when you want to set up or reset the state **before each test** (e.g., refreshing database entries).
    // Here, @BeforeEach is not used because each test builds on the state of the previous one.
    // @BeforeEach example:
    // @BeforeEach
    // public void setupEach() {
    //     bankAccount = new BankAccount(500, 0); // This would reset the balance to 500 before each test.
    // }

    @Test
    @DisplayName("Withdraw 300 successfully")
    public void testWithdraw() {
        bankAccount.withdraw(300); // Withdraws 300 from the initial balance of 500, so the new balance should be 200.
        assertEquals(200, bankAccount.getBalance());
    }

    @Test
    @DisplayName("Deposit 500 successfully")
    public void testDeposit() {
        bankAccount.deposit(500); // Adds 500 to the current balance (200 after the first test), resulting in a new balance of 700.
        assertEquals(700, bankAccount.getBalance());
    }

    // @AfterEach is executed **after each test** in the class.
    // Use this to reset states, clear resources, or perform cleanup after every test.
    // For example, if each test uses some temporary files or resets a service state.
    // Example usage:
    // @AfterEach
    // public void tearDownEach() {
    //     bankAccount.reset(); // Reset the bank account balance/state after each test to prevent data bleed.
    // }

    // @AfterAll is executed **once** after all tests in the class have completed.
    // It is used for cleaning up shared resources that were initialized in @BeforeAll.
    // In this example, it simply prints a message indicating all tests are finished.
    @AfterAll
    public void endTest() {
        System.out.println("Finished with tests.");
    }
}
