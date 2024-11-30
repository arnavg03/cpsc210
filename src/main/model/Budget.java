package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;


import java.util.ArrayList;
import java.util.List;

// Represents the user's entire budget, including expenses and income sources.
public class Budget implements Writable {
    private List<Expense> expenses;
    private List<Income> incomes;

    // Constructs an empty budget
    public Budget() {
        expenses = new ArrayList<Expense>();
        incomes = new ArrayList<Income>();
    }
    
    // Methods to add expenses, income, view expenses and summarize by category

    /**
     * REQUIRES: expense is not null
     * MODIFIES: this
     * EFFECTS: Adds the given expense to the list of expenses and logs the event.
     */
    public void addExpense(Expense expense) {
        expenses.add(expense);
        EventLog.getInstance().logEvent(new Event("Added expense: " + expense.getDescription() 
                + " in category " + expense.getCategory() + " of amount " + expense.getAmount()));
    }

    /**
     * REQUIRES: income is not null
     * MODIFIES: this
     * EFFECTS: Adds the given income to the list of incomes and logs the event.
     */
    public void addIncome(Income income) {
        incomes.add(income);
        EventLog.getInstance().logEvent(new Event("Added income from source: " + income.getSource() 
                + " of amount " + income.getAmount()));
    }

    /**
     * REQUIRES: category is not null
     * EFFECTS: Filters expenses by the given category and logs the event.
     */
    public List<Expense> filterExpenses(String category) {
        List<Expense> filtered = new ArrayList<>();
        for (Expense e : expenses) {
            if (e.getCategory().equalsIgnoreCase(category)) {
                filtered.add(e);
            }
        }
        EventLog.getInstance().logEvent(new Event("Filtered expenses by category: " + category));
        return filtered;
    }

    /**
     * REQUIRES: source is not null
     * EFFECTS: Filters incomes by the given source and logs the event.
     */
    public List<Income> filterIncomes(String source) {
        List<Income> filtered = new ArrayList<>();
        for (Income i : incomes) {
            if (i.getSource().equalsIgnoreCase(source)) {
                filtered.add(i);
            }
        }
        EventLog.getInstance().logEvent(new Event("Filtered incomes by source: " + source));
        return filtered;
    }

    /**
     * MODIFIES: this
     * EFFECTS: Logs an event when the budget is saved to a file.
     */
    public void saveBudget() {
        EventLog.getInstance().logEvent(new Event("Budget saved to file."));
    }

    /**
     * MODIFIES: this
     * EFFECTS: Logs an event when the budget is loaded from a file.
     */
    public void loadBudget() {
        EventLog.getInstance().logEvent(new Event("Budget loaded from file."));
    }

    /*
     * EFFECTS: Returns the list of all expenses.
     */
    public List<Expense> getExpenses() {
        return expenses;
    }

    /*
     * EFFECTS: Returns the list of all incomes.
     */
    public List<Income> getIncomes() {
        return incomes;
    }

    /*
     * REQUIRES: category is not null
     * EFFECTS: Returns the total amount of expenses in the specified category.
     */
    public double getTotalByCategory(String category) {
        double total = 0.0;
        for (Expense e : expenses) {
            if (e.getCategory().equals(category)) {
                total += e.getAmount();
            }
        }
        return total;
    }

    /*
     * EFFECTS: Returns this budget as a JSON object
     */
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("expenses", expensesToJson());
        json.put("incomes", incomesToJson());
        return json;
    }

    /*
     * EFFECTS: Returns expenses as a JSON array
     */
    private JSONArray expensesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Expense e : expenses) {
            jsonArray.put(e.toJson());
        }

        return jsonArray;
    }

    /*
     * EFFECTS: Returns incomes as a JSON array
     */
    private JSONArray incomesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Income i : incomes) {
            jsonArray.put(i.toJson());
        }

        return jsonArray;
    }
}
