package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

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
        List<Income> incomes = new ArrayList<Income>();
        incomes.add(income);
        assertEquals(incomes, budget.getIncomes());
    }

    @Test
    public void testGetTotalByCategory() {
        Budget budget = new Budget();
        budget.addExpense(new Expense(100, "Food", LocalDate.now(), "Dinner"));
        budget.addExpense(new Expense(50, "Food", LocalDate.now(), "Lunch"));
        budget.addExpense(new Expense(200, "Digital", LocalDate.now(), "Netflix"));
        assertEquals(150, budget.getTotalByCategory("Food"));
    }

    @Test
    public void testGetterMethodsMisc() {
        Budget budget = new Budget();
        Income income = new Income(500, "Salary", LocalDate.now());
        Expense expense = new Expense(100, "Food", LocalDate.now(), "Dinner");
        budget.addExpense(expense);
        budget.addIncome(income);
        assertEquals(500, income.getAmount());
        assertEquals("Salary", income.getSource());
        assertEquals(LocalDate.now(), income.getDate());
        assertEquals(LocalDate.now(), expense.getDate());
        assertEquals("Dinner", expense.getDescription());
    }
}
