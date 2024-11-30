package ui;

import model.*;
import persistence.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class FinanceApp {
    private static final String JSON_STORE = "./data/budget.json";
    private Budget budget;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    /*
     * EFFECTS: runs the finance application
     */
    public FinanceApp() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runFinance();
    }

    /*
     * MODIFIES: this
     * EFFECTS: processes user input
     */
    private void runFinance() {
        boolean running = true;
        String command;

        init();

        while (running) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("g")) {
                running = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    /*
     * MODIFIES: this
     * EFFECTS: initializes budget and scanner
     */
    private void init() {
        budget = new Budget();
        input = new Scanner(System.in);
        input.useDelimiter("\r?\n|\r");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add expense");
        System.out.println("\tb -> add income");
        System.out.println("\tc -> view expenses");
        System.out.println("\td -> view summary by category");
        System.out.println("\te -> save budget to file");
        System.out.println("\tf -> load budget from file");
        System.out.println("\tg -> quit");
        System.out.println(System.getProperty("user.dir"));
    }

    /*
     * MODIFIES: this
     * EFFECTS: processes user command
     */
    private void processCommand(String command) {
        if (command.equals("a")) {
            addExpense();
        } else if (command.equals("b")) {
            addIncome();
        } else if (command.equals("c")) {
            viewExpenses();
        } else if (command.equals("d")) {
            viewSummary();
        } else if (command.equals("e")) {
            saveBudget();
        } else if (command.equals("f")) {
            loadBudget();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // Methods to handle user commands

    /*
     * MODIFIES: this
     * EFFECTS: prompts user for expense details and adds to the budget
     */
    private void addExpense() {
        System.out.print("Enter amount: ");
        double amount = input.nextDouble();
        System.out.print("Enter category: ");
        String category = input.next();
        System.out.print("Enter description: ");
        String description = input.next();
        Expense expense = new Expense(amount, category, LocalDate.now(), description);
        budget.addExpense(expense);
        System.out.println("Expense added!");
    }

    /*
     * MODIFIES: this
     * EFFECTS: prompts user for income details and adds to the budget
     */
    private void addIncome() {
        System.out.println("Enter income: ");
        double amount = input.nextDouble();
        System.out.println("Enter source: ");
        String source = input.next();
        Income income = new Income(amount, source, LocalDate.now());
        budget.addIncome(income);
        System.out.println("Income added");
    }

    /*
     * EFFECTS: displays all expenses in the budget
     */ 
    private void viewExpenses() {
        System.out.println("\nExpenses:");
        for (Expense e : budget.getExpenses()) {
            System.out.println("Amount: " + e.getAmount() + ", Category: " + e.getCategory() 
                    + ", Date: " + e.getDate() + ", Description: " + e.getDescription());
        }
    }

    /*
     * EFFECTS: prompts user for a category and displays the total expenses in that category
     */
    private void viewSummary() {
        System.out.print("Enter category: ");
        String category = input.next();
        double total = budget.getTotalByCategory(category);
        System.out.println("Total expenses for " + category + ": " + total);
    }

    /*
     * EFFECTS: saves the current budget to a JSON file
     */
    private void saveBudget() {
        try {
            jsonWriter.open();
            jsonWriter.write(budget);
            jsonWriter.close();
            System.out.println("Saved budget to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: loads the budget from a JSON file
     */
    private void loadBudget() {
        try {
            budget = jsonReader.read();
            System.out.println("Loaded budget from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}