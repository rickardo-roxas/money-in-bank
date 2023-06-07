import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
     * Reads user's stored file to populate List of accounts.
     * @param userFolder directory of user
     * @throws Exception if exception or error occurs
     */
    public void readUserAccounts(String userFolder) throws Exception {
        try {
            inputStream = new BufferedReader(new FileReader(userFolder + "/accounts"));
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
     * Deposits amount to existing bank account
     * @param accountNumber given account number
     * @param amount deposit amount
     * @return newBalance
     * @throws Exception if exception or error occurs
     */
    public double deposit(String accountNumber, double amount) throws Exception {
        double newBalance = 0.0;
        try {
            for (Account account: accounts) {
                if (account.getAccountNumber().equals(accountNumber)) {
                    newBalance = account.getBalance() + amount;
                } else {
                    throw new Exception();
                } // end of if-else
            } // end of for
        } catch (ArithmeticException e1) {
            JOptionPane.showMessageDialog(null, e1.getMessage());
            e1.printStackTrace();
        } // end of try-catch
        return newBalance;
    } // end of deposit method

    /**
     * Withdraws amount from existing bank account
     * @param accountNumber given account number
     * @param amount withdrawal amount
     * @return newBalance
     * @throws Exception if exception or error occurs
     */
    public double withdraw(String accountNumber, double amount) throws Exception {
        double newBalance = 0.0;
        try {
            for (Account account: accounts) {
                if (account.getAccountName().equals(accountNumber)) {
                    newBalance = account.getBalance() - amount;
                } else {
                     throw new Exception();
                } // end of if-else
            } // end of for
        } catch (ArithmeticException e1) {
            JOptionPane.showMessageDialog(null, e1.getMessage());
            e1.printStackTrace();
        } // end of try-catch
        return newBalance;
    } // end of withdraw method
} // end of class BankUtility