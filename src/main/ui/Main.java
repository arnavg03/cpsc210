package ui;

import javax.swing.*;
import model.*;

public class Main {

    // EFFECTS: launches the FinanceApp GUI
    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("\nEvent Log:");
            for (Event event : EventLog.getInstance()) {
                System.out.println(event);
            }
        }));
        SwingUtilities.invokeLater(FinanceAppGUI::new); // Starts the FinanceApp GUI
    }
    
    /** 
    public static void main(String[] args) throws Exception {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
        System.out.println("\nEvent Log:");
        for (Event event : EventLog.getInstance()) {
            System.out.println(event);
        }
    }));
        new FinanceApp();  // Starts the FinanceApp console interface 
    }
    */
}