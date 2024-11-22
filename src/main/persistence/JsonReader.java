package persistence;

import model.Budget;
import model.Expense;
import model.Income;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads budget data from JSON stored in a file.
public class JsonReader {
    private String source;

    //EFFECTS: constructs reader to read from the given source file
    public JsonReader(String source) {
        this.source = source;
    }

    /*
     * EFFECTS: reads budget from file and returns it;
     * throws IOException if reading fails
     */
    public Budget read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseBudget(jsonObject);
    }

    // EFFECTS: reads source file as a string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }

        return contentBuilder.toString();
    }
    
    // EFFECTS: parses budget from JSON object and returns it
    private Budget parseBudget(JSONObject jsonObject) {
        Budget budget = new Budget();
        addExpenses(budget, jsonObject);
        addIncomes(budget, jsonObject);
        return budget;
    }

    // MODIFIES: budget
    // EFFECTS: parses expenses from JSON object and adds them to budget
    private void addExpenses(Budget budget, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("expenses");
        for (Object json : jsonArray) {
            JSONObject nextExpense = (JSONObject) json;
            budget.addExpense(parseExpense(nextExpense));
        }
    }

    // EFFECTS: parses an expense from JSON object and returns it
    private Expense parseExpense(JSONObject jsonObject) {
        double amount = jsonObject.getDouble("amount");
        String category = jsonObject.getString("category");
        LocalDate date = LocalDate.parse(jsonObject.getString("date"));
        String description = jsonObject.getString("description");
        return new Expense(amount, category, date, description);
    }

    /*
     * MODIFIES: budget
     * EFFECTS: parses incomes from JSON object and adds them to budget
     */
    private void addIncomes(Budget budget, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("incomes");
        for (Object json : jsonArray) {
            JSONObject nextIncome = (JSONObject) json;
            budget.addIncome(parseIncome(nextIncome));
        }
    }

    /*
     * EFFECTS: parses an income from JSON object and returns it
     */
    private Income parseIncome(JSONObject jsonObject) {
        double amount = jsonObject.getDouble("amount");
        String source = jsonObject.getString("source");
        LocalDate date = LocalDate.parse(jsonObject.getString("date"));
        return new Income(amount, source, date);
    }
}
