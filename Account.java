public class Account {
    // Private variables to store balance, interest rate, and account number
    private double balance = 0;
    private double interest = 0.02; // Interest rate initialized to 2%
    private int accountNumber;
    private static int numberOfAccounts = 1000000; // Static variable to generate unique account numbers

    // Constructor for the Account class
    Account() {
        accountNumber = numberOfAccounts++; // Assign unique account number and increment the counter
    }

    // Getter method for balance
    public double getBalance() {
        return balance;
    }

    // Getter method for interest rate in percentage
    public double getInterest() {
        return interest * 100;
    }

    // Getter method for account number
    public int getAccountNumber() {
        return accountNumber;
    }

    // Setter method for balance
    public void setBalance(double balance) {
        this.balance = balance;
    }

    // Setter method for interest rate
    public void setInterest(double interest) {
        this.interest = interest;
    }

    // Method to withdraw a specified amount
    public void withdraw(double amount) {
        // Check if the amount to withdraw plus fee exceeds the balance
        if (amount + 5 > balance) {
            System.out.println("Insufficient Funds");
            return;
        }
        balance -= amount + 5; // Deduct amount and fee from balance
        checkInterest(0); // Recalculate interest rate based on new balance
        System.out.println("Withdrawn " + amount + " with a fee of 5 dollars");
        System.out.println("Balance " + balance);
    }

    // Method to deposit a specified amount
    public void deposit(double amount) {
        // Check if the amount to deposit is less than or equal to 0
        if (amount <= 0) {
            System.out.println("You have no money to deposit");
            return;
        }
        checkInterest(amount); // Recalculate interest rate based on new balance
        amount = amount + amount * interest; // Add interest to deposit amount
        balance += amount; // Add amount to balance
        System.out.println("Deposit " + amount + " with an interest of " + (interest * 100) + "%");
        System.out.println("Balance " + balance);
    }

    // Method to check and set interest rate based on balance
    public void checkInterest(double amount) {
        // If the new balance exceeds 10000, set interest rate to 5%
        if (balance + amount > 10000) {
            interest = 0.05;
        } else {
            interest = 0.02; // Otherwise, set interest rate to 2%
        }
    }
}
