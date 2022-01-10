package ibmjunit;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;

public class Activity2 {

@Test	
public void notEnoughFunds() {
	BankAccount ba = new BankAccount(9);
	assertThrows(NotEnoughFundsException.class, () -> ba.withdraw(10),"Low balance");
	
}

@Test	
public void enoughFunds() {
	BankAccount account = new BankAccount(100);
	 
    // Assertion for no exceptions
    assertDoesNotThrow(() -> account.withdraw(100));


}
}
