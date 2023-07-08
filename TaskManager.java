import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Task {
    private String name;
    private String description;
    private boolean isComplete;

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
        this.isComplete = false;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }
}

public class TaskManager {
    private List<Task> tasks;
    private Scanner scanner;

    public TaskManager() {
        tasks = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        taskManager.run();
    }

    public void run() {
        boolean exit = false;
        while (!exit) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    addTask();
                    break;
                case 2:
                    completeTask();
                    break;
                case 3:
                    viewTasks();
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
        System.out.println("\nTask Manager");
        System.out.println("1. Add Task");
        System.out.println("2. Complete Task");
        System.out.println("3. View Tasks");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    private void addTask() {
        System.out.println("\nAdd Task");
        System.out.print("Enter task name: ");
        String name = scanner.nextLine();
        System.out.print("Enter task description: ");
        String description = scanner.nextLine();

        Task task = new Task(name, description);
        tasks.add(task);
        System.out.println("Task added successfully.");
    }

    private void completeTask() {
        System.out.println("\nComplete Task");
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
            return;
        }

        System.out.println("Select a task to complete:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.println((i + 1) + ". " + task.getName());
        }

        System.out.print("Enter the task number: ");
        int taskNumber = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        if (taskNumber < 1 || taskNumber > tasks.size()) {
            System.out.println("Invalid task number. Please try again.");
            return;
        }

        Task task = tasks.get(taskNumber - 1);
        task.setComplete(true);
        System.out.println("Task marked as complete.");
    }

    private void viewTasks() {
        System.out.println("\nView Tasks");
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                String status = task.isComplete() ? "Complete" : "Incomplete";
                System.out.println((i + 1) + ". " + task.getName() + " - " + status);
            }
        }
    }
}