import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private double balance;
    private ArrayList<String> transactionHistory;

    public User(String username, String password, double balance) {
        this.username = username;
        this.password = password;
        this.balance = balance;
        this.transactionHistory = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void addTransaction(String transaction) {
        transactionHistory.add(transaction);
    }

    public ArrayList<String> getTransactionHistory() {
        return transactionHistory;
    }
}
