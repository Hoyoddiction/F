public class Checking extends Account {
    // Static variable to store the account type, common to all Checking accounts
    private static String accountType = "Checking";

    // Constructor that takes an initial deposit as a parameter
    Checking(double initialDeposit) {
        super(); // Call the constructor of the superclass (Account)
        this.setBalance(initialDeposit); // Set the initial balance
        this.checkInterest(0); // Check if interest should be applied
    }

    // Overriding the toString method to provide a string representation of the Checking account details
    @Override
    public String toString() {
        return "Account Type: " + accountType + " Account\n" + // Account type
                "Account Number: " + this.getAccountNumber() + "\n" + // Account number
                "Balance: " + this.getBalance() + "\n" + // Account balance
                "Interest Rate: " + this.getInterest() + "%\n"; // Interest rate
    }
}
