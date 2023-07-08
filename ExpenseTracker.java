import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

class Expense {
    private LocalDate date;
    private double amount;
    private String category;
    private String description;

    public Expense(LocalDate date, double amount, String category, String description) {
        this.date = date;
        this.amount = amount;
        this.category = category;
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }
}

class ExpenseTracker {
    private List<Expense> expenses;
    private Map<String, Double> categoryTotals;
    private Scanner scanner;

    public ExpenseTracker() {
        expenses = new ArrayList<>();
        categoryTotals = new HashMap<>();
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        ExpenseTracker tracker = new ExpenseTracker();
        tracker.run();
    }

    public void run() {
        boolean exit = false;
        while (!exit) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    addExpense();
                    break;
                case 2:
                    viewExpenseReport();
                    break;
                case 3:
                    viewSpendingAnalysis();
                    break;
                case 4:
                    exit = true;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
        scanner.close();
    }

    private void displayMenu() {
        System.out.println("\nExpense Tracker");
        System.out.println("1. Add Expense");
        System.out.println("2. View Expense Report");
        System.out.println("3. View Spending Analysis");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    private void addExpense() {
        System.out.println("\nAdd Expense");
        System.out.print("Enter the expense date (YYYY-MM-DD): ");
        String dateStr = scanner.nextLine();
        LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ISO_DATE);

        System.out.print("Enter the expense amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline character

        System.out.print("Enter the expense category: ");
        String category = scanner.nextLine();

        System.out.print("Enter a description for the expense: ");
        String description = scanner.nextLine();

        Expense expense = new Expense(date, amount, category, description);
        expenses.add(expense);

        // Update category totals
        if (categoryTotals.containsKey(category)) {
            double total = categoryTotals.get(category);
            categoryTotals.put(category, total + amount);
        } else {
            categoryTotals.put(category, amount);
        }

        System.out.println("Expense added successfully.");
    }

    private void viewExpenseReport() {
        System.out.println("\nExpense Report");
        if (expenses.isEmpty()) {
            System.out.println("No expenses recorded.");
            return;
        }

        System.out.println("Category\tTotal Amount");
        for (Map.Entry<String, Double> entry : categoryTotals.entrySet()) {
            String category = entry.getKey();
            double totalAmount = entry.getValue();
            System.out.printf("%s\t\t$%.2f%n", category, totalAmount);
        }
    }

    private void viewSpendingAnalysis() {
        System.out.println("\nSpending Analysis");
        if (expenses.isEmpty()) {
            System.out.println("No expenses recorded.");
            return;
        }

        // Calculate average monthly expenses
        Map<Integer, Double> monthlyExpenses = new HashMap<>();
        for (Expense expense : expenses) {
            int month = expense.getDate().getMonthValue();
            double amount = expense.getAmount();

            if (monthlyExpenses.containsKey(month)) {
                double total = monthlyExpenses.get(month);
                monthlyExpenses.put(month, total + amount);
            } else {
                monthlyExpenses.put(month, amount);
            }
        }

        System.out.println("Month\t\tAverage Expense");
        for (Map.Entry<Integer, Double> entry : monthlyExpenses.entrySet()) {
            int month = entry.getKey();
            double totalExpense = entry.getValue();
            double averageExpense = totalExpense / LocalDate.now().getMonthValue();
            System.out.printf("%d\t\t$%.2f%n", month, averageExpense);
        }
    }
}