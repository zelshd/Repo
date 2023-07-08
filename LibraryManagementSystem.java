import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LibraryManagementSystem {
    private Map<Integer, Book> bookCollection;
    private Map<Integer, Patron> patronCollection;
    private Scanner scanner;

    public LibraryManagementSystem() {
        bookCollection = new HashMap<>();
        patronCollection = new HashMap<>();
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        LibraryManagementSystem system = new LibraryManagementSystem();
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
                    addBook();
                    break;
                case 2:
                    addPatron();
                    break;
                case 3:
                    borrowBook();
                    break;
                case 4:
                    returnBook();
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
        System.out.println("\nLibrary Management System");
        System.out.println("1. Add Book");
        System.out.println("2. Add Patron");
        System.out.println("3. Borrow Book");
        System.out.println("4. Return Book");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    private void addBook() {
        System.out.println("\nAdd Book");
        System.out.print("Enter book ID: ");
        int bookId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter book author: ");
        String author = scanner.nextLine();

        Book book = new Book(bookId, title, author);
        bookCollection.put(bookId, book);
        System.out.println("Book added successfully.");
    }

    private void addPatron() {
        System.out.println("\nAdd Patron");
        System.out.print("Enter patron ID: ");
        int patronId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        System.out.print("Enter patron name: ");
        String name = scanner.nextLine();

        Patron patron = new Patron(patronId, name);
        patronCollection.put(patronId, patron);
        System.out.println("Patron added successfully.");
    }

    private void borrowBook() {
        System.out.println("\nBorrow Book");
        System.out.print("Enter patron ID: ");
        int patronId = scanner.nextInt();
        System.out.print("Enter book ID: ");
        int bookId = scanner.nextInt();

        Patron patron = patronCollection.get(patronId);
        Book book = bookCollection.get(bookId);

        if (patron == null || book == null) {
            System.out.println("Invalid patron ID or book ID.");
            return;
        }

        if (book.isBorrowed()) {
            System.out.println("Book not available for borrowing.");
            return;
        }

        book.setBorrowed(true);
        patron.borrowBook(book);
        System.out.println("Book borrowed successfully.");
    }

    private void returnBook() {
        System.out.println("\nReturn Book");
        System.out.print("Enter patron ID: ");
        int patronId = scanner.nextInt();
        System.out.print("Enter book ID: ");
        int bookId = scanner.nextInt();

        Patron patron = patronCollection.get(patronId);
        Book book = bookCollection.get(bookId);

        if (patron == null || book == null) {
            System.out.println("Invalid patron ID or book ID.");
            return;
        }

        if (!book.isBorrowed() || !patron.hasBorrowedBook(book)) {
            System.out.println("Patron has not borrowed this book.");
            return;
        }

        book.setBorrowed(false);
        patron.returnBook(book);
        System.out.println("Book returned successfully.");
    }
}

class Book {
    private int id;
    private String title;
    private String author;
    private boolean isBorrowed;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isBorrowed = false;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void setBorrowed(boolean borrowed) {
        isBorrowed = borrowed;
    }
}

class Patron {
    private int id;
    private String name;
    private List<Book> borrowedBooks;

    public Patron(int id, String name) {
        this.id = id;
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }

    public boolean hasBorrowedBook(Book book) {
        return borrowedBooks.contains(book);
    }
}