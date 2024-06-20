public class Customer {

    // Private final variables to store customer information
    private final String firstName;
    private final String lastName;
    private final String ssn;
    private final Account account;

    // Constructor for the Customer class, takes first name, last name, ssn, and account as parameters
    Customer(String firstName, String lastName, String ssn, Account account) {
        this.firstName = firstName; // Initialize the first name
        this.lastName = lastName; // Initialize the last name
        this.ssn = ssn; // Initialize the social security number
        this.account = account; // Initialize the account
    }

    // Override the toString method to provide a custom string representation of the Customer
    @Override
    public String toString() {
        return "\nCustomer Information\n" + // Header for customer information
                "First Name: " + firstName + "\n" + // Provide first name
                "Last Name: " + lastName + "\n" + // Provide last name
                "SSN: " + ssn + "\n" + // Provide social security number
                account; // Provide account information by calling its toString method
    }

    // Method to return basic customer information
    public String basicInfo() {
        return "First Name: " + firstName + // Provide first name
                " Last Name: " + lastName + // Provide last name
                " SSN: " + ssn + // Provide social security number
                " Account Number: " + account.getAccountNumber(); // Provide account number
    }

    // Getter method for the account
    Account getAccount() {
        return account; // Return the account object
    }
}
