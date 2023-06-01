/**
 * @author Johan Rickardo Roxas
 * @version 1.00 (2023/06/01)
 * Template for Account object.
 */
public class Account {
    private String accountHolder;
    private String accountNumber;
    private double balance;

    /**
     * Constructs an object of Account with default values.
     */
    public Account() {
        accountHolder = "Jay Pritchett";
        accountNumber = "123 567 8900";
        balance = 10000.00;
    } // end of Account default constructor

    public Account(String accountHolder, String accountNumber, double balance) {
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        this.balance = balance;
    } // end of Account constructor

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public String toString() {
        return accountHolder + "," +
                accountNumber + "," +
                balance;
    } // end of toString method
} // end of class Bank
