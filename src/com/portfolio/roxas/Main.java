package com.portfolio.roxas;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.lang.*;
import java.io.*;
import java.awt.*;
import java.util.List;

import static java.awt.event.KeyEvent.*;

/**
 * @author Johan Rickardo Roxas
 * @version 1.00 (2023/06/01)
 * Main program that contains the graphical user-interface.
 */
public class Main extends JFrame {

    /**
     * Utility class.
     * Methods invoked produce useful information.
     */
    public static final BankUtility bankUtility = new BankUtility();

    /**
     * Midnight green
     * Primary color for UI
     */
    final Color midnightGreen = new Color(9,82,96);

    /**
     * Teal
     * Secondary color for UI
     */
    final Color teal = new Color(8,127,140);

    /**
     * Zomp
     * Secondary color for UI
     */
    final Color zomp = new Color(90,170,149);

    /**
     * Asparagus
     * Primary color for UI
     */
    static final Color asparagus = new Color(134,168,115);

    /**
     * Satin sheen gold
     * Primary color for UI
     */
    final Color satinSheenGold = new Color(187,159,6);

    /**
     * GridBagLayout used for some components.
     */
    final GridBagLayout gridBagLayout = new GridBagLayout();

    /**
     * GridBagConstraints user for all components using the gridBagLayout object.
     */
    final GridBagConstraints gbc = new GridBagConstraints();

    /**
     * CardLayout used for the center panel.
     */
    final CardLayout cardLayout1 = new CardLayout(20,10);

    /**
     * CardLayout used for account details panel.
     */
    final CardLayout cardLayout2 = new CardLayout(30,40);

    /**
     * CardLayout used for the transfer recipient panel
     */
    final CardLayout cardLayout3 = new CardLayout(5,5);

    /**
     * Thin Montserrat font
     */
    Font montserratThin;

    /**
     * Regular Montserrat font
     */
    Font montserrat;

    /**
     * Bold Montserrat font
     */
    Font montserratBold;

    /**
     * Black Montserrat font
     */
    Font montserratBlack;

    /**
     * Empty border that acts as a padding for JPanel components.
     * Normal padding
     */
    EmptyBorder normalPadding = new EmptyBorder(20,30,20,30);
    /**
     * Empty border that acts as a padding for JPanel components.
     * Thin padding
     */
    EmptyBorder thinPadding = new EmptyBorder(10,10,10,10);

    /**
     * Cursor used for buttons
     */
    Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);

    /**
     * Default cursor
     */
    Cursor defaultCursor = new Cursor(Cursor.DEFAULT_CURSOR);

    /**
     * Cursor used for texts
     */
    Cursor textCursor = new Cursor(Cursor.TEXT_CURSOR);

    /**
     * Main entry point of the program
     * @param args command line arguments
     */
    public static void main(String[] args) {
        try {
            run();
            new Main();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } // end of try-catch
    } // end of main method

    /**
     * Folder name of user
     */
    public static String user = "test_user";

    /**
     * The folder of the user used in methods
     */
    public static final String userFolder = "users/" + user;

    /**
     * First bank account of user
     */
    private static Account accountOne;

    /**
     * Second bank account of user
     */
    private static Account accountTwo;

    /**
     * Controls the execution of the program
     */
    private static void run() {
        try {
            bankUtility.readUserAccounts();
            accountOne = bankUtility.accounts.get(0);
            accountTwo = bankUtility.accounts.get(1);
        } catch (FileNotFoundException e1) {
            JOptionPane.showMessageDialog(null, "No bank accounts found.");
        } catch (IOException e2) {
            JOptionPane.showMessageDialog(null,"Error reading user record. Restart app.");
            System.exit(0);
        } catch (Exception exception) {
            exception.printStackTrace();
        } // end of try-catch
    } // end of run method

    /**
     * Loads fonts
     */
    private void loadFonts() {
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

            // Montserrat Thin
            montserratThin = Font.createFont(Font.TRUETYPE_FONT,
                    new File("fonts/Montserrat/static/Montserrat-Thin.ttf")).deriveFont(20f);
            ge.registerFont(montserratThin);

            // Montserrat Regular
            montserrat = Font.createFont(Font.TRUETYPE_FONT,
                    new File("fonts/Montserrat/static/Montserrat-Regular.ttf")).deriveFont(20f);
            ge.registerFont(montserrat);

            // Montserrat Bold
            montserratBold = Font.createFont(Font.TRUETYPE_FONT,
                    new File("fonts/Montserrat/static/Montserrat-Bold.ttf")).deriveFont(20f);
            ge.registerFont(montserratBold);

            // Montserrat Black
            montserratBlack = Font.createFont(Font.TRUETYPE_FONT,
                    new File("fonts/Montserrat/static/Montserrat-Black.ttf")).deriveFont(20f);
            ge.registerFont(montserratBlack);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (FontFormatException fontError) {
            System.out.println(fontError.getMessage());
            fontError.printStackTrace();
        } // end of try-catch
    } // end of run method

    /**
     * Constructs Main with graphical user interface
     */
    public Main() {
        super("Money In Bank");
        setDefaultLookAndFeelDecorated(true);

        loadFonts();

        // Sets the icon image
        ImageIcon logo = new ImageIcon("icons/logo.png");
        setIconImage(logo.getImage());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        add(mainPanel);

        // Left Panel
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());
        leftPanel.setPreferredSize(new Dimension(300, 700));
        mainPanel.add(leftPanel, BorderLayout.LINE_START);

        // Left Panel Components
        // Logo Panel
        JPanel logoPanel = new JPanel();
        logoPanel.setPreferredSize(new Dimension(400,280));
        logoPanel.setLayout(new BorderLayout());
        leftPanel.add(logoPanel, BorderLayout.NORTH);

        // Logo Panel Components
        // Logo Holder Panel
        JPanel logoHolderPanel = new JPanel();
        logoHolderPanel.setBorder(normalPadding);
        logoHolderPanel.setLayout(new BorderLayout());
        logoHolderPanel.setPreferredSize(new Dimension(400,190));
        logoPanel.add(logoHolderPanel, BorderLayout.NORTH);

        // Logo Label
        JLabel logoLabel = new JLabel();
        logoLabel.setIcon(logo);
        logoLabel.setHorizontalAlignment(JLabel.CENTER);
        logoLabel.setVerticalAlignment(JLabel.CENTER);
        logoHolderPanel.add(logoLabel, BorderLayout.CENTER);

        // Introduction Panel
        JPanel introPanel = new JPanel();
        introPanel.setBorder(new EmptyBorder(20,60,20,0));
        introPanel.setLayout(new BorderLayout());
        introPanel.setPreferredSize(new Dimension(400,90));
        logoPanel.add(introPanel, BorderLayout.SOUTH);

        // Intro Label
        JLabel introLabel = new JLabel("Hello,");
        introLabel.setFont(montserrat);
        introLabel.setForeground(Color.BLACK);
        introPanel.add(introLabel, BorderLayout.NORTH);

        JLabel firstNameLabel = new JLabel();
        firstNameLabel.setFont(montserratBold);
        firstNameLabel.setText("<Name>");
        firstNameLabel.setForeground(Color.BLACK);
        introPanel.add(firstNameLabel, BorderLayout.SOUTH);

        // Buttons Panel
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBorder(new EmptyBorder(10,30,5,30));
        buttonsPanel.setLayout(gridBagLayout);
        buttonsPanel.setPreferredSize(new Dimension(400,400));
        leftPanel.add(buttonsPanel, BorderLayout.SOUTH);

        // Buttons Panel Components
        gbc.gridx = 0;
        gbc.gridwidth = 50;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 0, 0, 80);

        // Button Icons
        // Add Account Icon
        ImageIcon accountIcon =
                new ImageIcon("icons/btn/account_balance_FILL0_wght400_GRAD0_opsz48.png");
        ImageIcon scaledAccountIcon = scaleImage(accountIcon, 30,30);

        // Deposit Account Icon
        ImageIcon depositIcon =
                new ImageIcon("icons/btn/savings_FILL0_wght400_GRAD0_opsz48.png");
        ImageIcon scaledDepositIcon = scaleImage(depositIcon, 30,30);

        // Withdraw Icon
        ImageIcon withdrawIcon =
                new ImageIcon("icons/btn/price_change_FILL0_wght400_GRAD0_opsz48.png");
        ImageIcon scaledWithdrawIcon = scaleImage(withdrawIcon, 30,30);

        // Transfer Icon
        ImageIcon transferIcon =
                new ImageIcon("icons/btn/attach_money_FILL0_wght400_GRAD0_opsz48.png");
        ImageIcon scaledTransferIcon = scaleImage(transferIcon, 30,30);

        // Transactions Icon
        ImageIcon transactionsIcon =
                new ImageIcon("icons/btn/receipt_long_FILL0_wght400_GRAD0_opsz48.png");
        ImageIcon scaledTransactionsIcon = scaleImage(transactionsIcon, 30,30);

        // Switch to Wallet Icon
        ImageIcon switchIcon =
                new ImageIcon("icons/btn/account_balance_wallet_FILL0_wght400_GRAD0_opsz48.png");
        ImageIcon scaledSwitchIcon = scaleImage(switchIcon, 30,30);

        // Sign Out Icon
        ImageIcon signOutIcon =
                new ImageIcon("icons/btn/logout_FILL0_wght400_GRAD0_opsz48.png");
        ImageIcon scaledSignOutIcon = scaleImage(signOutIcon, 30,30);

        // Set Budget Icon
        ImageIcon setBudgetIcon =
                new ImageIcon("icons/btn/redeem_FILL0_wght400_GRAD0_opsz48.png");
        ImageIcon scaledSetBudgetIcon = scaleImage(setBudgetIcon, 70,70);

        // Calculate Budget Icon
        ImageIcon calculateBudgetIcon =
                new ImageIcon("icons/btn/calculate_FILL0_wght400_GRAD0_opsz48.png");
        ImageIcon scaledCalculateBudgetIcon = scaleImage(calculateBudgetIcon,70,70);

        // Budget Goals Icon
        ImageIcon budgetGoalsIcon =
                new ImageIcon("icons/btn/bar_chart_FILL0_wght400_GRAD0_opsz48.png");
        ImageIcon scaledBudgetGoalsIcon = scaleImage(budgetGoalsIcon,70,70);

        // Buttons
        // Add Account Button
        gbc.gridy = 0;
        JButton accountButton = new JButton(scaledAccountIcon);
        accountButton.setText("Account");
        accountButton.setFont(montserratBold.deriveFont(15f));
        accountButton.setForeground(Color.BLACK);
        accountButton.setHorizontalAlignment(SwingConstants.LEFT);
        accountButton.setOpaque(false);
        accountButton.setContentAreaFilled(false);
        accountButton.setBorderPainted(false);
        buttonsPanel.add(accountButton, gbc);

        accountButton.addMouseListener(new java.awt.event.MouseAdapter() {
            /**
             * Interaction with the button when the mouse hovers on it
             * @param e the event to be processed
             */
            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(handCursor);

                ImageIcon newIcon =
                        new ImageIcon("icons/btn-hover/account_balance_FILL0_wght400_GRAD0_opsz48.png");
                ImageIcon scaledIcon = scaleImage(newIcon,30,30);
                accountButton.setIcon(scaledIcon);
                accountButton.setFont(montserratBold.deriveFont(17f));
                accountButton.setForeground(asparagus);
            } // end of mouseEntered method

            /**
             * Interaction with the button when the mouse hovers away from it
             * @param e the event to be processed
             */
            @Override
            public void mouseExited(MouseEvent e) {
                setCursor(defaultCursor);

                accountButton.setIcon(scaledAccountIcon);
                accountButton.setFont(montserratBold.deriveFont(15f));
                accountButton.setForeground(Color.BLACK);
            } // end of mouseExited method
        }); // end of mouseListener for accountButton

        // Deposit Button
        gbc.gridy = 1;
        JButton depositButton = new JButton(scaledDepositIcon);
        depositButton.setText("Deposit");
        depositButton.setFont(montserratBold.deriveFont(15f));
        depositButton.setForeground(Color.BLACK);
        depositButton.setHorizontalAlignment(SwingConstants.LEFT);
        depositButton.setOpaque(false);
        depositButton.setContentAreaFilled(false);
        depositButton.setBorderPainted(false);
        buttonsPanel.add(depositButton, gbc);

        depositButton.addMouseListener(new java.awt.event.MouseAdapter() {
            /**
             * Interaction with the button when the mouse hovers on it
             * @param e the event to be processed
             */
            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(handCursor);

                ImageIcon newIcon =
                        new ImageIcon("icons/btn-hover/savings_FILL0_wght400_GRAD0_opsz48.png");
                ImageIcon scaledIcon = scaleImage(newIcon,30,30);
                depositButton.setIcon(scaledIcon);
                depositButton.setFont(montserratBold.deriveFont(17f));
                depositButton.setForeground(asparagus);
            } // end of mouseEntered method

            /**
             * Interaction with the button when the mouse hovers away from it
             * @param e the event to be processed
             */
            @Override
            public void mouseExited(MouseEvent e) {
                setCursor(defaultCursor);

                depositButton.setIcon(scaledDepositIcon);
                depositButton.setFont(montserratBold.deriveFont(15f));
                depositButton.setForeground(Color.BLACK);
            } // end of mouseExited method
        }); // end of mouseListener for depositButton

        // Withdraw Button
        gbc.gridy = 2;
        JButton withdrawButton = new JButton(scaledWithdrawIcon);
        withdrawButton.setText("Withdraw");
        withdrawButton.setFont(montserratBold.deriveFont(15f));
        withdrawButton.setForeground(Color.BLACK);
        withdrawButton.setHorizontalAlignment(SwingConstants.LEFT);
        withdrawButton.setOpaque(false);
        withdrawButton.setContentAreaFilled(false);
        withdrawButton.setBorderPainted(false);
        buttonsPanel.add(withdrawButton, gbc);

        withdrawButton.addMouseListener(new java.awt.event.MouseAdapter() {
            /**
             * Interaction with the button when the mouse hovers on it
             * @param e the event to be processed
             */
            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(handCursor);

                ImageIcon newIcon =
                        new ImageIcon("icons/btn-hover/price_change_FILL0_wght400_GRAD0_opsz48.png");
                ImageIcon scaledIcon = scaleImage(newIcon,30,30);
                withdrawButton.setIcon(scaledIcon);
                withdrawButton.setFont(montserratBold.deriveFont(17f));
                withdrawButton.setForeground(asparagus);
            } // end of mouseEntered method

            /**
             * Interaction with the button when the mouse hovers away from it
             * @param e the event to be processed
             */
            @Override
            public void mouseExited(MouseEvent e) {
                setCursor(defaultCursor);

                withdrawButton.setIcon(scaledWithdrawIcon);
                withdrawButton.setFont(montserratBold.deriveFont(15f));
                withdrawButton.setForeground(Color.BLACK);
            } // end of mouseExited method
        }); // end of mouseListener for withdrawButton

        // Transfer Button
        gbc.gridy = 3;
        JButton transferButton = new JButton(scaledTransferIcon);
        transferButton.setText("Transfer");
        transferButton.setFont(montserratBold.deriveFont(15f));
        transferButton.setForeground(Color.BLACK);
        transferButton.setHorizontalAlignment(SwingConstants.LEFT);
        transferButton.setOpaque(false);
        transferButton.setContentAreaFilled(false);
        transferButton.setBorderPainted(false);
        buttonsPanel.add(transferButton, gbc);

        transferButton.addMouseListener(new java.awt.event.MouseAdapter() {
            /**
             * Interaction with the button when the mouse hovers on it
             * @param e the event to be processed
             */
            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(handCursor);

                ImageIcon newIcon =
                        new ImageIcon("icons/btn-hover/attach_money_FILL0_wght400_GRAD0_opsz48.png");
                ImageIcon scaledIcon = scaleImage(newIcon,30,30);
                transferButton.setIcon(scaledIcon);
                transferButton.setFont(montserratBold.deriveFont(17f));
                transferButton.setForeground(asparagus);
            } // end of mouseEntered method

            /**
             * Interaction with the button when the mouse hovers away from it
             * @param e the event to be processed
             */
            @Override
            public void mouseExited(MouseEvent e) {
                setCursor(defaultCursor);

                transferButton.setIcon(scaledTransferIcon);
                transferButton.setFont(montserratBold.deriveFont(15f));
                transferButton.setForeground(Color.BLACK);
            } // end of mouseExited method
        }); // end of mouseListener for transferButton

        // Transaction Button
        gbc.gridy = 4;
        JButton transactionsButton = new JButton(scaledTransactionsIcon);
        transactionsButton.setText("Transactions");
        transactionsButton.setFont(montserratBold.deriveFont(15f));
        transactionsButton.setForeground(Color.BLACK);
        transactionsButton.setHorizontalAlignment(SwingConstants.LEFT);
        transactionsButton.setOpaque(false);
        transactionsButton.setContentAreaFilled(false);
        transactionsButton.setBorderPainted(false);
        buttonsPanel.add(transactionsButton, gbc);

        transactionsButton.addMouseListener(new java.awt.event.MouseAdapter() {
            /**
             * Interaction with the button when the mouse hovers on it
             * @param e the event to be processed
             */
            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(handCursor);

                ImageIcon newIcon =
                        new ImageIcon("icons/btn-hover/receipt_long_FILL0_wght400_GRAD0_opsz48.png");
                ImageIcon scaledIcon = scaleImage(newIcon,30,30);
                transactionsButton.setIcon(scaledIcon);
                transactionsButton.setFont(montserratBold.deriveFont(17f));
                transactionsButton.setForeground(asparagus);
            } // end of mouseEntered method

            /**
             * Interaction with the button when the mouse hovers away from it
             * @param e the event to be processed
             */
            @Override
            public void mouseExited(MouseEvent e) {
                setCursor(defaultCursor);

                transactionsButton.setIcon(scaledTransactionsIcon);
                transactionsButton.setFont(montserratBold.deriveFont(15f));
                transactionsButton.setForeground(Color.BLACK);
            } // end of mouseExited method
        }); // end of mouseListener for transactionsButton

        // Switch to Wallet Button
        gbc.gridy = 5;
        JButton switchButton = new JButton(scaledSwitchIcon);
        switchButton.setText("Go to Wallet");
        switchButton.setFont(montserratBold.deriveFont(15f));
        switchButton.setForeground(Color.BLACK);
        switchButton.setHorizontalAlignment(SwingConstants.LEFT);
        switchButton.setOpaque(false);
        switchButton.setContentAreaFilled(false);
        switchButton.setBorderPainted(false);
        buttonsPanel.add(switchButton, gbc);

        switchButton.addMouseListener(new java.awt.event.MouseAdapter() {
            /**
             * Interaction with the button when the mouse hovers on it
             * @param e the event to be processed
             */
            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(handCursor);

                ImageIcon newIcon =
                        new ImageIcon("icons/btn-hover/account_balance_wallet_FILL0_wght400_GRAD0_opsz48.png");
                ImageIcon scaledIcon = scaleImage(newIcon,30,30);
                switchButton.setIcon(scaledIcon);
                switchButton.setFont(montserratBold.deriveFont(17f));
                switchButton.setForeground(asparagus);
            } // end of mouseEntered method

            /**
             * Interaction with the button when the mouse hovers away from it
             * @param e the event to be processed
             */
            @Override
            public void mouseExited(MouseEvent e) {
                setCursor(defaultCursor);

                switchButton.setIcon(scaledSwitchIcon);
                switchButton.setFont(montserratBold.deriveFont(15f));
                switchButton.setForeground(Color.BLACK);
            } // end of mouseExited method
        }); // end of mouseListener for switchButton

        // Sign Out Button
        gbc.gridy = 6;
        JButton signOutButton = new JButton(scaledSignOutIcon);
        signOutButton.setText("Sign Out");
        signOutButton.setFont(montserratBold.deriveFont(15f));
        signOutButton.setForeground(Color.BLACK);
        signOutButton.setHorizontalAlignment(SwingConstants.LEFT);
        signOutButton.setOpaque(false);
        signOutButton.setContentAreaFilled(false);
        signOutButton.setBorderPainted(false);
        buttonsPanel.add(signOutButton, gbc);

        signOutButton.addMouseListener(new java.awt.event.MouseAdapter() {
            /**
             * Interaction with the button when the mouse hovers on it
             * @param e the event to be processed
             */
            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(handCursor);

                ImageIcon newIcon =
                        new ImageIcon("icons/btn-hover/logout_FILL0_wght400_GRAD0_opsz48.png");
                ImageIcon scaledIcon = scaleImage(newIcon,30,30);
                signOutButton.setIcon(scaledIcon);
                signOutButton.setFont(montserratBold.deriveFont(17f));
                signOutButton.setForeground(asparagus);
            } // end of mouseEntered method

            /**
             * Interaction with the button when the mouse hovers away from it
             * @param e the event to be processed
             */
            @Override
            public void mouseExited(MouseEvent e) {
                setCursor(defaultCursor);

                signOutButton.setIcon(scaledSignOutIcon);
                signOutButton.setFont(montserratBold.deriveFont(15f));
                signOutButton.setForeground(Color.BLACK);
            } // end of mouseExited method
        }); // end of mouseListener for signOutButton

        // Center Panel
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setSize(900,700);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // Center Panel Components
        // Card Panel
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(cardLayout1);
        cardPanel.setBackground(Color.WHITE);
        cardPanel.setPreferredSize(new Dimension(900,500));
        centerPanel.add(cardPanel, BorderLayout.NORTH);

        // Card 1 Components
        // Account Panel
        JPanel accountPanel = new JPanel();
        accountPanel.setLayout(new BorderLayout());
        accountPanel.setBackground(Color.WHITE);
        accountPanel.setPreferredSize(new Dimension(900,400));
        cardPanel.add(accountPanel, "1");

        // Left Button Panel
        JPanel leftButtonPanel = new JPanel();
        leftButtonPanel.setLayout(gridBagLayout);
        leftButtonPanel.setBackground(Color.white);
        leftButtonPanel.setPreferredSize( new Dimension(100,500));
        accountPanel.add(leftButtonPanel, BorderLayout.LINE_START);

        // Button Panel Constraints
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.insets = new Insets(10, 20, 10, 20);

        // Left Button Components
        ImageIcon leftArrowIcon = new ImageIcon("icons/btn/arrow_back_ios_FILL0_wght400_GRAD0_opsz48.png");

        JButton leftArrowButton = new JButton();
        leftArrowButton.setIcon(leftArrowIcon);
        leftArrowButton.setHorizontalAlignment(SwingConstants.CENTER);
        leftArrowButton.setVerticalAlignment(SwingConstants.CENTER);
        leftArrowButton.setOpaque(false);
        leftArrowButton.setContentAreaFilled(false);
        leftArrowButton.setBorderPainted(false);
        leftButtonPanel.add(leftArrowButton, gbc);

        // Right Button Panel
        JPanel rightButtonPanel = new JPanel();
        rightButtonPanel.setLayout(gridBagLayout);
        rightButtonPanel.setBackground(Color.WHITE);
        rightButtonPanel.setPreferredSize( new Dimension(100,500));
        accountPanel.add(rightButtonPanel, BorderLayout.LINE_END);

        // Right Button Components
        ImageIcon rightArrowIcon = new ImageIcon("icons/btn/arrow_forward_ios_FILL0_wght400_GRAD0_opsz48.png");

        JButton rightArrowButton = new JButton();
        rightArrowButton.setIcon(rightArrowIcon);
        rightArrowButton.setHorizontalAlignment(SwingConstants.CENTER);
        rightArrowButton.setVerticalAlignment(SwingConstants.CENTER);
        rightArrowButton.setOpaque(false);
        rightArrowButton.setContentAreaFilled(false);
        rightArrowButton.setBorderPainted(false);
        rightButtonPanel.add(rightArrowButton, gbc);

        // Account Card Panel
        JPanel accountCardPanel = new JPanel();
        accountCardPanel.setLayout(cardLayout2);
        accountCardPanel.setBackground(asparagus);
        accountCardPanel.setPreferredSize(new Dimension(400, 200));
        accountPanel.add(accountCardPanel, BorderLayout.CENTER);

        // Account Card Panel Components
        // Adds Account Cards of user's existing bank accounts
        if (bankUtility.accounts != null) {
            for (Account account : bankUtility.accounts) {
                accountCardPanel.add(addAccountCardPanel(account), BorderLayout.CENTER);
            } // end of for
        } // end of if

        // Add Account Panel
        JPanel addAccountPanel = new JPanel();
        addAccountPanel.setLayout(gridBagLayout);
        addAccountPanel.setBackground(asparagus);
        addAccountPanel.setPreferredSize(new Dimension(400, 200));
        accountCardPanel.add(addAccountPanel, BorderLayout.CENTER);

        gbc.gridx = 0;
        gbc.gridwidth = 50;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5,10,0,10);

        // Add Account Panel Components
        // Add Account Button
        ImageIcon addAccountIcon = new ImageIcon("icons/btn/icons8-add-50.png");
        ImageIcon scaledAddAccountIcon = scaleImage(addAccountIcon,40,40);

        gbc.gridy = 0;
        JButton addAccountButton = new JButton();
        addAccountButton.setIcon(scaledAddAccountIcon);
        addAccountButton.setText("Add Account");
        addAccountButton.setFont(montserrat.deriveFont(17.5f));
        addAccountButton.setForeground(Color.WHITE);
        addAccountButton.setBorderPainted(false);
        addAccountButton.setOpaque(false);
        addAccountButton.setContentAreaFilled(false);
        addAccountPanel.add(addAccountButton,gbc);

        addAccountButton.addMouseListener(new java.awt.event.MouseAdapter() {
            /**
             * Interaction with the button when the mouse hovers on it
             * @param e the event to be processed
             */
            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(handCursor);

                ImageIcon zoomedIcon = scaleImage(addAccountIcon,42,42);
                addAccountButton.setIcon(zoomedIcon);
                addAccountButton.setFont(montserrat.deriveFont(19.5f));
            } // end of mouseEntered method

            /**
             * Interaction with the button when the mouse hovers away from it
             * @param e the event to be processed
             */
            @Override
            public void mouseExited(MouseEvent e) {
                setCursor(defaultCursor);

                addAccountButton.setIcon(scaledAddAccountIcon);
                addAccountButton.setFont(montserrat.deriveFont(17.5f));
            } // end of mouseExited method
        }); // end of mouseListener for addAccountButton

        // Bottom Panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.setBackground(zomp);
        bottomPanel.setPreferredSize(new Dimension(800,140));
        bottomPanel.setBorder(normalPadding);
        centerPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Bottom Panel Components
        // Buttons Panel
        JPanel budgetButtonsPanel = new JPanel();
        budgetButtonsPanel.setLayout(new FlowLayout());
        budgetButtonsPanel.setBackground(zomp);
        budgetButtonsPanel.setPreferredSize(new Dimension(800,140));
        budgetButtonsPanel.setBorder(thinPadding);
        bottomPanel.add(budgetButtonsPanel, BorderLayout.CENTER);

        // Buttons Panel Components
        // Set Budget
        JButton setBudgetButton = new JButton();
        setBudgetButton.setIcon(scaledSetBudgetIcon);
        setBudgetButton.setText("Set Budget");
        setBudgetButton.setFont(montserratBold.deriveFont(15f));
        setBudgetButton.setForeground(Color.BLACK);
        setBudgetButton.setVerticalAlignment(SwingConstants.CENTER);
        setBudgetButton.setOpaque(false);
        setBudgetButton.setContentAreaFilled(false);
        setBudgetButton.setBorderPainted(false);
        budgetButtonsPanel.add(setBudgetButton);

        // Calculate Budget
        JButton calculateBudgetButton = new JButton();
        calculateBudgetButton.setIcon(scaledCalculateBudgetIcon);
        calculateBudgetButton.setText("Calculate Budget");
        calculateBudgetButton.setFont(montserratBold.deriveFont(15f));
        calculateBudgetButton.setForeground(Color.BLACK);
        calculateBudgetButton.setVerticalAlignment(SwingConstants.CENTER);
        calculateBudgetButton.setOpaque(false);
        calculateBudgetButton.setContentAreaFilled(false);
        calculateBudgetButton.setBorderPainted(false);
        budgetButtonsPanel.add(calculateBudgetButton);

        // Budget Goals
        JButton budgetGoalsButton = new JButton();
        budgetGoalsButton.setIcon(scaledBudgetGoalsIcon);
        budgetGoalsButton.setText("Budget Goals");
        budgetGoalsButton.setFont(montserratBold.deriveFont(15f));
        budgetGoalsButton.setForeground(Color.BLACK);
        budgetGoalsButton.setVerticalAlignment(SwingConstants.CENTER);
        budgetGoalsButton.setOpaque(false);
        budgetGoalsButton.setContentAreaFilled(false);
        budgetGoalsButton.setBorderPainted(false);
        budgetButtonsPanel.add(budgetGoalsButton);

        // Copyright Label
        JLabel copyrightLabel = new JLabel();
        copyrightLabel.setText("<html><center>© 2023 ROXAS, JOHAN RICKARDO <br><center>MONEY IN BANK VERSION 1.00");
        copyrightLabel.setFont(new Font("Helvetica" , Font.PLAIN, 10));
        copyrightLabel.setForeground(Color.BLACK);
        copyrightLabel.setVerticalTextPosition(JLabel.CENTER);
        copyrightLabel.setHorizontalAlignment(JLabel.CENTER);
        bottomPanel.add(copyrightLabel, BorderLayout.SOUTH);

        // Card 2 Components
        // Deposit Panel
        JPanel depositPanel = new JPanel();
        depositPanel.setLayout(gridBagLayout);
        depositPanel.setBackground(Color.WHITE);
        depositPanel.setPreferredSize(new Dimension(900,700));
        cardPanel.add(depositPanel, "2");

        // Deposit Panel Components
        gbc.gridwidth = 50;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 20, 10, 20);

        // Account Selector Label
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel depositAccountLabel = new JLabel();
        depositAccountLabel.setText("Select Account:");
        depositAccountLabel.setFont(montserrat.deriveFont(17.5f));
        depositAccountLabel.setForeground(Color.BLACK);
        depositAccountLabel.setHorizontalAlignment(JLabel.CENTER);
        depositPanel.add(depositAccountLabel, gbc);

        // Account Selector Combo Box
        gbc.gridwidth = 50;
        gbc.gridx = 0;
        gbc.gridy = 1;
        JComboBox<Account> depositAccountComboBox = new JComboBox<>();
        depositAccountComboBox.setFont(montserrat.deriveFont(17.5f));
        depositAccountComboBox.setForeground(Color.BLACK);
        depositAccountComboBox.setBackground(Color.WHITE);
        depositPanel.add(depositAccountComboBox, gbc);

        depositAccountComboBox.addItem(accountOne);
        depositAccountComboBox.addItem(accountTwo);
        depositAccountComboBox.setSelectedItem(null);

        // Account Details Panel
        gbc.gridx = 0;
        gbc.gridy = 2;
        JPanel depositAccountPanel = new JPanel();
        depositAccountPanel.setLayout(gridBagLayout);
        depositAccountPanel.setBackground(asparagus);
        depositAccountPanel.setPreferredSize(new Dimension(600,200));
        depositPanel.add(depositAccountPanel, gbc);

        // Account Details Components
        // Balance
        gbc.insets = new Insets(5, 10, 0, 10);
        gbc.gridy = 0;
        JLabel depositBalanceLabel = new JLabel();
        depositBalanceLabel.setText("SELECT ACCOUNT");
        depositBalanceLabel.setFont(montserratBlack.deriveFont(40f));
        depositBalanceLabel.setForeground(Color.WHITE);
        depositBalanceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        depositBalanceLabel.setVerticalAlignment(SwingConstants.CENTER);
        depositAccountPanel.add(depositBalanceLabel, gbc);

        // Account Name
        gbc.gridy = 1;
        JLabel depositAccountNameLabel = new JLabel();
        depositAccountNameLabel.setFont(montserrat.deriveFont(17.5f));
        depositAccountNameLabel.setForeground(Color.WHITE);
        depositAccountNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        depositAccountNameLabel.setVerticalAlignment(SwingConstants.CENTER);
        depositAccountPanel.add(depositAccountNameLabel, gbc);

        // Account Number
        gbc.gridy = 2;
        JLabel depositAccountNumberLabel = new JLabel();
        depositAccountNumberLabel.setFont(montserrat.deriveFont(17.5f));
        depositAccountNumberLabel.setForeground(Color.WHITE);
        depositAccountNumberLabel.setHorizontalAlignment(SwingConstants.CENTER);
        depositAccountNumberLabel.setVerticalAlignment(SwingConstants.CENTER);
        depositAccountPanel.add(depositAccountNumberLabel, gbc);

        // Deposit Amount Label
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel depositAmountLabel = new JLabel();
        depositAmountLabel.setText("Amount:");
        depositAmountLabel.setFont(montserrat.deriveFont(17.5f));
        depositAmountLabel.setForeground(Color.BLACK);
        depositAmountLabel.setHorizontalAlignment(SwingConstants.CENTER);
        depositPanel.add(depositAmountLabel, gbc);

        // Deposit Amount Text Field
        gbc.gridwidth = 2;
        gbc.gridx = 1;
        gbc.gridy = 3;
        JTextField depositAmountTextField = new JTextField();
        depositAmountTextField.setColumns(28);
        depositAmountTextField.setFont(montserrat.deriveFont(17.5f));
        depositAmountTextField.setForeground(Color.BLACK);
        depositAmountTextField.setCursor(textCursor);
        depositAmountTextField.setEnabled(false);
        depositPanel.add(depositAmountTextField, gbc);

        // Source Label
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 4;
        JLabel depositSourceLabel = new JLabel();
        depositSourceLabel.setText("Source:");
        depositSourceLabel.setFont(montserrat.deriveFont(17.5f));
        depositSourceLabel.setForeground(Color.BLACK);
        depositPanel.add(depositSourceLabel, gbc);

        // Radio Panel
        gbc.gridwidth = 1;
        gbc.gridx = 1;
        gbc.gridy = 4;
        JPanel depositRadioPanel = new JPanel();
        depositRadioPanel.setLayout(new FlowLayout());
        depositRadioPanel.setBackground(Color.WHITE);
        depositPanel.add(depositRadioPanel, gbc);

        // Radio Panel Components
        // Cash Radio
        JRadioButton cashRadioButton = new JRadioButton();
        cashRadioButton.setText("Cash from Wallet");
        cashRadioButton.setFont(montserratBold.deriveFont(15f));
        cashRadioButton.setForeground(Color.BLACK);
        cashRadioButton.setAlignmentX(SwingConstants.WEST);
        depositRadioPanel.add(cashRadioButton);

        // Other Radio
        JRadioButton otherRadioButton = new JRadioButton();
        otherRadioButton.setText("Other");
        otherRadioButton.setFont(montserratBold.deriveFont(15f));
        otherRadioButton.setForeground(Color.BLACK);
        otherRadioButton.setAlignmentX(SwingConstants.WEST);
        depositRadioPanel.add(otherRadioButton);

        ButtonGroup depositRadioGroup = new ButtonGroup();
        depositRadioGroup.add(cashRadioButton);
        depositRadioGroup.add(otherRadioButton);

        // Separator
        gbc.gridwidth = 50;
        gbc.gridx = 0;
        gbc.gridy = 5;
        JSeparator s2 = new JSeparator();
        s2.setForeground(Color.BLACK);
        s2.setOrientation(SwingConstants.HORIZONTAL);
        s2.setPreferredSize(new Dimension(300, 2));
        depositPanel.add(s2, gbc);

        // Buttons Panel
        gbc.gridy = 6;
        JPanel depositButtonsPanel = new JPanel();
        depositButtonsPanel.setLayout(new FlowLayout());
        depositButtonsPanel.setBackground(Color.WHITE);
        depositButtonsPanel.setAlignmentX(SwingConstants.EAST);
        depositPanel.add(depositButtonsPanel, gbc);

        // Deposit Amount
        // Clear Button
        JButton clearDepositButton = new JButton();
        clearDepositButton.setText("Clear");
        clearDepositButton.setFont(montserratBold.deriveFont(15f));
        clearDepositButton.setForeground(Color.WHITE);
        clearDepositButton.setBackground(teal);
        clearDepositButton.setVerticalAlignment(SwingConstants.CENTER);
        depositButtonsPanel.add(clearDepositButton);

        // Deposit Button
        JButton acceptDepositButton = new JButton();
        acceptDepositButton.setText("Deposit");
        acceptDepositButton.setFont(montserratBold.deriveFont(15f));
        acceptDepositButton.setForeground(Color.WHITE);
        acceptDepositButton.setBackground(satinSheenGold);
        acceptDepositButton.setVerticalAlignment(SwingConstants.CENTER);
        acceptDepositButton.setEnabled(false);
        depositButtonsPanel.add(acceptDepositButton);

        // Card 3 Components
        // Withdraw Panel
        JPanel withdrawPanel = new JPanel();
        withdrawPanel.setLayout(gridBagLayout);
        withdrawPanel.setBackground(Color.WHITE);
        withdrawPanel.setPreferredSize(new Dimension(900,700));
        cardPanel.add(withdrawPanel, "3");

        // Withdraw Panel Components
        gbc.gridwidth = 50;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 20, 10, 20);

        // Account Selector Label
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel withdrawAccountLabel = new JLabel();
        withdrawAccountLabel.setText("Select Account:");
        withdrawAccountLabel.setFont(montserrat.deriveFont(17.5f));
        withdrawAccountLabel.setForeground(Color.BLACK);
        withdrawAccountLabel.setHorizontalAlignment(JLabel.CENTER);
        withdrawPanel.add(withdrawAccountLabel, gbc);

        // Account Selector Combo Box
        gbc.gridx = 0;
        gbc.gridy = 1;
        JComboBox<Account> withdrawAccountComboBox = new JComboBox<>();
        withdrawAccountComboBox.setFont(montserrat.deriveFont(17.5f));
        withdrawAccountComboBox.setBackground(Color.WHITE);
        withdrawAccountComboBox.setForeground(Color.BLACK);
        withdrawPanel.add(withdrawAccountComboBox, gbc);

        withdrawAccountComboBox.addItem(accountOne);
        withdrawAccountComboBox.addItem(accountTwo);
        withdrawAccountComboBox.setSelectedItem(null);

        // Account Details Panel
        gbc.gridwidth = 50;
        gbc.gridx = 0;
        gbc.gridy = 2;
        JPanel withdrawAccountPanel = new JPanel();
        withdrawAccountPanel.setLayout(gridBagLayout);
        withdrawAccountPanel.setBackground(asparagus);
        withdrawAccountPanel.setPreferredSize(new Dimension(600,200));
        withdrawPanel.add(withdrawAccountPanel, gbc);

        // Account Details Components
        // Balance
        gbc.insets = new Insets(10, 10, 0, 10);
        gbc.gridy = 0;
        JLabel withdrawBalanceLabel = new JLabel();
        withdrawBalanceLabel.setText("SELECT ACCOUNT");
        withdrawBalanceLabel.setFont(montserratBlack.deriveFont(40f));
        withdrawBalanceLabel.setForeground(Color.WHITE);
        withdrawBalanceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        withdrawBalanceLabel.setVerticalAlignment(SwingConstants.CENTER);
        withdrawAccountPanel.add(withdrawBalanceLabel, gbc);

        // Account Name
        gbc.gridy = 1;
        JLabel withdrawAccountNameLabel = new JLabel();
        withdrawAccountNameLabel.setFont(montserrat.deriveFont(17.5f));
        withdrawAccountNameLabel.setForeground(Color.WHITE);
        withdrawAccountNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        withdrawAccountNameLabel.setVerticalAlignment(SwingConstants.CENTER);
        withdrawAccountPanel.add(withdrawAccountNameLabel, gbc);

        // Account Number
        gbc.gridy = 2;
        JLabel withdrawAccountNumberLabel = new JLabel();
        withdrawAccountNumberLabel.setFont(montserrat.deriveFont(17.5f));
        withdrawAccountNumberLabel.setForeground(Color.WHITE);
        withdrawAccountNumberLabel.setHorizontalAlignment(SwingConstants.CENTER);
        withdrawAccountNumberLabel.setVerticalAlignment(SwingConstants.CENTER);
        withdrawAccountPanel.add(withdrawAccountNumberLabel, gbc);

        // Withdraw Amount Label
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel withdrawAmountLabel = new JLabel();
        withdrawAmountLabel.setText("Amount:");
        withdrawAmountLabel.setFont(montserrat.deriveFont(17.5f));
        withdrawAmountLabel.setForeground(Color.BLACK);
        withdrawAmountLabel.setHorizontalAlignment(SwingConstants.CENTER);
        withdrawPanel.add(withdrawAmountLabel, gbc);

        // Withdraw Amount Text Field
        gbc.gridwidth = 2;
        gbc.gridx = 1;
        gbc.gridy = 3;
        JTextField withdrawAmountTextField = new JTextField();
        withdrawAmountTextField.setColumns(28);
        withdrawAmountTextField.setFont(montserrat.deriveFont(17.5f));
        withdrawAmountTextField.setForeground(Color.BLACK);
        withdrawAmountTextField.setCursor(textCursor);
        withdrawAmountTextField.setEnabled(false);
        withdrawPanel.add(withdrawAmountTextField, gbc);

        // Withdraw Amounts Panel
        gbc.gridwidth = 50;
        gbc.gridx = 0;
        gbc.gridy = 4;
        JPanel withdrawAmountsPanel = new JPanel();
        withdrawAmountsPanel.setLayout(new FlowLayout());
        withdrawAmountsPanel.setBackground(Color.WHITE);
        withdrawAmountsPanel.setAlignmentX(SwingConstants.EAST);
        withdrawAmountsPanel.setPreferredSize(new Dimension(600,50));
        withdrawPanel.add(withdrawAmountsPanel, gbc);

        // 500
        JButton fiveHundredButton = new JButton();
        fiveHundredButton.setText("₱500");
        fiveHundredButton.setFont(montserratBold.deriveFont(15f));
        fiveHundredButton.setBackground(midnightGreen);
        fiveHundredButton.setForeground(Color.WHITE);
        fiveHundredButton.setVerticalAlignment(SwingConstants.CENTER);
        fiveHundredButton.setEnabled(false);
        withdrawAmountsPanel.add(fiveHundredButton);

        // 1000
        JButton oneThousandButton = new JButton();
        oneThousandButton.setText("₱1000");
        oneThousandButton.setFont(montserratBold.deriveFont(15f));
        oneThousandButton.setBackground(midnightGreen);
        oneThousandButton.setForeground(Color.WHITE);
        oneThousandButton.setVerticalAlignment(SwingConstants.CENTER);
        oneThousandButton.setEnabled(false);
        withdrawAmountsPanel.add(oneThousandButton);

        // 1500
        JButton oneThousandFiveButton = new JButton();
        oneThousandFiveButton.setText("₱1500");
        oneThousandFiveButton.setFont(montserratBold.deriveFont(15f));
        oneThousandFiveButton.setBackground(midnightGreen);
        oneThousandFiveButton.setForeground(Color.WHITE);
        oneThousandFiveButton.setVerticalAlignment(SwingConstants.CENTER);
        oneThousandFiveButton.setEnabled(false);
        withdrawAmountsPanel.add(oneThousandFiveButton);

        // 2000
        JButton twoThousandButton = new JButton();
        twoThousandButton.setText("₱2000");
        twoThousandButton.setFont(montserratBold.deriveFont(15f));
        twoThousandButton.setBackground(midnightGreen);
        twoThousandButton.setForeground(Color.WHITE);
        twoThousandButton.setVerticalAlignment(SwingConstants.CENTER);
        twoThousandButton.setEnabled(false);
        withdrawAmountsPanel.add(twoThousandButton);

        // 3000
        JButton threeThousandButton = new JButton();
        threeThousandButton.setText("₱3000");
        threeThousandButton.setFont(montserratBold.deriveFont(15f));
        threeThousandButton.setBackground(midnightGreen);
        threeThousandButton.setForeground(Color.WHITE);
        threeThousandButton.setVerticalAlignment(SwingConstants.CENTER);
        threeThousandButton.setEnabled(false);
        withdrawAmountsPanel.add(threeThousandButton);

        // 5000
        JButton fiveThousandButton = new JButton();
        fiveThousandButton.setText("₱5000");
        fiveThousandButton.setFont(montserratBold.deriveFont(15f));
        fiveThousandButton.setBackground(midnightGreen);
        fiveThousandButton.setForeground(Color.WHITE);
        fiveThousandButton.setVerticalAlignment(SwingConstants.CENTER);
        fiveThousandButton.setEnabled(false);
        withdrawAmountsPanel.add(fiveThousandButton);

        // Separator
        gbc.gridwidth = 50;
        gbc.gridx = 0;
        gbc.gridy = 5;
        JSeparator s3 = new JSeparator();
        s3.setForeground(Color.BLACK);
        s3.setOrientation(SwingConstants.HORIZONTAL);
        s3.setPreferredSize(new Dimension(400,2));
        withdrawPanel.add(s3,gbc);

        // Buttons Panel
        gbc.gridy = 6;
        JPanel withdrawButtonsPanel = new JPanel();
        withdrawButtonsPanel.setLayout(new FlowLayout());
        withdrawButtonsPanel.setBackground(Color.WHITE);
        withdrawButtonsPanel.setAlignmentX(SwingConstants.EAST);
        withdrawPanel.add(withdrawButtonsPanel, gbc);

        // Buttons Panel Components
        // Clear Button
        JButton clearWithdrawalButton = new JButton();
        clearWithdrawalButton.setText("Clear");
        clearWithdrawalButton.setFont(montserratBold.deriveFont(15f));
        clearWithdrawalButton.setBackground(teal);
        clearWithdrawalButton.setForeground(Color.WHITE);
        clearWithdrawalButton.setVerticalAlignment(SwingConstants.CENTER);
        withdrawButtonsPanel.add(clearWithdrawalButton);

        // Withdraw Button
        JButton acceptWithdrawalButton = new JButton();
        acceptWithdrawalButton.setText("Withdraw");
        acceptWithdrawalButton.setFont(montserratBold.deriveFont(15f));
        acceptWithdrawalButton.setBackground(satinSheenGold);
        acceptWithdrawalButton.setForeground(Color.WHITE);
        acceptWithdrawalButton.setVerticalAlignment(SwingConstants.CENTER);
        acceptWithdrawalButton.setEnabled(false);
        withdrawButtonsPanel.add(acceptWithdrawalButton);

        // Card 4 Components
        // Transfer Panel
        JPanel transferPanel = new JPanel();
        transferPanel.setLayout(cardLayout3);
        transferPanel.setBackground(Color.WHITE);
        cardPanel.add(transferPanel, "4");

        // Transfer Panel Components
        // Step 1
        // Step 1 Panel
        JPanel transferOnePanel = new JPanel();
        transferOnePanel.setLayout(gridBagLayout);
        transferOnePanel.setBackground(Color.WHITE);
        transferOnePanel.setPreferredSize(new Dimension(900,700));
        transferPanel.add(transferOnePanel, "t1");

        // Step 1 Panel Components
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10,20,10,20);

        // Step 1 Label
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel stepOneLabel = new JLabel();
        stepOneLabel.setText("Step 1 of 3");
        stepOneLabel.setFont(montserratBold.deriveFont(20f));
        stepOneLabel.setForeground(Color.BLACK);
        transferOnePanel.add(stepOneLabel, gbc);

        // Source Account Selector Label
        gbc.gridwidth = 50;
        gbc.gridy = 1;
        JLabel sourceAccountLabel = new JLabel();
        sourceAccountLabel.setText("Transfer from:");
        sourceAccountLabel.setFont(montserrat.deriveFont(17.5f));
        sourceAccountLabel.setForeground(Color.BLACK);
        sourceAccountLabel.setHorizontalAlignment(JLabel.CENTER);
        transferOnePanel.add(sourceAccountLabel, gbc);

        // Source Account Selector Combo Box
        gbc.gridy = 2;
        JComboBox<Account> transferSourceComboBox = new JComboBox<>();
        transferSourceComboBox.setFont(montserrat.deriveFont(17.5f));
        depositAccountComboBox.setBackground(Color.WHITE);
        transferSourceComboBox.setForeground(Color.BLACK);
        transferOnePanel.add(transferSourceComboBox, gbc);

        transferSourceComboBox.addItem(accountOne);
        transferSourceComboBox.addItem(accountTwo);
        transferSourceComboBox.setSelectedItem(null);

        // Source Account Details Panel
        gbc.gridy = 3;
        JPanel transferSourceDetailsPanel = new JPanel();
        transferSourceDetailsPanel.setLayout(gridBagLayout);
        transferSourceDetailsPanel.setBackground(asparagus);
        transferSourceDetailsPanel.setPreferredSize(new Dimension(600,200));
        transferOnePanel.add(transferSourceDetailsPanel, gbc);

        // Source Account Details Components
        gbc.insets = new Insets(10,10,0,10);
        gbc.gridy = 0;
        JLabel sourceBalanceLabel = new JLabel();
        sourceBalanceLabel.setText("SELECT ACCOUNT");
        sourceBalanceLabel.setFont(montserratBlack.deriveFont(40f));
        sourceBalanceLabel.setForeground(Color.WHITE);
        sourceBalanceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        sourceBalanceLabel.setVerticalAlignment(SwingConstants.CENTER);
        transferSourceDetailsPanel.add(sourceBalanceLabel, gbc);

        // Source Account Name
        gbc.gridy = 1;
        JLabel sourceAccountNameLabel = new JLabel();
        sourceAccountNameLabel.setFont(montserrat.deriveFont(17.5f));
        sourceAccountNameLabel.setForeground(Color.WHITE);
        sourceAccountNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        sourceAccountNameLabel.setVerticalAlignment(SwingConstants.CENTER);
        transferSourceDetailsPanel.add(sourceAccountNameLabel, gbc);

        // Source Account Number
        gbc.gridy = 2;
        JLabel sourceAccountNumberLabel = new JLabel();
        sourceAccountNumberLabel.setFont(montserrat.deriveFont(17.5f));
        sourceAccountNumberLabel.setForeground(Color.WHITE);
        sourceAccountNumberLabel.setHorizontalAlignment(SwingConstants.CENTER);
        sourceAccountNumberLabel.setVerticalAlignment(SwingConstants.CENTER);
        transferSourceDetailsPanel.add(sourceAccountNumberLabel, gbc);

        // Step 1 Next Button
        gbc.gridy = 6;
        JButton transferOneNextButton = new JButton();
        transferOneNextButton.setText("Next");
        transferOneNextButton.setFont(montserratBold.deriveFont(15f));
        transferOneNextButton.setBackground(satinSheenGold);
        transferOneNextButton.setForeground(Color.WHITE);
        transferOneNextButton.setVerticalAlignment(SwingConstants.CENTER);
        transferOneNextButton.setEnabled(false);
        transferOnePanel.add(transferOneNextButton, gbc);

        // Step 2
        // Step 2 Panel
        JPanel transferTwoPanel = new JPanel();
        transferTwoPanel.setLayout(gridBagLayout);
        transferTwoPanel.setBackground(Color.WHITE);
        transferTwoPanel.setPreferredSize(new Dimension(900,700));
        transferPanel.add(transferTwoPanel, "t2");

        // Step 2 Panel Components
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5,10,5,10);

        // Step 2 Label
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel stepTwoLabel = new JLabel();
        stepTwoLabel.setText("Step 2 of 3");
        stepTwoLabel.setFont(montserratBold.deriveFont(20f));
        stepTwoLabel.setForeground(Color.BLACK);
        transferTwoPanel.add(stepTwoLabel, gbc);

        // Recipient Account Selector Label
        gbc.gridwidth = 50;
        gbc.gridy = 1;
        JLabel recipientAccountLabel = new JLabel();
        recipientAccountLabel.setText("Transfer to:");
        recipientAccountLabel.setFont(montserrat.deriveFont(17.5f));
        recipientAccountLabel.setForeground(Color.BLACK);
        recipientAccountLabel.setHorizontalAlignment(JLabel.CENTER);
        transferTwoPanel.add(recipientAccountLabel, gbc);

        // Recipient Account Radio Panel
        gbc.gridwidth = 1;
        gbc.gridy = 2;
        JPanel transferRadioPanel = new JPanel();
        transferRadioPanel.setLayout(new FlowLayout());
        transferRadioPanel.setBackground(Color.WHITE);
        transferTwoPanel.add(transferRadioPanel, gbc);

        // Transfer Radio Panel Components
        // Own Account
        JRadioButton ownAccountRadio = new JRadioButton();
        ownAccountRadio.setText("Own Account");
        ownAccountRadio.setFont(montserratBold.deriveFont(15f));
        ownAccountRadio.setForeground(Color.BLACK);
        transferRadioPanel.add(ownAccountRadio);

        // Another
        JRadioButton anotherRadio = new JRadioButton();
        anotherRadio.setText("Another");
        anotherRadio.setFont(montserratBold.deriveFont(15f));
        anotherRadio.setForeground(Color.BLACK);
        transferRadioPanel.add(anotherRadio);

        // Third-Party
        JRadioButton thirdPartyRadio = new JRadioButton();
        thirdPartyRadio.setText("Third Party");
        thirdPartyRadio.setFont(montserratBold.deriveFont(15f));
        thirdPartyRadio.setForeground(Color.BLACK);
        transferRadioPanel.add(thirdPartyRadio);

        ButtonGroup transferRadioGroup = new ButtonGroup();
        transferRadioGroup.add(ownAccountRadio);
        transferRadioGroup.add(anotherRadio);
        transferRadioGroup.add(thirdPartyRadio);

        // Recipient Panel
        gbc.gridwidth = 50;
        gbc.gridy = 3;
        JPanel recipientPanel = new JPanel();
        recipientPanel.setLayout(cardLayout3);
        recipientPanel.setBackground(Color.WHITE);
        recipientPanel.setPreferredSize(new Dimension(900, 500));
        transferTwoPanel.add(recipientPanel, gbc);

        // Own Account Panel
        JPanel ownAccountPanel = new JPanel();
        ownAccountPanel.setLayout(gridBagLayout);
        ownAccountPanel.setBackground(Color.WHITE);
        ownAccountPanel.setPreferredSize(new Dimension(900,500));
        recipientPanel.add(ownAccountPanel);

        // Own Account Panel Components
        // Own Account Selector
        gbc.gridwidth = 50;
        gbc.gridy = 0;
        JLabel ownAccountLabel = new JLabel();
        ownAccountLabel.setText("Select Account:");
        ownAccountLabel.setFont(montserrat.deriveFont(17.5f));
        ownAccountLabel.setForeground(Color.BLACK);
        ownAccountPanel.add(ownAccountLabel, gbc);

        // Own Account Selector Combo Box
        gbc.gridy = 1;
        JComboBox<String> ownAccountComboBox = new JComboBox<>();
        ownAccountComboBox.setFont(montserrat.deriveFont(17.5f));
        ownAccountComboBox.setForeground(Color.BLACK);
        ownAccountComboBox.addItem("BPI" + " | " + "Savings 1" + " | " + "123 456 7890");
        ownAccountComboBox.addItem("BPI" + " | " + "Savings 2" + " | " + "098 765 4321");
        ownAccountPanel.add(ownAccountComboBox, gbc);

        // Own Account Details Panel
        gbc.gridy = 2;
        JPanel ownAccountDetailsPanel = new JPanel();
        ownAccountDetailsPanel.setLayout(gridBagLayout);
        ownAccountDetailsPanel.setBackground(asparagus);
        ownAccountDetailsPanel.setPreferredSize(new Dimension(600,200));
        ownAccountPanel.add(ownAccountDetailsPanel, gbc);

        // Own Account Details Components
        // Balance
        gbc.insets = new Insets(5,10,0,10);
        gbc.gridy = 0;
        JLabel ownTransferBalanceLabel = new JLabel();
        ownTransferBalanceLabel.setText("₱ " + "135,978.23");
        ownTransferBalanceLabel.setFont(montserratBlack.deriveFont(40f));
        ownTransferBalanceLabel.setForeground(Color.WHITE);
        ownTransferBalanceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        ownTransferBalanceLabel.setVerticalAlignment(SwingConstants.CENTER);
        ownAccountDetailsPanel.add(ownTransferBalanceLabel, gbc);

        // Account Name
        gbc.gridy = 1;
        JLabel ownTransferAccountNameLabel = new JLabel();
        ownTransferAccountNameLabel.setText("Savings 1");
        ownTransferAccountNameLabel.setFont(montserrat.deriveFont(17.5f));
        ownTransferAccountNameLabel.setForeground(Color.WHITE);
        ownTransferAccountNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        ownTransferAccountNameLabel.setVerticalAlignment(SwingConstants.CENTER);
        ownAccountDetailsPanel.add(ownTransferAccountNameLabel, gbc);

        // Account Number
        gbc.gridy = 2;
        JLabel ownTransferAccountNumberLabel = new JLabel();
        ownTransferAccountNumberLabel.setText("123 456 7890");
        ownTransferAccountNumberLabel.setFont(montserrat.deriveFont(17.5f));
        ownTransferAccountNumberLabel.setForeground(Color.WHITE);
        ownTransferAccountNumberLabel.setHorizontalAlignment(SwingConstants.CENTER);
        ownTransferAccountNumberLabel.setVerticalAlignment(SwingConstants.CENTER);
        ownAccountDetailsPanel.add(ownTransferAccountNumberLabel, gbc);

        // Buttons Panel
        gbc.insets = new Insets(10,20,10,20);
        gbc.gridwidth = 50;
        gbc.gridy = 4;
        JPanel transferTwoButtonsPanel = new JPanel();
        transferTwoButtonsPanel.setLayout(new FlowLayout());
        transferTwoButtonsPanel.setBackground(Color.WHITE);
        transferTwoPanel.add(transferTwoButtonsPanel, gbc);

        // Previous Button
        JButton transferTwoPreviousButton = new JButton();
        transferTwoPreviousButton.setText("Previous");
        transferTwoPreviousButton.setFont(montserratBold.deriveFont(15f));
        transferTwoPreviousButton.setForeground(Color.BLACK);
        transferTwoPreviousButton.setVerticalAlignment(SwingConstants.CENTER);
        transferTwoButtonsPanel.add(transferTwoPreviousButton);

        // Next Button
        JButton transferTwoNextButton = new JButton();
        transferTwoNextButton.setText("Next");
        transferTwoNextButton.setFont(montserratBold.deriveFont(15f));
        transferTwoNextButton.setForeground(Color.BLACK);
        transferTwoNextButton.setVerticalAlignment(SwingConstants.CENTER);
        transferTwoButtonsPanel.add(transferTwoNextButton);

        // Step 3
        // Step 3 Panel
        JPanel transferThreePanel = new JPanel();
        transferThreePanel.setLayout(gridBagLayout);
        transferThreePanel.setBackground(Color.WHITE);
        transferThreePanel.setPreferredSize(new Dimension(900,700));
        transferPanel.add(transferThreePanel, "t3");

        // Step 3 Panel Components
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5,10,5,10);

        // Step 3 Label
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel stepThreeLabel = new JLabel();
        stepThreeLabel.setText("Step 3 of 3");
        stepThreeLabel.setFont(montserratBold.deriveFont(20f));
        stepThreeLabel.setForeground(Color.BLACK);
        transferThreePanel.add(stepThreeLabel, gbc);

        // Source Details
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 2;
        JPanel sourceDetailsPanel = new JPanel();
        sourceDetailsPanel.setLayout(gridBagLayout);
        sourceDetailsPanel.setBackground(asparagus);
        sourceDetailsPanel.setPreferredSize(new Dimension(250,200));
        transferThreePanel.add(sourceDetailsPanel,gbc);

        // Transfer To Label
        ImageIcon transferDetailsIcon = new ImageIcon("icons/icons8-arrow-50.png");
        ImageIcon scaledTransferToIcon = scaleImage(transferDetailsIcon,40,40);

        gbc.gridwidth = 1;
        gbc.gridx = 1;
        JLabel transferToLabel = new JLabel();
        transferToLabel.setIcon(scaledTransferToIcon);
        transferToLabel.setForeground(Color.BLACK);
        transferThreePanel.add(transferToLabel, gbc);

        // Recipient Details Panel
        gbc.gridwidth = 1;
        gbc.gridx = 2;
        JPanel recipientDetailsPanel = new JPanel();
        recipientDetailsPanel.setLayout(gridBagLayout);
        recipientDetailsPanel.setBackground(asparagus);
        recipientDetailsPanel.setPreferredSize(new Dimension(200,200));
        transferThreePanel.add(recipientDetailsPanel, gbc);

        // Recipient Details Panel Components


        // Transfer Amount Label
        gbc.gridx = 0;
        gbc.gridy = 4;
        JLabel transferAmountLabel = new JLabel();
        transferAmountLabel.setText("Amount:");
        transferAmountLabel.setFont(montserrat.deriveFont(17.5f));
        transferAmountLabel.setForeground(Color.BLACK);
        transferThreePanel.add(transferAmountLabel, gbc);

        // Transfer Amount Text Field
        gbc.gridwidth = 2;
        gbc.gridx = 1;
        gbc.gridy = 4;
        JTextField transferAmountTextField = new JTextField();
        transferAmountTextField.setColumns(24);
        transferAmountTextField.setFont(montserrat.deriveFont(17.5f));
        transferAmountTextField.setForeground(Color.BLACK);
        transferAmountTextField.setCursor(textCursor);
        transferThreePanel.add(transferAmountTextField, gbc);

        // Separator
        gbc.gridwidth = 10;
        gbc.gridx = 0;
        gbc.gridy = 5;
        JSeparator s4 = new JSeparator();
        s4.setForeground(Color.BLACK);
        s4.setOrientation(SwingConstants.HORIZONTAL);
        s4.setPreferredSize(new Dimension(400,2));
        transferThreePanel.add(s4, gbc);

        // Buttons Panel
        gbc.gridy = 6;
        JPanel transferThreeButtonsPanel = new JPanel();
        transferThreeButtonsPanel.setLayout(new FlowLayout());
        transferThreeButtonsPanel.setBackground(Color.WHITE);
        transferThreePanel.add(transferThreeButtonsPanel, gbc);

        // Transfer Three Buttons Panel Components
        // Previous Button
        JButton transferThreePreviousButton = new JButton();
        transferThreePreviousButton.setText("Previous");
        transferThreePreviousButton.setFont(montserratBold.deriveFont(15f));
        transferThreePreviousButton.setForeground(Color.BLACK);
        transferThreeButtonsPanel.add(transferThreePreviousButton);

        // Confirm Button
        JButton transferThreeConfirmButton = new JButton();
        transferThreeConfirmButton.setText("Confirm");
        transferThreeConfirmButton.setFont(montserratBold.deriveFont(15f));
        transferThreeConfirmButton.setForeground(Color.BLACK);
        transferThreeButtonsPanel.add(transferThreeConfirmButton);

        // Card 5 Components
        // Transactions Panel
        JPanel transactionsPanel = new JPanel();
        transactionsPanel.setLayout(new BorderLayout());
        transactionsPanel.setBackground(Color.WHITE);
        transactionsPanel.setPreferredSize(new Dimension(900,600));
        cardPanel.add(transactionsPanel, "5");

        // Account Selector Combo Box
        JComboBox<Account> transactionsAccountComboBox = new JComboBox<>();
        transactionsAccountComboBox.setFont(montserrat.deriveFont(17.5f));
        transactionsAccountComboBox.setBackground(Color.WHITE);
        transactionsAccountComboBox.setForeground(Color.BLACK);
        transactionsPanel.add(transactionsAccountComboBox, BorderLayout.NORTH);

        transactionsAccountComboBox.addItem(accountOne);
        transactionsAccountComboBox.addItem(accountTwo);

        transactionsAccountComboBox.setSelectedItem(null);

        // Transactions Panel Components
        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());
        tablePanel.setBackground(Color.WHITE);
        transactionsPanel.add(tablePanel, BorderLayout.CENTER);

        // Table Panel Components
        String[] columnNames = {"Type" , "Amount" , "Date"};

        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        JTable table = new JTable(model);
        table.getTableHeader().setFont(montserratBold.deriveFont(17.5f));
        table.getTableHeader().setResizingAllowed(false);
        table.setFont(montserrat.deriveFont(15f));
        table.setIntercellSpacing(new Dimension(0, 5));

        JScrollPane scrollPane = new JScrollPane(table);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        // Card 6 Components
        // Wallet Panel
        JPanel walletPanel = new JPanel();
        walletPanel.setLayout(new BorderLayout());
        walletPanel.setBackground(Color.WHITE);
        walletPanel.setPreferredSize(new Dimension(900,600));
        cardPanel.add(walletPanel, "6");

        // Wallet Panel Components
        // Tiles Panel
        JPanel walletTilesPanel = new JPanel();
        walletTilesPanel.setLayout(gridBagLayout);
        walletTilesPanel.setBorder(thinPadding);
        walletTilesPanel.setBackground(Color.WHITE);
        walletTilesPanel.setPreferredSize(new Dimension(650, 600));
        walletPanel.add(walletTilesPanel, BorderLayout.LINE_START);

        // Tiles Components
        gbc.gridwidth = 50;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Date Panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        JPanel datePanel = new JPanel();
        datePanel.setLayout(new FlowLayout());
        datePanel.setBackground(Color.WHITE);
        walletTilesPanel.add(datePanel,gbc);

        // Left Button
        ImageIcon dateLeftIcon = scaleImage(leftArrowIcon,20,20);

        JButton leftDateButton = new JButton();
        leftDateButton.setIcon(dateLeftIcon);
        leftDateButton.setHorizontalAlignment(SwingConstants.CENTER);
        leftDateButton.setVerticalAlignment(SwingConstants.CENTER);
        leftDateButton.setOpaque(false);
        leftDateButton.setContentAreaFilled(false);
        leftDateButton.setBorderPainted(false);
        datePanel.add(leftDateButton);

        // Date
        ImageIcon dateIcon = new ImageIcon("icons/calendar_today_FILL0_wght400_GRAD0_opsz48.png");
        ImageIcon scaledDateIcon = scaleImage(dateIcon,20,20);

        JLabel walletDateLabel = new JLabel();
        walletDateLabel.setIcon(scaledDateIcon);
        walletDateLabel.setText("MAY 2023");
        walletDateLabel.setFont(montserratBold.deriveFont(15f));
        walletDateLabel.setForeground(Color.BLACK);
        walletDateLabel.setHorizontalAlignment(JLabel.LEFT);
        walletDateLabel.setVerticalAlignment(JLabel.CENTER);
        datePanel.add(walletDateLabel);

        // Right Button
        ImageIcon dateRightIcon = scaleImage(rightArrowIcon,20,20);

        JButton rightDateButton = new JButton();
        rightDateButton.setIcon(dateRightIcon);
        rightDateButton.setHorizontalAlignment(SwingConstants.CENTER);
        rightDateButton.setVerticalAlignment(SwingConstants.CENTER);
        rightDateButton.setOpaque(false);
        rightDateButton.setContentAreaFilled(false);
        rightDateButton.setBorderPainted(false);
        datePanel.add(rightDateButton);

        // Balance Panel
        gbc.gridx = 0;
        gbc.gridy = 1;
        JPanel walletBalancePanel = new JPanel();
        walletBalancePanel.setLayout(gridBagLayout);
        walletBalancePanel.setBackground(asparagus);
        walletBalancePanel.setPreferredSize(new Dimension(510, 210));
        walletTilesPanel.add(walletBalancePanel, gbc);

        // Balance Panel Components
        // Balance Label
        ImageIcon balanceIcon = new ImageIcon("icons/icons8-wallet-64.png");
        ImageIcon scaledBalanceIcon = scaleImage(balanceIcon,35,35);

        gbc.fill = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel walletBalanceLabel = new JLabel();
        walletBalanceLabel.setText("Balance");
        walletBalanceLabel.setIcon(scaledBalanceIcon);
        walletBalanceLabel.setFont(montserrat.deriveFont(22f));
        walletBalanceLabel.setForeground(Color.WHITE);
        walletBalancePanel.add(walletBalanceLabel, gbc);

        // Actual Balance Label
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel actualBalanceLabel = new JLabel();
        actualBalanceLabel.setText("₱ " + "1,500.00");
        actualBalanceLabel.setFont(montserratBlack.deriveFont(30f));
        actualBalanceLabel.setForeground(Color.WHITE);
        walletBalancePanel.add(actualBalanceLabel, gbc);

        // Expense Panel
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 2;
        JPanel walletExpensePanel = new JPanel();
        walletExpensePanel.setLayout(gridBagLayout);
        walletExpensePanel.setBackground(satinSheenGold);
        walletExpensePanel.setPreferredSize(new Dimension(250, 170));
        walletTilesPanel.add(walletExpensePanel, gbc);

        // Expense Panel Components
        // Expense Label
        ImageIcon expenseIcon = new ImageIcon("icons/icons8-cash-50.png");
        ImageIcon scaledExpenseIcon = scaleImage(expenseIcon,26,26);

        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel walletExpenseLabel = new JLabel();
        walletExpenseLabel.setText("Expense");
        walletExpenseLabel.setIcon(scaledExpenseIcon);
        walletExpenseLabel.setFont(montserrat.deriveFont(17f));
        walletExpenseLabel.setForeground(Color.WHITE);
        walletExpensePanel.add(walletExpenseLabel, gbc);

        // Actual Expense Label
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel actualExpenseLabel = new JLabel();
        actualExpenseLabel.setText("₱ " + "651.00");
        actualExpenseLabel.setFont(montserratBlack.deriveFont(22f));
        actualExpenseLabel.setForeground(Color.WHITE);
        walletExpensePanel.add(actualExpenseLabel, gbc);

        // Income Panel
        gbc.gridwidth = 1;
        gbc.gridx = 1;
        gbc.gridy = 2;
        JPanel walletIncomePanel = new JPanel();
        walletIncomePanel.setLayout(gridBagLayout);
        walletIncomePanel.setBackground(teal);
        walletIncomePanel.setPreferredSize(new Dimension(250,170));
        walletTilesPanel.add(walletIncomePanel, gbc);

        // Income Panel Components
        // Income Label
        ImageIcon incomeIcon = new ImageIcon("icons/icons8-savings-50.png");
        ImageIcon scaledIncomeIcon = scaleImage(incomeIcon,26,26);

        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel walletIncomeLabel = new JLabel();
        walletIncomeLabel.setText("Income");
        walletIncomeLabel.setIcon(scaledIncomeIcon);
        walletIncomeLabel.setVerticalTextPosition(JLabel.TOP);
        walletIncomeLabel.setFont(montserrat.deriveFont(17f));
        walletIncomeLabel.setForeground(Color.WHITE);
        walletIncomePanel.add(walletIncomeLabel, gbc);

        // Actual Income Label
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel actualIncomeLabel = new JLabel();
        actualIncomeLabel.setText("₱ " + "1,500.00");
        actualIncomeLabel.setFont(montserratBlack.deriveFont(22f));
        actualIncomeLabel.setForeground(Color.WHITE);
        walletIncomePanel.add(actualIncomeLabel, gbc);

        // Buttons Panel
        JPanel walletButtonsPanel = new JPanel();
        walletButtonsPanel.setLayout(gridBagLayout);
        walletButtonsPanel.setBackground(Color.WHITE);
        walletButtonsPanel.setPreferredSize(new Dimension(250,600));
        walletPanel.add(walletButtonsPanel, BorderLayout.LINE_END);

        // Buttons Panel Components
        gbc.gridwidth = 50;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5,0,0,0);

        // Record Book Button
        ImageIcon recordIcon = new ImageIcon("icons/btn/icons8-study-50.png");
        ImageIcon scaledRecordIcon = scaleImage(recordIcon,30,30);

        gbc.gridx = 0;
        gbc.gridy = 0;
        JButton recordButton = new JButton();
        recordButton.setIcon(scaledRecordIcon);
        recordButton.setText("Records");
        recordButton.setFont(montserratBold.deriveFont(14f));
        recordButton.setForeground(Color.WHITE);
        recordButton.setBackground(midnightGreen);
        recordButton.setVerticalAlignment(SwingConstants.CENTER);
        recordButton.setAlignmentX(SwingConstants.WEST);
        walletButtonsPanel.add(recordButton, gbc);

        // Expense Breakdown Button
        ImageIcon expenseBreakdownIcon = new ImageIcon("icons/btn/icons8-cash-receipt-50.png");
        ImageIcon scaledExpenseBreakdownIcon = scaleImage(expenseBreakdownIcon,30,30);

        gbc.gridy = 1;
        JButton expenseBreakdownButton = new JButton();
        expenseBreakdownButton.setIcon(scaledExpenseBreakdownIcon);
        expenseBreakdownButton.setText("Expenses");
        expenseBreakdownButton.setFont(montserratBold.deriveFont(14f));
        expenseBreakdownButton.setForeground(Color.WHITE);
        expenseBreakdownButton.setBackground(midnightGreen);
        expenseBreakdownButton.setVerticalAlignment(SwingConstants.CENTER);
        expenseBreakdownButton.setAlignmentX(SwingConstants.WEST);
        walletButtonsPanel.add(expenseBreakdownButton, gbc);

        // Income Breakdown Button
        ImageIcon incomeBreakdownIcon = new ImageIcon("icons/btn/icons8-cash-50.png");
        ImageIcon scaledIncomeBreakdownIcon = scaleImage(incomeBreakdownIcon,30,30);

        gbc.gridy = 2;
        JButton incomeBreakdownButton = new JButton();
        incomeBreakdownButton.setIcon(scaledIncomeBreakdownIcon);
        incomeBreakdownButton.setText("Income");
        incomeBreakdownButton.setFont(montserratBold.deriveFont(14f));
        incomeBreakdownButton.setForeground(Color.WHITE);
        incomeBreakdownButton.setBackground(midnightGreen);
        incomeBreakdownButton.setVerticalAlignment(SwingConstants.CENTER);
        incomeBreakdownButton.setAlignmentX(SwingConstants.WEST);
        walletButtonsPanel.add(incomeBreakdownButton, gbc);

        /////////////////////////////////////////////////////////////////////////

        // Action Listeners
        // Left Panel Buttons
        accountButton.addActionListener(e -> {
            SwingUtilities.updateComponentTreeUI(this);
            cardLayout1.show(cardPanel, "1");
        }); // end of ActionListener for accountButton
        depositButton.addActionListener(e -> cardLayout1.show(cardPanel,"2"));
        withdrawButton.addActionListener(e -> cardLayout1.show(cardPanel,"3"));
        transferButton.addActionListener(e -> cardLayout1.show(cardPanel,"4"));
        transactionsButton.addActionListener(e -> cardLayout1.show(cardPanel,"5"));
        switchButton.addActionListener(e -> cardLayout1.show(cardPanel, "6"));
        signOutButton.addActionListener(e -> System.exit(0));

        // Center Panel Arrows
        leftArrowButton.addActionListener(e -> {
            cardPanel.revalidate();
            cardLayout2.previous(accountCardPanel);
        });
        rightArrowButton.addActionListener(e -> {
            cardPanel.revalidate();
            cardLayout2.next(accountCardPanel);
        });

        // Deposit Buttons
        // Deposit Combo Box
        depositAccountComboBox.addActionListener(e -> {
            Account selectedAccount = (Account) depositAccountComboBox.getSelectedItem();

            if (selectedAccount != null) {
                depositBalanceLabel.setText("₱ " + String.format("%,.2f" , selectedAccount.getBalance()));
                depositAccountNameLabel.setText(selectedAccount.getAccountName());
                depositAccountNumberLabel.setText(selectedAccount.getAccountNumber());
                depositAmountTextField.setEnabled(true);
            } // end of if

            // Cash Radio
            cashRadioButton.addActionListener(e1 -> {
                if (!depositAmountTextField.getText().equals(""))
                    acceptDepositButton.setEnabled(true);
            }); // end of ActionListener for cashRadioButton

            // Other Radio
            otherRadioButton.addActionListener(e1 -> {
                if (!depositAmountTextField.getText().equals(""))
                    acceptDepositButton.setEnabled(true);
            }); // end of ActionListener for otherRadioButton

            depositAmountTextField.addKeyListener(new KeyAdapter() {
                /**
                 * Validates characters being entered to be numerical values only
                 * @param e the event to be processed
                 */
                @Override
                public void keyTyped(KeyEvent e) {
                    String value = depositAmountTextField.getText();
                    if ((e.getKeyChar() >= '0' && e.getKeyChar() <= '9') || e.getKeyChar() == VK_BACK_SPACE
                            || e.getKeyChar() == VK_PERIOD || e.getKeyChar() == VK_DECIMAL){
                        depositAmountTextField.setEditable(true);
                        // label.setText("");
                    } else {
                        depositAmountTextField.setEditable(false);
                        // label.setText("* Enter only numeric digits(0-9)");
                    } // end of if-else
                } // end of ketTyped method
            }); // end of KeyListener for depositAmountTextField

            // Accept Deposit Button
            acceptDepositButton.addActionListener(e1 -> {
                try {
                    if (selectedAccount != null) {
                        double amount = Double.parseDouble(depositAmountTextField.getText());
                        selectedAccount.deposit(amount);
                    } // end of if
                } catch (Exception exception1) {
                    JOptionPane.showMessageDialog(null, "Error making deposit. Try again");
                } // end of try-catch
            }); // end of ActionListener for acceptDepositButton
        }); // end of ActionListener for depositAccountComboBox

        // Clear Deposit Button
        clearDepositButton.addActionListener(e -> {
            depositBalanceLabel.setText("SELECT ACCOUNT");
            depositAccountNameLabel.setText("");
            depositAccountNumberLabel.setText("");
            depositAmountTextField.setText("");
            depositRadioGroup.clearSelection();
            depositAccountComboBox.setSelectedItem(null);
            depositAmountTextField.setEnabled(false);
            acceptDepositButton.setEnabled(false);
        }); // end of ActionListener for clearDepositButton

        // Withdraw Buttons
        // Withdraw Combo Box
        withdrawAccountComboBox.addActionListener(e -> {
            Account selectedAccount = (Account) withdrawAccountComboBox.getSelectedItem();

            if (selectedAccount != null) {
                withdrawBalanceLabel.setText("₱ " + String.format("%,.2f" , selectedAccount.getBalance()));
                withdrawAccountNameLabel.setText(selectedAccount.getAccountName());
                withdrawAccountNumberLabel.setText(selectedAccount.getAccountNumber());
                withdrawAmountTextField.setEnabled(true);
                fiveHundredButton.setEnabled(true);
                oneThousandButton.setEnabled(true);
                oneThousandFiveButton.setEnabled(true);
                twoThousandButton.setEnabled(true);
                threeThousandButton.setEnabled(true);
                fiveThousandButton.setEnabled(true);
            } // end of if

            withdrawAmountTextField.addKeyListener(new KeyAdapter() {
                /**
                 * Validates characters being entered to be numerical values only
                 * @param e the event to be processed
                 */
                @Override
                public void keyTyped(KeyEvent e) {
                    String value = withdrawAmountTextField.getText();
                    if ((e.getKeyChar() >= '0' && e.getKeyChar() <= '9') || e.getKeyChar() == VK_BACK_SPACE
                            || e.getKeyChar() == VK_PERIOD || e.getKeyChar() == VK_DECIMAL){
                        withdrawAmountTextField.setEditable(true);
                        // label.setText("");
                    } else {
                        withdrawAmountTextField.setEditable(false);
                        // label.setText("* Enter only numeric digits(0-9)");
                    } // end of if-else
                } // end of keyTyped method
            }); // end of KeyListener for withdrawAmountTextField

            fiveHundredButton.addActionListener(e1 -> {
                withdrawAmountTextField.setText(String.valueOf(500.00));
                if (!withdrawAmountTextField.getText().equals(""))
                    acceptWithdrawalButton.setEnabled(true);
            }); // end of ActionListener for fiveHundredButton

            oneThousandButton.addActionListener(e2 -> {
                withdrawAmountTextField.setText(String.valueOf(1000.00));
                if (!withdrawAmountTextField.getText().equals(""))
                    acceptWithdrawalButton.setEnabled(true);
            }); // end of ActionListener for oneThousandButton

            oneThousandFiveButton.addActionListener(e3 -> {
                withdrawAmountTextField.setText(String.valueOf(1500.00));
                if (!withdrawAmountTextField.getText().equals(""))
                    acceptWithdrawalButton.setEnabled(true);
            }); // end of ActionListener for oneThousandFiveButton

            twoThousandButton.addActionListener(e4 -> {
                withdrawAmountTextField.setText(String.valueOf(2000.00));
                if (!withdrawAmountTextField.getText().equals(""))
                    acceptWithdrawalButton.setEnabled(true);
            }); // end of ActionListener for twoThousandButton

            threeThousandButton.addActionListener(e5 -> {
                withdrawAmountTextField.setText(String.valueOf(3000.00));
                if (!withdrawAmountTextField.getText().equals(""))
                    acceptWithdrawalButton.setEnabled(true);
            }); // end of ActionListener for threeThousandButton

            fiveThousandButton.addActionListener(e6 -> {
                withdrawAmountTextField.setText(String.valueOf(5000.00));
                if (!withdrawAmountTextField.getText().equals(""))
                    acceptWithdrawalButton.setEnabled(true);
            }); // end of ActionListener for fiveThousandButton

            withdrawAmountTextField.addActionListener(e7 -> {
                acceptWithdrawalButton.setEnabled(true);
            }); // end of ActionListener for withdrawAmountTextField

            // Accept Withdrawal Button
            acceptWithdrawalButton.addActionListener(e2 -> {
                try {
                    if (selectedAccount != null) {
                        double amount = Double.parseDouble(withdrawAmountTextField.getText());
                        selectedAccount.withdraw(amount);
                    } // end of if
                } catch (Exception exception1) {
                    JOptionPane.showMessageDialog(null, "Error making withdrawal. Try again");
                } // end of try-catch
            }); // end of ActionListener for acceptWithdrawalButton method
        }); // end of ActionListener for withdrawAccountComboBox

        // Clear Withdraw Button
        clearWithdrawalButton.addActionListener(e -> {
            withdrawBalanceLabel.setText("SELECT ACCOUNT");
            withdrawAccountNameLabel.setText("");
            withdrawAccountNumberLabel.setText("");
            withdrawAmountTextField.setText("");
            withdrawAccountComboBox.setSelectedItem(null);
            withdrawAmountTextField.setEnabled(false);
            acceptWithdrawalButton.setEnabled(false);
            withdrawAmountTextField.setEnabled(true);
            fiveHundredButton.setEnabled(false);
            oneThousandButton.setEnabled(false);
            oneThousandFiveButton.setEnabled(false);
            twoThousandButton.setEnabled(false);
            threeThousandButton.setEnabled(false);
            fiveThousandButton.setEnabled(false);
        }); // end of ActionListener for clearWithdrawalButton

        // Transaction Panel Buttons
        transactionsAccountComboBox.addActionListener(e -> {
            model.setRowCount(0);
            table.scrollRectToVisible(table.getCellRect(0, 0, true));

            Account selectedAccount = (Account) transactionsAccountComboBox.getSelectedItem();
            if (selectedAccount != null) {
                try {
                    List<Transaction> transactions = selectedAccount.readTransactions(selectedAccount.getAccountNumber());
                    for (Transaction transaction : transactions) {
                        Object[] transactionData = {transaction.getType(), transaction.getAmount(), transaction.getDate()};
                        model.addRow(transactionData);
                    } // end of for
                } catch (FileNotFoundException e1) {
                    Object[] noFile = {"No Transactions Found"};
                    model.addRow(noFile);
                } // end of try-catch
            } // end of if
        }); // end of ActionListener for transactionsAccountComboBox

        // Transfer Panel Buttons
        // Step 1
        transferSourceComboBox.addActionListener(e -> {
            Account selectedAccount = (Account) transferSourceComboBox.getSelectedItem();

            if (selectedAccount != null) {
                sourceBalanceLabel.setText("₱ " + String.format("%,.2f" , selectedAccount.getBalance()));
                sourceAccountNameLabel.setText(selectedAccount.getAccountName());
                sourceAccountNumberLabel.setText(selectedAccount.getAccountNumber());

                transferOneNextButton.setEnabled(true);
                transferOneNextButton.addActionListener(next -> cardLayout3.next(transferPanel));
            } // end of if
        }); // end of ActionListener for transferSourceComboBox



        // Step 2
        transferTwoPreviousButton.addActionListener(e -> cardLayout3.previous(transferPanel));
        transferTwoNextButton.addActionListener(e -> cardLayout3.next(transferPanel));

        // Step 3
        transferThreePreviousButton.addActionListener(e -> cardLayout3.previous(transferPanel));

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1200,700);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    } // end of Main constructor

    /**
     * Adds new account card panel in the Accounts page
     * @param account specified bank account
     * @return new panel
     */
    private JPanel addAccountCardPanel(Account account) {
        // Account Card
        // Account Holder Panel
        JPanel accountHolderPanel = new JPanel();
        accountHolderPanel.setLayout(gridBagLayout);
        accountHolderPanel.setBackground(asparagus);
        accountHolderPanel.setPreferredSize(new Dimension(400, 200));

        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 10, 0, 10);

        // Account Holder components
        // Account Bank Label
        gbc.gridy = 0;
        JLabel bankLabel = new JLabel();
        bankLabel.setText(accountOne.getBank());
        bankLabel.setFont(montserratBold.deriveFont(30f));
        bankLabel.setForeground(Color.WHITE);
        bankLabel.setVerticalAlignment(SwingConstants.CENTER);
        bankLabel.setHorizontalAlignment(SwingConstants.CENTER);
        accountHolderPanel.add(bankLabel, gbc);

        // Separator 1
        gbc.gridy = 1;
        JSeparator s1 = new JSeparator();
        s1.setForeground(Color.WHITE);
        s1.setOrientation(SwingConstants.HORIZONTAL);
        s1.setPreferredSize(new Dimension(400, 2));
        accountHolderPanel.add(s1, gbc);

        // Account 1 Actual Balance
        gbc.gridy = 2;
        JLabel balanceLabel = new JLabel();
        balanceLabel.setText("₱ " + String.format("%,.2f",account.getBalance()));
        balanceLabel.setFont(montserratBlack.deriveFont(40f));
        balanceLabel.setForeground(Color.WHITE);
        balanceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        balanceLabel.setVerticalAlignment(SwingConstants.CENTER);
        accountHolderPanel.add(balanceLabel,gbc);

        // Account Name
        gbc.gridy = 3;
        JLabel accountNameLabel = new JLabel();
        accountNameLabel.setText(account.getAccountName());
        accountNameLabel.setFont(montserrat.deriveFont(17.5f));
        accountNameLabel.setForeground(Color.WHITE);
        accountNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        accountNameLabel.setVerticalAlignment(SwingConstants.CENTER);
        accountHolderPanel.add(accountNameLabel, gbc);

        // Account Number
        gbc.gridy = 4;
        JLabel accountNumberLabel = new JLabel();
        accountNumberLabel.setText(account.getAccountNumber());
        accountNumberLabel.setFont(montserrat.deriveFont(17.5f));
        accountNumberLabel.setForeground(Color.WHITE);
        accountNumberLabel.setHorizontalAlignment(SwingConstants.CENTER);
        accountNumberLabel.setVerticalAlignment(SwingConstants.CENTER);
        accountHolderPanel.add(accountNumberLabel, gbc);

        // Account Holder
        gbc.gridy = 5;
        JLabel accountHolderLabel = new JLabel();
        accountHolderLabel.setText(account.getAccountHolder());
        accountHolderLabel.setFont(montserrat.deriveFont(17.5f));
        accountHolderLabel.setForeground(Color.WHITE);
        accountHolderLabel.setHorizontalAlignment(SwingConstants.CENTER);
        accountHolderLabel.setVerticalAlignment(SwingConstants.CENTER);
        accountHolderPanel.add(accountHolderLabel, gbc);

        return accountHolderPanel;
    }  // end of addAccountCardPanel

    private void refresh() {
        this.invalidate();
        this.validate();
        this.repaint();
    } // end of refresh method

    /**
     * Resizes an image using desired dimensions
     * @param sourceImage given image
     * @param width desired width
     * @param height desired height
     * @return scaled ImageIcon
     */
    private ImageIcon scaleImage(ImageIcon sourceImage, int width, int height) {
        int newWidth = sourceImage.getIconWidth();
        int newHeight = sourceImage.getIconHeight();

        if (sourceImage.getIconWidth() > width) {
            newWidth = width;
            newHeight = (newWidth * sourceImage.getIconHeight()) / sourceImage.getIconWidth();
        } else if (newHeight > height) {
            newHeight = height;
            newWidth = (sourceImage.getIconWidth() * newHeight) / sourceImage.getIconHeight();
        } // end of if-else
        return new ImageIcon(sourceImage.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH));
    } // end of scaleImage method
} // end of class Main
