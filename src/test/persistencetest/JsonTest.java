package persistencetest;

import model.Expense;
import model.Income;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkExpense(double amount, String category, String description, Expense expense) {
        assertEquals(amount, expense.getAmount());
        assertEquals(category, expense.getCategory());
        assertEquals(description, expense.getDescription());
    }

    protected void checkIncome(double amount, String source, Income income) {
        assertEquals(amount, income.getAmount());
        assertEquals(source, income.getSource());
    }
}
