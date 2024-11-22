package persistence;

import model.Budget;
import org.json.JSONObject;

import java.io.*;


// A writer that writes JSON representation of a budget to file.
public class JsonWriter {
    private static final int TAB = 4; // Indentation for JSON data
    private PrintWriter writer;
    private String destination;

    //EFFECTS: constructs writer to write to the given destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    /*
     * MODIFIES: this
     * EFFECTS: opens writer; throws FileNotFoundException if destination cannot be opened
     */
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    /*
     * MODIFIES: this
     * EFFECTS: writes JSON representation of budget to file
     */
    public void write(Budget budget) {
        JSONObject json = budget.toJson();
        saveToFile(json.toString(TAB));
    }

    /*
     * MODIFIES: this
     * EFFECTS: closes the writer
     */
    public void close() {
        writer.close();
    }

    /*
     * MODIFIES: this
     * EFFECTS: writes string to file
     */
    private void saveToFile(String json) {
        writer.print(json);
    }
}
