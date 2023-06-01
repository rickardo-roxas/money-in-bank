import javax.swing.*;

/**
 * @author Johan Rickardo Roxas
 * @version 1.00 (2023/06/01)
 * Main program that contains the graphical user-interface.
 */
public class Main extends JFrame {

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

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1200,600);

        ImageIcon favicon = new ImageIcon("icons/favicon.png");
        setIconImage(favicon.getImage());

        setLocationRelativeTo(null);
        setVisible(true);
    } // end of Main constructor
} // end of class Main
