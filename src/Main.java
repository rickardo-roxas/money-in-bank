import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.lang.*;
import java.io.*;
import java.awt.*;

/**
 * @author Johan Rickardo Roxas
 * @version 1.00 (2023/06/01)
 * Main program that contains the graphical user-interface.
 */
public class Main extends JFrame {

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
     * CardLayout used for soe components.
     */
    final CardLayout cardLayout = new CardLayout(30,40);

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

    Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);

    Cursor defaultCursor = new Cursor(Cursor.DEFAULT_CURSOR);


    /**
     * Main entry point of the program
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Main mainProgram;
        try {
            mainProgram = new Main();
            mainProgram.run();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } // end of try-catch
    } // end of main method

    private void run() {

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
        cardPanel.setLayout(cardLayout);
        cardPanel.setBackground(Color.WHITE);
        cardPanel.setPreferredSize(new Dimension(900,500));
        centerPanel.add(cardPanel, BorderLayout.NORTH);

        // Card Panel Components
        // Account Panel
        JPanel accountPanel = new JPanel();
        accountPanel.setLayout(new BorderLayout());
        accountPanel.setBackground(Color.WHITE);
        accountPanel.setPreferredSize(new Dimension(900,500));
        cardPanel.add(accountPanel);

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
        accountCardPanel.setLayout(cardLayout);
        accountCardPanel.setBackground(asparagus);
        accountCardPanel.setPreferredSize(new Dimension(400, 200));
        accountPanel.add(accountCardPanel, BorderLayout.CENTER);

        // Account Holder Panel
        JPanel accountHolderPanel = new JPanel();
        accountHolderPanel.setLayout(gridBagLayout);
        accountHolderPanel.setBackground(asparagus);
        accountHolderPanel.setPreferredSize(new Dimension(400, 200));
        accountCardPanel.add(accountHolderPanel, BorderLayout.CENTER);

        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 10, 0, 10);

        // Account Panel components
        // Bank Label
        gbc.gridy = 0;
        JLabel bankLabel = new JLabel();
        bankLabel.setText("BPI");
        bankLabel.setFont(montserratBold.deriveFont(30f));
        bankLabel.setForeground(Color.WHITE);
        bankLabel.setVerticalAlignment(SwingConstants.CENTER);
        bankLabel.setHorizontalAlignment(SwingConstants.CENTER);
        accountHolderPanel.add(bankLabel, gbc);

        // Separator
        gbc.gridy = 1;
        JSeparator s1 = new JSeparator();
        s1.setForeground(Color.WHITE);
        s1.setOrientation(SwingConstants.HORIZONTAL);
        s1.setPreferredSize(new Dimension(400, 2));
        accountHolderPanel.add(s1, gbc);

        // Actual Balance
        gbc.gridy = 2;
        JLabel balanceLabel = new JLabel();
        balanceLabel.setText("₱ " + "135,978.23");
        balanceLabel.setFont(montserratBlack.deriveFont(40f));
        balanceLabel.setForeground(Color.WHITE);
        balanceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        balanceLabel.setVerticalAlignment(SwingConstants.CENTER);
        accountHolderPanel.add(balanceLabel,gbc);

        // Account Name
        gbc.gridy = 3;
        JLabel accountNameLabel = new JLabel();
        accountNameLabel.setText("Savings 1");
        accountNameLabel.setFont(montserrat.deriveFont(17.5f));
        accountNameLabel.setForeground(Color.WHITE);
        accountNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        accountNameLabel.setVerticalAlignment(SwingConstants.CENTER);
        accountHolderPanel.add(accountNameLabel, gbc);

        // Account Number
        gbc.gridy = 4;
        JLabel accountNumberLabel = new JLabel();
        accountNumberLabel.setText("123 456 7890");
        accountNumberLabel.setFont(montserrat.deriveFont(17.5f));
        accountNumberLabel.setForeground(Color.WHITE);
        accountNumberLabel.setHorizontalAlignment(SwingConstants.CENTER);
        accountNumberLabel.setVerticalAlignment(SwingConstants.CENTER);
        accountHolderPanel.add(accountNumberLabel, gbc);

        // Account Holder
        gbc.gridy = 5;
        JLabel accountHolderLabel = new JLabel();
        accountHolderLabel.setText("John Doe");
        accountHolderLabel.setFont(montserrat.deriveFont(17.5f));
        accountHolderLabel.setForeground(Color.WHITE);
        accountHolderLabel.setHorizontalAlignment(SwingConstants.CENTER);
        accountHolderLabel.setVerticalAlignment(SwingConstants.CENTER);
        accountHolderPanel.add(accountHolderLabel, gbc);

        // Bottom Panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.setBackground(Color.DARK_GRAY);
        bottomPanel.setPreferredSize(new Dimension(800,200));
        bottomPanel.setBorder(normalPadding);
        centerPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Buttons Panel
        JPanel budgetButtonsPanel = new JPanel();
        budgetButtonsPanel.setLayout(new FlowLayout());
        budgetButtonsPanel.setBackground(Color.DARK_GRAY);
        budgetButtonsPanel.setPreferredSize(new Dimension(800,200));
        budgetButtonsPanel.setBorder(normalPadding);
        bottomPanel.add(budgetButtonsPanel, BorderLayout.CENTER);

        // Set Budget
        JButton setBudgetButton = new JButton();
        setBudgetButton.setText("Set Budget");
        setBudgetButton.setIcon(scaledSetBudgetIcon);
        setBudgetButton.setVerticalAlignment(SwingConstants.CENTER);
        budgetButtonsPanel.add(setBudgetButton);

        // Calculate Budget
        JButton calculateBudgetButton = new JButton();
        calculateBudgetButton.setText("Calculate Budget");
        calculateBudgetButton.setIcon(scaledCalculateBudgetIcon);
        calculateBudgetButton.setVerticalAlignment(SwingConstants.CENTER);
        budgetButtonsPanel.add(calculateBudgetButton);

        // Budget Goals
        JButton budgetGoalsButton = new JButton();
        budgetGoalsButton.setText("Budget Goals");
        budgetGoalsButton.setIcon(scaledBudgetGoalsIcon);
        budgetGoalsButton.setVerticalAlignment(SwingConstants.CENTER);
        budgetButtonsPanel.add(budgetGoalsButton);

        // Copyright Label
        JLabel copyrightLabel = new JLabel();
        copyrightLabel.setText("© 2023 ROXAS, JOHAN RICKARDO");
        copyrightLabel.setFont(new Font("Helvetica" , Font.PLAIN, 10));
        copyrightLabel.setForeground(Color.WHITE);
        copyrightLabel.setVerticalTextPosition(JLabel.CENTER);
        copyrightLabel.setHorizontalAlignment(JLabel.CENTER);
        bottomPanel.add(copyrightLabel, BorderLayout.SOUTH);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1200,700);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    } // end of Main constructor

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
