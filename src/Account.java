/**
 * @author Johan Rickardo Roxas
 * @version 1.00 (2023/06/01)
 * Template for Account object.
 */
public class Account {
    /**
     * Name of bank that the account is registered to
     */
    private String bank;
    /**
     * Name of account holder
     */
    private String accountHolder;
    /**
     * Name of account (i.e. Savings, Checking, etc.)
     */
    private String accountName;
    /**
     * Account number (XXX XXX XXXX)
     */
    private String accountNumber;
    /**
     * Outstanding balance of the account
     */
    private double balance;

    /**
     * Constructs an object of Account with default values.
     */
    public Account() {
        bank = "BPI";
        accountHolder = "Juan Dela Cruz";
        accountNumber = "123 567 8900";
        balance = 10000.00;
    } // end of Account default constructor

    /**
     * Constructs an object of Account with user-defined values
     * @param bank given bank company
     * @param accountHolder given name of account holder
     * @param accountNumber given account number
     * @param balance given balance
     */
    public Account(String bank, String accountHolder, String accountNumber, double balance) {
        this.bank = bank;
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        this.balance = balance;
    } // end of Account constructor

    /**
     * Mutates the state of the bank attribute
     * @param bank given value
     */
    public void setBank(String bank) {
        this.bank = bank;
    } // end of setBank mutator method

    /**
     * Mutates the state of the accountHolder attribute
     * @param accountHolder given value
     */
    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    } // end of setAccountHolder mutator method

    /**
     * Mutates the state of the accountName attribute
     * @param accountName given value
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    } // end of setAccountName mutator method

    /**
     * Mutates the state of the accountNumber attribute
     * @param accountNumber given value
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    } // end of setAccountNumber mutator method

    /**
     * Mutates the state of the balance attribute
     * @param balance given value
     */
    public void setBalance(double balance) {
        this.balance = balance;
    } // end of setBalance mutator method

    /**
     * Accesses the state of the bank attribute
     * @return state/value of bank attribute
     */
    public String getBank() {
        return bank;
    } // end of getBank accessor method

    /**
     * Accesses the state of the accountHolder attribute
     * @return state/value of accountHolder attribute
     */
    public String getAccountHolder() {
        return accountHolder;
    } // end of getAccountHolder accessor method

    /**
     * Accesses the state of the accountName attribute
     * @return state/value of accountName attribute
     */
    public String getAccountName() {
        return accountName;
    } // end of getAccountName accessor method

    /**
     * Accesses the state of the accountNumber attribute
     * @return state/value of accountNumber attribute
     */
    public String getAccountNumber() {
        return accountNumber;
    } // end of getAccountNumber accessor method

    /**
     * Accesses the state of the balance attribute
     * @return state/value of balance attribute
     */
    public double getBalance() {
        return balance;
    } // end of getBalance accessor method

    /**
     * Concatenates the attribute states of Account
     * @return states as comma-separated values
     */
    public String toString() {
        return accountHolder + "," +
                accountNumber + "," +
                balance;
    } // end of toString method
} // end of class Bank
