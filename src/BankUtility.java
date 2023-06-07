import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author Johan Rickardo Roxas
 * @version 1.00 (2023/06/06)
 * BankUtility class.
 * TODO: Documentation
 */
public class BankUtility {

    /**
     * Accounts of current user
     */
    public List<Account> accounts = new ArrayList<>();

    /**
     * Reads input from file
     */
    private BufferedReader inputStream;

    /**
     * Default format for date of transaction.
     */
    private final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    /**
     * Date of application use.
     */
    private final LocalDateTime now = LocalDateTime.now();

    /**
     * Reads user's stored file to populate List of accounts.
     * @throws Exception if exception or error occurs
     */
    public void readUserAccounts() throws Exception {
        try {
            inputStream = new BufferedReader(new FileReader(Main.userFolder + "/accounts"));
            String line = "";

            while ((line = inputStream.readLine()) != null) {
                String[] accountData = line.split(",");
                String bank = accountData[0];
                String accountHolder = accountData[1];
                String accountName = accountData[2];
                String accountNumber = accountData[3];
                double balance = Double.parseDouble(accountData[4]);
                accounts.add(new Account(bank, accountHolder, accountName, accountNumber, balance));
                System.out.println(accounts);
            } // end of while
            inputStream.close();
        } catch (FileNotFoundException e1) {
            throw new FileNotFoundException();
        } catch (IOException e2) {
            throw new IOException();
        } // end of try-catch
    } // end of readFromFile method

    /**
     * TODO: Documentation of current method
     * @param userFolder
     * @param accountNumber
     * @throws Exception
     */
    public void readTransactions(String userFolder, String accountNumber) throws Exception {
        List<Transaction> transactions = new ArrayList<>();
        try {
             inputStream = new BufferedReader(new FileReader(userFolder + "/" + accountNumber));
             String line = "";

             while ((line = inputStream.readLine()) != null) {
                 String[] transactionData = line.split(",");
                 String type = transactionData[0];
                 double amount = Double.parseDouble(transactionData[1]);
                 String date = transactionData[2];
                 transactions.add(new Transaction(type, amount, date));
             } // end of while
         } catch (FileNotFoundException e1) {
             throw new FileNotFoundException();
         } catch (IOException e2) {
             throw new IOException();
         } // end of try-catch
    } // end of readTransactions method

    private void printTransaction(String accountNumber, String type, double amount) {
        try {
            inputStream = new BufferedReader(new FileReader(Main.userFolder + "/" + accountNumber));
        } catch (FileNotFoundException e1) {

        } catch (IOException e2) {

        } // end of try-catch
    } // end of printTransaction method

    /**
     * Deposits amount to existing bank account
     * @param accountNumber given account number
     * @param amount deposit amount
     * @throws Exception if exception or error occurs
     */
    public void deposit(String accountNumber, double amount) throws Exception {
        double newBalance = 0.0;
        try {
            for (Account account: accounts) {
                if (account.getAccountNumber().equals(accountNumber)) {
                    newBalance = account.getBalance() + amount;
                    account.setBalance(newBalance);
                } // end of if-else
            } // end of for
        } catch (ArithmeticException e1) {
            JOptionPane.showMessageDialog(null, e1.getMessage());
            e1.printStackTrace();
        } // end of try-catch
    } // end of deposit method

    /**
     * Withdraws amount from existing bank account
     * @param accountNumber given account number
     * @param amount withdrawal amount
     * @throws Exception if exception or error occurs
     */
    public void withdraw(String accountNumber, double amount) throws Exception {
        double newBalance = 0.0;
        try {
            for (Account account: accounts) {
                if (account.getAccountName().equals(accountNumber)) {
                    newBalance = account.getBalance() - amount;
                    account.setBalance(newBalance);
                } // end of if-else
            } // end of for
        } catch (ArithmeticException e1) {
            JOptionPane.showMessageDialog(null, e1.getMessage());
            e1.printStackTrace();
        } // end of try-catch
    } // end of withdraw method
} // end of class BankUtility