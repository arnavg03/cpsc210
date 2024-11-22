package ui;

import javax.swing.*;

public class Main {

    // EFFECTS: launches the FinanceApp GUI
    public static void main(String[] args) {
        SwingUtilities.invokeLater(FinanceAppGUI::new); // Starts the FinanceApp GUI
    }

    /** 
    public static void main(String[] args) throws Exception {
        new FinanceApp();  // Starts the FinanceApp console interface 
    }
    */
}