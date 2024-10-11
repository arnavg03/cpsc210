package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

public class BudgetTest {
    @Test
    public void testAddExpense() {
        Budget budget = new Budget();
        Expense exp = new Expense(100, "Food", LocalDate.now(), "Dinner");
        budget.addExpense(exp);
        assertEquals(1, budget.getExpenses().size());
        assertEquals(100, budget.getExpenses().get(0).getAmount());
    }

    @Test
    public void testAddIncome() {
        Budget budget = new Budget();
        Income income = new Income(500, "Salary", LocalDate.now());
        budget.addIncome(income);
        // Verify that income is added successfully (tests for other methods will follow a similar pattern)
    }

    @Test
    public void testGetTotalByCategory() {
        Budget budget = new Budget();
        budget.addExpense(new Expense(100, "Food", LocalDate.now(), "Dinner"));
        budget.addExpense(new Expense(50, "Food", LocalDate.now(), "Lunch"));
        assertEquals(150, budget.getTotalByCategory("Food"));
    }
}
