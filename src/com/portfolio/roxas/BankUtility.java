package com.portfolio.roxas;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @author Johan Rickardo Roxas
 * @version 1.00 (2023/06/06)
 * com.portfolio.roxas.BankUtility class.
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
     * Saves accounts of user to existing or new file
     * @throws IOException if writing or reading error occurs
     */
    public void saveFile() throws IOException {
        try {
            FileOutputStream outputStream = new FileOutputStream(Main.userFolder + "/accounts", false);
            StringBuilder stringBuilder = new StringBuilder();

            // Append the account information to the StringBuilder
            for (Account account : accounts) {
                stringBuilder.append(account.toString()).append("\n");
            } // end of for

            // Write the StringBuilder content to the file
            outputStream.write(stringBuilder.toString().getBytes());
            outputStream.close();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e2) {
            throw new IOException();
        } // end of try-catch
    } // end of saveFile method
} // end of class com.portfolio.roxas.BankUtility