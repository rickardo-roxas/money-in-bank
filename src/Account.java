import javax.swing.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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
     * Transaction of current account
     */
    private final List<Transaction> transactions = new ArrayList<>();

    /**
     * Default format for date of transaction.
     */
    private final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    /**
     * Date of application use.
     */
    private final LocalDateTime now = LocalDateTime.now();

    /**
     * Constructs an object of Account with default values.
     */
    public Account() {
        bank = "BPI";
        accountHolder = "Juan Dela Cruz";
        accountName = "Savings";
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
    public Account(String bank, String accountHolder, String accountName, String accountNumber, double balance) {
        this.bank = bank;
        this.accountHolder = accountHolder;
        this.accountName = accountName;
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
        return bank + "," +
                accountHolder + "," +
                accountNumber + "," +
                balance;
    } // end of toString method

    /**
     * Deposits amount to current account
     * @param amount amount to deposit
     * @throws Exception if exception or error occurs
     */
    public void deposit(double amount) throws Exception {
        double newBalance = 0.0;
        try {
            if (amount > 0) {
                newBalance = this.getBalance() + amount;
                this.setBalance(newBalance);
            } // end of if
            transactions.add(new Transaction("Deposit",amount, dateFormat.format(now)));
            printTransaction();
        } catch (ArithmeticException e1) {
            JOptionPane.showMessageDialog(null, e1.getMessage());
            e1.printStackTrace();
        } catch (Exception e2) {
            throw new Exception();
        } // end of try-catch
    } // end of deposit method

    /**
     * Withdraws amount from current bank account
     * @param amount amount to withdraw
     * @throws Exception if exception or error occurs
     */
    public void withdraw(double amount) throws Exception {
        try {
            if ((amount > 0) && (amount <= this.balance)) {
                this.setBalance(this.getBalance() - amount);
            } // end of if
            transactions.add(new Transaction("Withdraw",amount, dateFormat.format(now)));
            printTransaction();
        } catch (ArithmeticException e1) {
            JOptionPane.showMessageDialog(null, e1.getMessage() + "Try again.");
            e1.printStackTrace();
        } catch (Exception e2) {
            throw new Exception();
        } // end of try-catch
    } // end of withdraw method

    /**
     * Transfers amount to another account
     * @param recipient Account to be given
     * @param amount amount to be transferred
     * @throws Exception if exception or error occurs
     */
    public void transfer(Account recipient, double amount) throws Exception {
        try {
             if (amount > 0) {
                 this.setBalance(this.getBalance() - amount);
                 recipient.setBalance(recipient.getBalance() + amount);
             } // end of if
            transactions.add(new Transaction("Transfer", amount, dateFormat.format(now)));
            printTransaction();
        } catch (ArithmeticException e1) {
            JOptionPane.showMessageDialog(null, e1.getMessage() + "Try again.");
            e1.printStackTrace();
        } catch (Exception e2) {
            throw new Exception();
        } // end of try-catch
    } // end of transfer method

    /**
     * Prints transaction to file
     * @throws Exception if error occurs during reading or writing file.
     */
    private void printTransaction() throws Exception {
        try {
            // Reads existing transactions
            BufferedReader inputStream = new BufferedReader(
                    new FileReader(Main.userFolder + "/" + this.accountNumber));
            String existingTransactions = inputStream.readLine();
            inputStream.close();

            // Writes to file with existing contents without overwriting
            FileOutputStream outputStream =
                    new FileOutputStream(Main.userFolder + "/" + this.accountNumber, false);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(existingTransactions).append("\n");

            // Prints new transactions to file
            for (Transaction transaction: transactions){
                stringBuilder.append(transaction.toString()).append("\n");
            } // end of for

            outputStream.write(stringBuilder.toString().getBytes());
            outputStream.close();
        } catch (FileNotFoundException e1) {
            throw new FileNotFoundException();
        } catch (IOException e2) {
            throw new IOException();
        } // end of try-catch
    } // end of printTransaction method
} // end of class Bank
