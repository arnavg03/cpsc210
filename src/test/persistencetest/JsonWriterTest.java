package persistencetest;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import org.junit.jupiter.api.Test;

import java.io.IOException;


import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {

    @Test
    public void testWriterInvalidFile() {
        try {
            Budget budget = new Budget();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testWriterEmptyBudget() {
        try {
            Budget budget = new Budget();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyBudget.json");
            writer.open();
            writer.write(budget);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyBudget.json");
            budget = reader.read();
            assertEquals(0, budget.getExpenses().size());
            assertEquals(0, budget.getIncomes().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    public void testWriterGeneralBudget() {
        try {
            Budget budget = new Budget();
            budget.addExpense(new Expense(100, "Food", java.time.LocalDate.now(), "Lunch"));
            budget.addIncome(new Income(500, "Job", java.time.LocalDate.now()));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralBudget.json");
            writer.open();
            writer.write(budget);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralBudget.json");
            budget = reader.read();
            assertEquals(1, budget.getExpenses().size());
            assertEquals(1, budget.getIncomes().size());
            checkExpense(100, "Food", "Lunch", budget.getExpenses().get(0));
            checkIncome(500, "Job", budget.getIncomes().get(0));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
