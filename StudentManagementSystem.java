import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentManagementSystem {
    private List<Student> studentList;
    private Scanner scanner;

    public StudentManagementSystem() {
        studentList = new ArrayList <> ();
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        StudentManagementSystem system = new StudentManagementSystem();
        system.run();
    }

    public void run() {
        boolean exit = false;
        while (!exit) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewStudentDetails();
                    break;
                case 3:
                    updateStudentRecord();
                    break;
                case 4:
                    deleteStudent();
                    break;
                case 5:
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
        System.out.println("\nStudent Management System");
        System.out.println("1. Add Student");
        System.out.println("2. View Student Details");
        System.out.println("3. Update Student Record");
        System.out.println("4. Delete Student");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    private void addStudent() {
        System.out.println("\nAdd Student");
        System.out.print("Enter student ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        System.out.print("Enter student age: ");
        int age = scanner.nextInt();
        System.out.print("Enter student grade: ");
        int grade = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        Student student = new Student(id, name, age, grade);
        studentList.add(student);
        System.out.println("Student added successfully.");
    }

    private void viewStudentDetails() {
        System.out.println("\nView Student Details");
        System.out.print("Enter student ID or name: ");
        String searchKey = scanner.nextLine();

        boolean found = false;
        for (Student student : studentList) {
            if (student.getId().equalsIgnoreCase(searchKey) || student.getName().equalsIgnoreCase(searchKey)) {
                System.out.println("Student details:");
                System.out.println(student);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Student not found.");
        }
    }

    private void updateStudentRecord() {
        System.out.println("\nUpdate Student Record");
        System.out.print("Enter student ID or name: ");
        String searchKey = scanner.nextLine();

        boolean found = false;
        for (Student student : studentList) {
            if (student.getId().equalsIgnoreCase(searchKey) || student.getName().equalsIgnoreCase(searchKey)) {
                System.out.print("Enter new student name: ");
                String newName = scanner.nextLine();
                System.out.print("Enter new student age: ");
                int newAge = scanner.nextInt();
                System.out.print("Enter new student grade: ");
                int newGrade = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                student.setName(newName);
                student.setAge(newAge);
                student.setGrade(newGrade);

                System.out.println("Student record updated successfully.");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Student not found.");
        }
    }

    private void deleteStudent() {
        System.out.println("\nDelete Student");
        System.out.print("Enter student ID or name: ");
        String searchKey = scanner.nextLine();

        boolean found = false;
        for (Student student : studentList) {
            if (student.getId().equalsIgnoreCase(searchKey) || student.getName().equalsIgnoreCase(searchKey)) {
                studentList.remove(student);
                System.out.println("Student deleted successfully.");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Student not found.");
        }
    }
}

class Student {
    private String id;
    private String name;
    private int age;
    private int grade;

    public Student(String id, String name, int age, int grade) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.grade = grade;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getGrade() {
        return grade;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "ID: " + id + "\nName: " + name + "\nAge: " + age + "\nGrade: " + grade;
    }
}