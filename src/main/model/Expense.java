package model;

import java.time.LocalDate;

import org.json.JSONObject;
import persistence.Writable;

// Represents an Expense having an amount (In dollars), category, date and description.
public class Expense implements Writable {
    private double amount;
    private String category;
    private LocalDate date;
    private String description;

    /*
     * REQUIRES: amount >= 0, category is not null, date is not null
     * MODIFIES: this
     * EFFECTS: expense account is set to amount; expense category is set to category
     *          expense date is set to date; expense description is set to description
     */
    public Expense(double amount, String category, LocalDate date, String description) {
        this.amount = amount;
        this.category = category;
        this.date = date;
        this.description = description;
    }
    
    // Getter methods

    /*
     *EFFECTS: returns the amount of the expense
     */
    public double getAmount() {
        return amount;
    }

    /*
     *EFFECTS: returns the category of the expense
     */
    public String getCategory() {
        return category;
    }
    
    /*
     *EFFECTS: returns the date of the expense
     */
    public LocalDate getDate() {
        return date;
    }

    /*
     *EFFECTS: returns the description of the expense
     */
    public String getDescription() {
        return description;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("amount", amount);
        json.put("category", category);
        json.put("date", date.toString());
        json.put("description", description);
        return json;
    }
}