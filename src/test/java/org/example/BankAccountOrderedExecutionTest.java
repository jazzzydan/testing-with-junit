package org.example;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Test BankAccount Class in due order")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BankAccountOrderedExecutionTest {

    static BankAccount bankAccount = new BankAccount(0, 0);

    @Test
    @Order(2)
    @DisplayName("Withdraw 300 successfully")
    public void testWithdraw() {
        bankAccount.withdraw(300); // initial balance is zero
        assertEquals(200, bankAccount.getBalance());
    }

    @Test
    @Order(1)
    @DisplayName("Deposit 500 successfully")
    public void testDeposit() {
        bankAccount.deposit(500);
        assertEquals(500, bankAccount.getBalance());
    }

}
