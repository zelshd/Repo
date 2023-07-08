import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class PasswordManager {
    private Map<String, String> passwordMap;
    private Scanner scanner;

    public PasswordManager() {
        passwordMap = new HashMap<>();
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        PasswordManager passwordManager = new PasswordManager();
        passwordManager.run();
    }

    public void run() {
        boolean exit = false;
        while (!exit) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    storePassword();
                    break;
                case 2:
                    retrievePassword();
                    break;
                case 3:
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
        System.out.println("\nPassword Manager");
        System.out.println("1. Store Password");
        System.out.println("2. Retrieve Password");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
    }

    private void storePassword() {
        System.out.println("\nStore Password");
        System.out.print("Enter the account name: ");
        String accountName = scanner.nextLine();

        System.out.print("Enter the username: ");
        String username = scanner.nextLine();

        System.out.print("Enter the password: ");
        String password = scanner.nextLine();

        String hashedPassword = hashPassword(password);

        passwordMap.put(accountName, hashedPassword);
        System.out.println("Password stored successfully.");
    }

    private void retrievePassword() {
        System.out.println("\nRetrieve Password");
        System.out.print("Enter the account name: ");
        String accountName = scanner.nextLine();

        if (passwordMap.containsKey(accountName)) {
            System.out.println("Enter the master password: ");
            String masterPassword = scanner.nextLine();

            String storedPassword = passwordMap.get(accountName);
            String hashedMasterPassword = hashPassword(masterPassword);

            if (storedPassword.equals(hashedMasterPassword)) {
                System.out.println("Account: " + accountName);
                System.out.println("Username: " + accountName);
                System.out.println("Password: " + storedPassword);
            } else {
                System.out.println("Incorrect master password. Access denied.");
            }
        } else {
            System.out.println("Account not found.");
        }
    }

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(password.getBytes());

            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}