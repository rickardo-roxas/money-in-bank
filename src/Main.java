import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    final CardLayout cardLayout1 = new CardLayout(20,10);

    /**
     * CardLayout used for some components.
     */
    final CardLayout cardLayout2 = new CardLayout(30,40);

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

    Cursor textCursor = new Cursor(Cursor.TEXT_CURSOR);
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
        cardPanel.setLayout(cardLayout1);
        cardPanel.setBackground(Color.WHITE);
        cardPanel.setPreferredSize(new Dimension(900,500));
        centerPanel.add(cardPanel, BorderLayout.NORTH);

        // Card Panel Components
        // Account Panel
        JPanel accountPanel = new JPanel();
        accountPanel.setLayout(new BorderLayout());
        accountPanel.setBackground(Color.WHITE);
        accountPanel.setPreferredSize(new Dimension(900,500));
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
        JComboBox<String> depositAccountComboBox = new JComboBox<>();
        depositAccountComboBox.setFont(montserrat.deriveFont(17.5f));
        depositAccountComboBox.setForeground(Color.BLACK);
        depositAccountComboBox.addItem("BPI"  + " | " + "Savings 1");
        depositAccountComboBox.addItem("BPI" + " | " + "Savings 2");
        depositPanel.add(depositAccountComboBox, gbc);

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
        depositBalanceLabel.setText("₱ " + "135,978.23");
        depositBalanceLabel.setFont(montserratBlack.deriveFont(40f));
        depositBalanceLabel.setForeground(Color.WHITE);
        depositBalanceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        depositBalanceLabel.setVerticalAlignment(SwingConstants.CENTER);
        depositAccountPanel.add(depositBalanceLabel, gbc);
        
        // Account Name
        gbc.gridy = 1;
        JLabel depositAccountNameLabel = new JLabel();
        depositAccountNameLabel.setText("Savings 1");
        depositAccountNameLabel.setFont(montserrat.deriveFont(17.5f));
        depositAccountNameLabel.setForeground(Color.WHITE);
        depositAccountNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        depositAccountNameLabel.setVerticalAlignment(SwingConstants.CENTER);
        depositAccountPanel.add(depositAccountNameLabel, gbc);

        // Account Number
        gbc.gridy = 2;
        JLabel depositAccountNumberLabel = new JLabel();
        depositAccountNumberLabel.setText("123 456 7890");
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
        gbc.gridwidth = 4;
        gbc.gridx = 1;
        gbc.gridy = 4;
        JPanel depositRadioPanel = new JPanel();
        depositRadioPanel.setLayout(new FlowLayout());
        depositRadioPanel.setBackground(Color.WHITE);
        depositRadioPanel.setAlignmentX(SwingConstants.WEST);
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
        clearDepositButton.setForeground(Color.BLACK);
        clearDepositButton.setVerticalAlignment(SwingConstants.CENTER);
        depositButtonsPanel.add(clearDepositButton);
        
        // Deposit Button
        JButton acceptDepositButton = new JButton();
        acceptDepositButton.setText("Deposit");
        acceptDepositButton.setFont(montserratBold.deriveFont(15f));
        acceptDepositButton.setForeground(Color.BLACK);
        acceptDepositButton.setVerticalAlignment(SwingConstants.CENTER);
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
        JComboBox<String> withdrawAccountComboBox = new JComboBox<>();
        withdrawAccountComboBox.setFont(montserrat.deriveFont(17.5f));
        withdrawAccountComboBox.setForeground(Color.BLACK);
        withdrawAccountComboBox.addItem("BPI"  + " | " + "Savings 1");
        withdrawAccountComboBox.addItem("BPI" + " | " + "Savings 2");
        withdrawPanel.add(withdrawAccountComboBox, gbc);

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
        withdrawBalanceLabel.setText("₱ " + "135,978.23");
        withdrawBalanceLabel.setFont(montserratBlack.deriveFont(40f));
        withdrawBalanceLabel.setForeground(Color.WHITE);
        withdrawBalanceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        withdrawBalanceLabel.setVerticalAlignment(SwingConstants.CENTER);
        withdrawAccountPanel.add(withdrawBalanceLabel, gbc);

        // Account Name
        gbc.gridy = 1;
        JLabel withdrawAccountNameLabel = new JLabel();
        withdrawAccountNameLabel.setText("Savings 1");
        withdrawAccountNameLabel.setFont(montserrat.deriveFont(17.5f));
        withdrawAccountNameLabel.setForeground(Color.WHITE);
        withdrawAccountNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        withdrawAccountNameLabel.setVerticalAlignment(SwingConstants.CENTER);
        withdrawAccountPanel.add(withdrawAccountNameLabel, gbc);

        // Account Number
        gbc.gridy = 2;
        JLabel withdrawAccountNumberLabel = new JLabel();
        withdrawAccountNumberLabel.setText("123 456 7890");
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
        fiveHundredButton.setForeground(Color.BLACK);
        fiveHundredButton.setVerticalAlignment(SwingConstants.CENTER);
        withdrawAmountsPanel.add(fiveHundredButton);

        // 1000
        JButton oneThousandButton = new JButton();
        oneThousandButton.setText("₱1000");
        oneThousandButton.setFont(montserratBold.deriveFont(15f));
        oneThousandButton.setForeground(Color.BLACK);
        oneThousandButton.setVerticalAlignment(SwingConstants.CENTER);
        withdrawAmountsPanel.add(oneThousandButton);

        // 1500
        JButton oneThousandFiveButton = new JButton();
        oneThousandFiveButton.setText("₱1500");
        oneThousandFiveButton.setFont(montserratBold.deriveFont(15f));
        oneThousandFiveButton.setForeground(Color.BLACK);
        oneThousandFiveButton.setVerticalAlignment(SwingConstants.CENTER);
        withdrawAmountsPanel.add(oneThousandFiveButton);

        // 2000
        JButton twoThousandButton = new JButton();
        twoThousandButton.setText("₱2000");
        twoThousandButton.setFont(montserratBold.deriveFont(15f));
        twoThousandButton.setForeground(Color.BLACK);
        twoThousandButton.setVerticalAlignment(SwingConstants.CENTER);
        withdrawAmountsPanel.add(twoThousandButton);

        // 3000
        JButton threeThousandButton = new JButton();
        threeThousandButton.setText("₱3000");
        threeThousandButton.setFont(montserratBold.deriveFont(15f));
        threeThousandButton.setForeground(Color.BLACK);
        threeThousandButton.setVerticalAlignment(SwingConstants.CENTER);
        withdrawAmountsPanel.add(threeThousandButton);

        // 5000
        JButton fiveThousandButton = new JButton();
        fiveThousandButton.setText("₱5000");
        fiveThousandButton.setFont(montserratBold.deriveFont(15f));
        fiveThousandButton.setForeground(Color.BLACK);
        fiveThousandButton.setVerticalAlignment(SwingConstants.CENTER);
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
        clearWithdrawalButton.setForeground(Color.BLACK);
        clearWithdrawalButton.setVerticalAlignment(SwingConstants.CENTER);
        withdrawButtonsPanel.add(clearWithdrawalButton);

        // Withdraw Button
        JButton acceptWithdrawalButton = new JButton();
        acceptWithdrawalButton.setText("Withdraw");
        acceptWithdrawalButton.setFont(montserratBold.deriveFont(15f));
        acceptWithdrawalButton.setForeground(Color.BLACK);
        acceptWithdrawalButton.setVerticalAlignment(SwingConstants.CENTER);
        withdrawButtonsPanel.add(acceptWithdrawalButton);

        // Card 4 Components
        // Transfer Panel
        JPanel transferPanel = new JPanel();
        transferPanel.setBackground(Color.CYAN);
        cardPanel.add(transferPanel, "4");

        // Card 5 Components
        // Transactions Panel
        JPanel transactionsPanel = new JPanel();
        transactionsPanel.setLayout(new BorderLayout());
        transactionsPanel.setBackground(Color.WHITE);
        transactionsPanel.setPreferredSize(new Dimension(900,600));
        cardPanel.add(transactionsPanel, "5");

        // Account Selector Combo Box
        JComboBox<String> transactionsAccountComboBox = new JComboBox<>();
        transactionsAccountComboBox.setFont(montserrat.deriveFont(17.5f));
        transactionsAccountComboBox.setForeground(Color.BLACK);
        transactionsAccountComboBox.addItem("BPI"  + " | " + "Savings 1" + " | " + "123 456 7890");
        transactionsAccountComboBox.addItem("BPI" + " | " + "Savings 2" + " | " + "098 765 4321");
        transactionsPanel.add(transactionsAccountComboBox, BorderLayout.NORTH);

        // Transactions Panel Components
        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());
        tablePanel.setBackground(Color.WHITE);
        transactionsPanel.add(tablePanel, BorderLayout.CENTER);

        // Table Panel Components
        String[] columnNames = {"Reference Number" , "Type" , "Amount" , "Date"};

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
        // Record Book Button
        ImageIcon recordIcon = new ImageIcon("icons/btn/icons8-study-50.png");
        ImageIcon scaledRecordIcon = scaleImage(recordIcon,30,30);

        gbc.gridwidth = 50;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.insets = new Insets(5,0,0,0);

        gbc.gridy = 0;
        JButton recordButton = new JButton();
        recordButton.setIcon(scaledRecordIcon);
        recordButton.setText("Record Book");
        recordButton.setFont(montserratBold.deriveFont(14f));
        recordButton.setForeground(Color.WHITE);
        recordButton.setBackground(midnightGreen);
        recordButton.setVerticalAlignment(SwingConstants.CENTER);
        walletButtonsPanel.add(recordButton, gbc);

        // Expense Breakdown Button
        ImageIcon expenseBreakdownIcon = new ImageIcon("icons/btn/icons8-cash-receipt-50.png");
        ImageIcon scaledExpenseBreakdownIcon = scaleImage(expenseBreakdownIcon,30,30);

        gbc.gridy = 1;
        JButton expenseBreakdownButton = new JButton();
        expenseBreakdownButton.setIcon(scaledExpenseBreakdownIcon);
        expenseBreakdownButton.setText("Expense Breakdown");
        expenseBreakdownButton.setFont(montserratBold.deriveFont(14f));
        expenseBreakdownButton.setForeground(Color.WHITE);
        expenseBreakdownButton.setBackground(midnightGreen);
        expenseBreakdownButton.setVerticalAlignment(SwingConstants.CENTER);
        walletButtonsPanel.add(expenseBreakdownButton, gbc);

        // Income Breakdown Button
        ImageIcon incomeBreakdownIcon = new ImageIcon("icons/btn/icons8-cash-50.png");
        ImageIcon scaledIncomeBreakdownIcon = scaleImage(incomeBreakdownIcon,30,30);

        gbc.gridy = 2;
        JButton incomeBreakdownButton = new JButton();
        incomeBreakdownButton.setIcon(scaledIncomeBreakdownIcon);
        incomeBreakdownButton.setText("Income Breakdown");
        incomeBreakdownButton.setFont(montserratBold.deriveFont(14f));
        incomeBreakdownButton.setForeground(Color.WHITE);
        incomeBreakdownButton.setBackground(midnightGreen);
        incomeBreakdownButton.setVerticalAlignment(SwingConstants.CENTER);
        walletButtonsPanel.add(incomeBreakdownButton, gbc);

        // Action Listeners
        accountButton.addActionListener(e -> cardLayout1.show(cardPanel, "1"));
        depositButton.addActionListener(e -> cardLayout1.show(cardPanel,"2"));
        withdrawButton.addActionListener(e -> cardLayout1.show(cardPanel,"3"));
        transferButton.addActionListener(e -> cardLayout1.show(cardPanel,"4"));
        transactionsButton.addActionListener(e -> cardLayout1.show(cardPanel,"5"));
        switchButton.addActionListener(e -> cardLayout1.show(cardPanel, "6"));
        signOutButton.addActionListener(e -> System.exit(0));

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
