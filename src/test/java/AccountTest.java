import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AccountTest {

    private Account account;

    // This method runs before each test to set up a new Account instance
    @BeforeEach
    public void setUp() {
        account = new Account(); // Initialize a new account with balance 0.0
    }

    // Test initial balance is 0.0 when an account is created
    @Test
    public void testInitialBalance() {
        assertEquals(0.0, account.getBalance(), "Initial balance should be 0.0");
    }

    // Test depositing money increases the balance
    @Test
    public void testDeposit() {
        account.deposit(100.0);
        assertEquals(100.0, account.getBalance(), "Balance should be 100.0 after depositing 100.0");
    }

    // Test depositing a negative amount
    @Test
    public void testDepositNegativeAmount() {
        account.deposit(-50.0); // Deposit a negative amount
        assertEquals(0.0, account.getBalance(), "Balance should remain 0.0 after depositing a negative amount");
    }

    // Test withdrawing an amount less than the balance
    @Test
    public void testWithdrawLessThanBalance() {
        account.deposit(100.0);
        double withdrawnAmount = account.withdraw(50.0); // Withdraw 50.0
        assertEquals(50.0, withdrawnAmount, "Withdrawn amount should be 50.0");
        assertEquals(50.0, account.getBalance(), "Balance should be 50.0 after withdrawing 50.0");
    }

    // Test withdrawing the exact balance
    @Test
    public void testWithdrawExactBalance() {
        account.deposit(100.0);
        double withdrawnAmount = account.withdraw(100.0); // Withdraw the exact balance
        assertEquals(100.0, withdrawnAmount, "Withdrawn amount should be 100.0");
        assertEquals(0.0, account.getBalance(), "Balance should be 0.0 after withdrawing the entire balance");
    }

    // Test withdrawing more than the available balance (should return 0.0 and not affect the balance)
    @Test
    public void testWithdrawMoreThanBalance() {
        account.deposit(50.0); // Deposit some amount
        double withdrawnAmount = account.withdraw(100.0); // Try to withdraw more than the balance
        assertEquals(0.0, withdrawnAmount, "Withdrawn amount should be 0.0 when trying to withdraw more than the balance");
        assertEquals(50.0, account.getBalance(), "Balance should remain 50.0 after failed withdrawal");
    }

    // Test withdrawing zero (balance should remain unchanged)
    @Test
    public void testWithdrawZeroAmount() {
        account.deposit(100.0); // Deposit some amount
        double withdrawnAmount = account.withdraw(0.0);
        assertEquals(0.0, withdrawnAmount, "Withdrawn amount should be 0.0 when withdrawing zero");
        assertEquals(100.0, account.getBalance(), "Balance should remain 100.0 after withdrawing 0.0");
    }

    // Test the balance after multiple transactions
    @Test
    public void testBalanceAfterMultipleTransactions() {
        account.deposit(200.0);
        account.withdraw(50.0);
        account.deposit(100.0);
        account.withdraw(100.0);
        assertEquals(150.0, account.getBalance(), "Balance should be 150.0 after a series of transactions");
    }
}
