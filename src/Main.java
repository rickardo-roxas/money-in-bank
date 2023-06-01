import javax.swing.*;
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
    private final Color midnightGreen = new Color(9,82,96);

    /**
     * Teal
     * Secondary color for UI
     */
    private final Color teal = new Color(8,127,140);

    /**
     * Zomp
     * Secondary color for UI
     */
    private final Color zomp = new Color(90,170,149);

    /**
     * Asparagus
     * Primary color for UI
     */
    private final Color asparagus = new Color(134,168,115);

    /**
     * Satin sheen gold
     * Primary color for UI
     */
    private final Color satinSheenGold = new Color(187,159,6);

    /**
     * Main entry point of the program
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Main mainProgram;
        try {
            mainProgram = new Main();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } // end of try-catch
    } // end of main method

    /**
     * Constructs Main with graphical user interface
     */
    public Main() {
        super("Money In Bank");

        // Sets the icon image
        ImageIcon favicon = new ImageIcon("icons/favicon.png");
        setIconImage(favicon.getImage());

        // Left Panel
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.RED);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1200,700);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    } // end of Main constructor
} // end of class Main
