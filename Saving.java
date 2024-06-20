public class Saving extends Account {
    // Static variable to store the type of the account
    private static String accountType = "Saving";

    // Constructor for the Saving class, takes an initial deposit as a parameter
    Saving(double initialDeposit) {
        super(); // Call the constructor of the superclass (Account)
        this.setBalance(initialDeposit); // Set the initial balance for the saving account
        this.checkInterest(0); // Check interest eligibility with an initial balance of 0
    }

    // Override the toString method to provide a custom string representation of the Saving account
    @Override
    public String toString() {
        return "Account Type:" + accountType + "Account\n" +
                "Account Number:" + this.getAccountNumber() + "\n" +
                "Balance" + this.getBalance() + "\n" +
                "Interest Rate:" + this.getInterest() + "%\n";
    }
}

