import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ATM {
    private HashMap<String, User> users;

    public ATM() {
        users = new HashMap<>();
        users.put("user1", new User("user1", "password1", 1000.0));
        users.put("user2", new User("user2", "password2", 2000.0));
    }

    private User authenticateUser(String username, String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public void showMenu() {
        System.out.println("Welcome to the ATM!");
        System.out.println("1. Transaction History");
        System.out.println("2. Withdraw");
        System.out.println("3. Deposit");
        System.out.println("4. Transfer");
        System.out.println("5. Exit");
    }

    public void transactionHistory(User user) {
        ArrayList<String> transactionHistory = user.getTransactionHistory();
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            System.out.println("Transaction History:");
            for (String transaction : transactionHistory) {
                System.out.println(transaction);
            }
        }
    }

    public void withdraw(User user, Scanner scanner) {
        try {
            System.out.println("Enter amount to withdraw:");
            double amount = scanner.nextDouble();
            if (amount <= user.getBalance()) {
                user.setBalance(user.getBalance() - amount);
                user.addTransaction("Withdraw: $" + amount);
                System.out.println("Withdrawal successful. New balance: $" + user.getBalance());
            } else {
                System.out.println("Insufficient balance.");
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a valid amount.");
            scanner.nextLine(); 
        }
    }

    public void deposit(User user, Scanner scanner) {
        try {
            System.out.println("Enter amount to deposit:");
            double amount = scanner.nextDouble();
            user.setBalance(user.getBalance() + amount);
            user.addTransaction("Deposit: $" + amount);
            System.out.println("Deposit successful. New balance: $" + user.getBalance());
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a valid amount.");
            scanner.nextLine();
        }
    }

    public void transfer(User user, Scanner scanner) {
        try {
            System.out.println("Enter username of recipient:");
            String recipientUsername = scanner.next();
            User recipient = users.get(recipientUsername);
            if (recipient != null) {
                System.out.println("Enter amount to transfer:");
                double amount = scanner.nextDouble();
                if (amount <= user.getBalance()) {
                    user.setBalance(user.getBalance() - amount);
                    recipient.setBalance(recipient.getBalance() + amount);
                    user.addTransaction("Transfer: $" + amount + " to " + recipientUsername);
                    recipient.addTransaction("Received: $" + amount + " from " + user.getUsername());
                    System.out.println("Transfer successful. New balance: $" + user.getBalance());
                } else {
                    System.out.println("Insufficient balance.");
                }
            } else {
                System.out.println("Recipient not found.");
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a valid amount.");
            scanner.nextLine(); 
        }
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter username:");
        String username = scanner.next();
        System.out.println("Enter password:");
        String password = scanner.next();
        User user = authenticateUser(username, password);
        if (user != null) {
            System.out.println("Authentication successful. Welcome, " + user.getUsername() + "!");
            while (true) {
                showMenu();
                try {
                    int choice = scanner.nextInt();
                    switch (choice) {
                        case 1:
                            transactionHistory(user);
                            break;
                        case 2:
                            withdraw(user, scanner);
                            break;
                        case 3:
                            deposit(user, scanner);
                            break;
                        case 4:
                            transfer(user, scanner);
                            break;
                        case 5:
                            System.out.println("Thank you for using the ATM. Goodbye!");
                            scanner.close();
                            return;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid input. Please enter a valid choice.");
                    scanner.nextLine();
                }
            }
        } else {
            System.out.println("Authentication failed. Goodbye!");
        }
    }

    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.start();
    }
}
