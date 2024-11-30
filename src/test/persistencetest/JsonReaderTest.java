package persistencetest;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

import persistence.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Budget budget = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyBudget() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyBudget.json");
        try {
            Budget budget = reader.read();
            assertEquals(0, budget.getExpenses().size());
            assertEquals(0, budget.getIncomes().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralBudget() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralBudget.json");
        try {
            Budget budget = reader.read();
            assertEquals(1, budget.getExpenses().size());
            assertEquals(1, budget.getIncomes().size());
            checkExpense(100, "Food", "Lunch", budget.getExpenses().get(0));
            checkIncome(500, "Job", budget.getIncomes().get(0));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
