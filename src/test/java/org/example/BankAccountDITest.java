package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

// @ExtendWith annotation links this test class to the BankAccountParameterResolver,
// which will be responsible for providing the BankAccount object when required.
@ExtendWith(BankAccountParameterResolver.class)
public class BankAccountDITest {

    // This test method tests the deposit functionality of the BankAccount class.
    // The BankAccount object is automatically injected by the BankAccountParameterResolver.
    @Test
    @DisplayName("Deposit 500 successfully")  // Custom test name to display in test reports.
    public void testDeposit(BankAccount bankAccount) {
        // The deposit() method is called to add 500 to the bank account balance.
        bankAccount.deposit(500);

        // The assertEquals method checks if the actual balance (after deposit) is equal to the expected value (500).
        assertEquals(500, bankAccount.getBalance());
    }
}
