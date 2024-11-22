package ui;

import model.*;
import persistence.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.*;

/**
 * Represents the GUI for the Finance Application.
 */
public class FinanceAppGUI extends JFrame {
    private static final String JSON_STORE = "./ProjectStarter/data/budget.json";
    private Budget budget;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private DefaultListModel<String> expenseListModel;
    private DefaultListModel<String> incomeListModel;
    private JPanel chartPanel;

    /**
     * MODIFIES: this
     * EFFECTS: initializes the FinanceApp GUI and sets up the application window
     */
    public FinanceAppGUI() {
        super("Finance App");
        budget = new Budget();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(800, 600);

        initializeComponents();

        setVisible(true);
    }

    /**
     * MODIFIES: this
     * EFFECTS: initializes all GUI components, including panels for expenses, incomes,
     *          a save/load panel, and a visualization panel
     */
    private void initializeComponents() {
        JPanel mainContainer = new JPanel(new BorderLayout());

        JPanel mainPanel = createMainPanel();
        mainPanel.setPreferredSize(new Dimension(400, 600));

        chartPanel = createChartPanel();
        chartPanel.setPreferredSize(new Dimension(500, 600));

        JPanel buttonsPanel = createButtonsPanel();

        mainContainer.add(mainPanel, BorderLayout.CENTER);
        mainContainer.add(chartPanel, BorderLayout.EAST);
        add(mainContainer, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);
    }

    /**
     * EFFECTS: creates the main panel containing expenses and incomes
     */
    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel(new GridLayout(2, 1));
        mainPanel.add(createExpensePanel());
        mainPanel.add(createIncomePanel());
        return mainPanel;
    }

    /**
     * EFFECTS: creates the expense panel with a list and buttons
     */
    private JPanel createExpensePanel() {
        JPanel expensePanel = new JPanel(new BorderLayout());
        expensePanel.setBorder(BorderFactory.createTitledBorder("Expenses"));
        expenseListModel = new DefaultListModel<>();
        JList<String> expenseList = new JList<>(expenseListModel);
        expensePanel.add(new JScrollPane(expenseList), BorderLayout.CENTER);

        JPanel expenseButtonPanel = new JPanel();
        JButton addExpenseButton = new JButton("Add Expense");
        JButton filterExpenseButton = new JButton("Filter Expenses");
        addExpenseButton.addActionListener(e -> addExpense());
        filterExpenseButton.addActionListener(e -> filterExpenses());
        expenseButtonPanel.add(addExpenseButton);
        expenseButtonPanel.add(filterExpenseButton);
        expensePanel.add(expenseButtonPanel, BorderLayout.SOUTH);
        return expensePanel;
    }

    /**
     * EFFECTS: creates the income panel with a list and buttons
     */
    private JPanel createIncomePanel() {
        JPanel incomePanel = new JPanel(new BorderLayout());
        incomePanel.setBorder(BorderFactory.createTitledBorder("Incomes"));
        incomeListModel = new DefaultListModel<>();
        JList<String> incomeList = new JList<>(incomeListModel);
        incomePanel.add(new JScrollPane(incomeList), BorderLayout.CENTER);

        JPanel incomeButtonPanel = new JPanel();
        JButton addIncomeButton = new JButton("Add Income");
        JButton filterIncomeButton = new JButton("Filter Incomes");
        addIncomeButton.addActionListener(e -> addIncome());
        filterIncomeButton.addActionListener(e -> filterIncomes());
        incomeButtonPanel.add(addIncomeButton);
        incomeButtonPanel.add(filterIncomeButton);
        incomePanel.add(incomeButtonPanel, BorderLayout.SOUTH);
        return incomePanel;
    }

    /**
     * EFFECTS: creates the panel with save and load buttons
     */
    private JPanel createButtonsPanel() {
        JPanel buttonsPanel = new JPanel();
        JButton saveButton = new JButton("Save");
        JButton loadButton = new JButton("Load");
        saveButton.addActionListener(e -> saveBudget());
        loadButton.addActionListener(e -> loadBudget());
        buttonsPanel.add(saveButton);
        buttonsPanel.add(loadButton);
        return buttonsPanel;
    }

    /**
     * EFFECTS: creates the chart panel for visualization
     */
    private JPanel createChartPanel() {
        chartPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawPieChart(g);
            }
        };
        chartPanel.setBorder(BorderFactory.createTitledBorder("Visualization"));
        return chartPanel;
    }

    /**
     * MODIFIES: g
     * EFFECTS: draws a pie chart representing the expense distribution by category
     *          with labeled segments, using random colors for each category. If no
     *          expenses are available, displays a "No data to display" message.
     */
    private void drawPieChart(Graphics g) {
        if (budget.getExpenses().isEmpty()) {
            g.drawString("No data to display", 150, 150);
            return;
        }
    
        Map<String, Double> categoryTotals = new HashMap<>();
        double total = 0;
    
        for (Expense e : budget.getExpenses()) {
            String category = e.getCategory();
            double amount = e.getAmount();
            categoryTotals.put(category, categoryTotals.getOrDefault(category, 0.0) + amount);
            total += amount;
        }
    
        int startAngle = 0;
    
        for (Map.Entry<String, Double> entry : categoryTotals.entrySet()) {
            double value = entry.getValue();
            String category = entry.getKey();
            int arcAngle = (int) Math.round((value / total) * 360);
    
            g.setColor(getRandomColor());
            g.fillArc(100, 75, 300, 300, startAngle, arcAngle);
    
            drawLabel(g, category, startAngle, arcAngle);
    
            startAngle += arcAngle;
        }
    }
    
    /**
     * MODIFIES: g
     * EFFECTS: draws the label for the pie chart segment
     */
    private void drawLabel(Graphics g, String category, int startAngle, int arcAngle) {
        double angle = Math.toRadians(startAngle + arcAngle / 2.0);

        int x = 100;
        int y = 75;
        int width = 300;
        int height = 300;

        int labelX = x + width / 2 + (int) (Math.cos(angle) * width / 2.5);
        int labelY = y + height / 2 - (int) (Math.sin(angle) * height / 2.5);
    
        g.setColor(Color.BLACK);
        g.drawString(category, labelX, labelY);
    }
    

    /**
     * EFFECTS: generates and returns a random color
     */
    private Color getRandomColor() {
        return new Color((int) (Math.random() * 255),
                         (int) (Math.random() * 255),
                         (int) (Math.random() * 255));
    }

    /**
     * MODIFIES: this, budget
     * EFFECTS: adds a new expense to the budget and refreshes the expense list and chart
     */
    private void addExpense() {
        String amount = JOptionPane.showInputDialog(this, "Enter Expense Amount:");
        String category = JOptionPane.showInputDialog(this, "Enter Expense Category:");
        String description = JOptionPane.showInputDialog(this, "Enter Expense Description:");
        Expense expense = new Expense(Double.parseDouble(amount), category, java.time.LocalDate.now(), description);
        budget.addExpense(expense);
        refreshExpenseList();
        chartPanel.repaint();
    }

    /**
     * MODIFIES: this
     * EFFECTS: refreshes the expense list display
     */
    private void refreshExpenseList() {
        expenseListModel.clear();
        for (Expense e : budget.getExpenses()) {
            expenseListModel.addElement("Amount: " + e.getAmount() + ", Category: " + e.getCategory()
                    + ", Desc: " + e.getDescription());
        }
    }

    /**
     * MODIFIES: this, budget
     * EFFECTS: adds a new income to the budget and refreshes the income list
     */
    private void addIncome() {
        String amount = JOptionPane.showInputDialog(this, "Enter Income Amount:");
        String source = JOptionPane.showInputDialog(this, "Enter Income Source:");
        Income income = new Income(Double.parseDouble(amount), source, java.time.LocalDate.now());
        budget.addIncome(income);
        refreshIncomeList();
    }

    /**
     * MODIFIES: this
     * EFFECTS: refreshes the income list display
     */
    private void refreshIncomeList() {
        incomeListModel.clear();
        for (Income i : budget.getIncomes()) {
            incomeListModel.addElement("Amount: " + i.getAmount() + ", Source: " + i.getSource());
        }
    }

    /**
     * MODIFIES: this
     * EFFECTS: filters expenses based on category input and updates the expense list
     */
    private void filterExpenses() {
        String category = JOptionPane.showInputDialog(this, "Enter category to filter by:");
        expenseListModel.clear();
        for (Expense e : budget.getExpenses()) {
            if (e.getCategory().equalsIgnoreCase(category)) {
                expenseListModel.addElement("Amount: " + e.getAmount() + ", Category: " + e.getCategory());
            }
        }
    }

    /**
     * MODIFIES: this
     * EFFECTS: filters incomes based on source input and updates the income list
     */
    private void filterIncomes() {
        String source = JOptionPane.showInputDialog(this, "Enter source to filter by:");
        incomeListModel.clear();
        for (Income i : budget.getIncomes()) {
            if (i.getSource().equalsIgnoreCase(source)) {
                incomeListModel.addElement("Amount: " + i.getAmount() + ", Source: " + i.getSource());
            }
        }
    }

    /**
     * MODIFIES: this, budget
     * EFFECTS: saves the budget to a file
     */
    private void saveBudget() {
        try {
            jsonWriter.open();
            jsonWriter.write(budget);
            jsonWriter.close();
            JOptionPane.showMessageDialog(this, "Budget saved successfully!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Failed to save budget.");
        }
    }

    /**
     * MODIFIES: this, budget
     * EFFECTS: loads the budget from a file
     */
    private void loadBudget() {
        try {
            budget = jsonReader.read();
            refreshExpenseList();
            refreshIncomeList();
            chartPanel.repaint();
            JOptionPane.showMessageDialog(this, "Budget loaded successfully!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Failed to load budget.");
        }
    }
}