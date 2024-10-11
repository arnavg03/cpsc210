package model;

import java.time.LocalDate;

// Represents Income having an amount (In dollars), source and date.
public class Income {
    private double amount;
    private String source;
    private LocalDate date;
 

    /*
     * REQUIRES: amount >= 0, source is not null, date is not null
     * MODIFIES: this
     * EFFECTS: Income account is set to amount; Income source is set to source
     *          Income date is set to date.
     */
    public Income(double amount, String source, LocalDate date) {
        this.amount = amount;
        this.source= source;
        this.date = date;
    }
    
    // Getter methods

    /*
     *EFFECTS: returns the amount of Income
     */
    public double getAmount() {
        return amount;
    }

    /*
     *EFFECTS: returns the category of the expense
     */
    public String getSource() {
        return source;
    }
    
    /*
     *EFFECTS: returns the date of the expense
     */
    public LocalDate getDate() {
        return date;
    }

}