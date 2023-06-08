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
} // end of class BankUtility