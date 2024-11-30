# My Personal Project : Personal Finance Tracker

## Project Proposal
The **Personal Finance Tracker** is a desktop application designed to help users effectively manage their expenses. This application allows users to record and categorize their daily expenses and income, track savings goals, and generate basic reports that can help visualize spending patterns. Users can categorize their transactions (like food, rent, services, entertainment, etc.), view monthly summaries, and do tasks such as setting targets for savings, all while maintaining a clear record of their financial history. The application should have a fully functioning GUI by the end of the term.

This application is intended for people who want a simple but effective tool to manage their personal finances. It is particularly useful for people who want to improve their financial habits by understanding how their money is used every month and setting financial goals to propagate healthy spending habits.

This project interests me because personal finance management is something me and many of my friends often struggle with. Building financial management skills is very important as an adult. Developing this application allows me to combine my interest in computer science with a real-world problem that affects many people. Creating this tool will also give me insight into the challenge of designing a flexible, user-friendly model while gaining insight into Java's Object-Oriented features.

---

## User Stories
- As a user, I want to be able to add a new expense to my finance tracker.
- As a user, I want to be able to view the list of expenses in my finance tracker.
- As a user, I want to be able to see a summary of my expenses by category.
- As a user, I want to be able to add an income source to my finance tracker.
- As a user, I want to be able to save my budget to a file.
- As a user, I want to be able to load a budget file to keep adding on to.
- As a user, I want to be able to add multiple expenses and incomes to the budget.
- As a user, I want to be able to save and load the state of my application.

---

## Instructions for End User

### Add Expenses
- Click the "Add Expense" button under the "Expenses" panel.
- Enter the details (amount, category, and description) in the prompted dialogs.
- The new expense will appear in the list.

### Filter Expenses
- Click the "Filter Expenses" button under the "Expenses" panel.
- Enter a category to filter the expense list.

### Add Income
- Click the "Add Income" button under the "Incomes" panel.
- Enter the details (amount and source) in the prompted dialogs.
- The new income will appear in the list.

### Filter Income
- Click the "Filter Incomes" button under the "Incomes" panel.
- Enter a source to filter the income list.

### Save the Budget
- Click the "Save" button at the bottom of the application.
- The current budget will be saved to a file.

### Load the Budget
- Click the "Load" button at the bottom of the application.
- The saved budget will be loaded, and all data will update in the application.

---

## Sample Event Log - Phase 4: Task 2
Below is a sample event log generated during the use of the **Personal Finance Tracker**:

```
Fri Nov 29 15:55:46 PST 2024
Added expense: burger in category food of amount 100.0
Fri Nov 29 15:55:52 PST 2024
Added expense: fries in category food of amount 50.0
Fri Nov 29 15:55:56 PST 2024
Added expense: water in category drink of amount 20.0
Fri Nov 29 15:55:58 PST 2024
Filtered expenses by category: drink
Fri Nov 29 15:56:03 PST 2024
Added income from source: ubc of amount 1000.0
Fri Nov 29 15:56:09 PST 2024
Added income from source: job of amount 100.0
Fri Nov 29 15:56:13 PST 2024
Added income from source: job of amount 100.0
Fri Nov 29 15:56:18 PST 2024
Filtered incomes by source: ubc
Fri Nov 29 15:56:20 PST 2024
Budget saved to file.
Fri Nov 29 15:56:22 PST 2024
Added expense: burger in category food of amount 100.0
Fri Nov 29 15:56:22 PST 2024
Added expense: fries in category food of amount 50.0
Fri Nov 29 15:56:22 PST 2024
Added expense: water in category drink of amount 20.0
Fri Nov 29 15:56:22 PST 2024
Added income from source: ubc of amount 1000.0
Fri Nov 29 15:56:22 PST 2024
Added income from source: job of amount 100.0
Fri Nov 29 15:56:22 PST 2024
Added income from source: job of amount 100.0
Fri Nov 29 15:56:22 PST 2024
Budget loaded from file.
```