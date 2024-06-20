import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    // Scanner object for reading user input
    Scanner keyboard = new Scanner(System.in);
    // Bank object that holds customers and their accounts
    Bank bank = new Bank();
    // Boolean flag to control the menu loop
    boolean exit;

    public static void main(String[] Args){
        // Create a new Menu object and start the menu loop
        Menu menu = new Menu();
        menu.runMenu();
    }

    // Method to start the menu loop
    public void runMenu(){
        printHeader(); // Print the header
        while(!exit){
            printMenu(); // Print the menu options
            int choice = getInput(); // Get user input for the menu choice
            performAction(choice); // Perform the chosen action
        }
    }

    // Method to print the header
    private void printHeader(){
        System.out.println("_____________________________________");
        System.out.println(":              Welcome              :");
        System.out.println(":                to                 :");
        System.out.println(":               Bank                :");
        System.out.println("_____________________________________");
    }

    // Method to print the menu options
    private void printMenu(){
        System.out.println("Selection:");
        System.out.println("1) Create New Account");
        System.out.println("2) Make a Deposit");
        System.out.println("3) Make a Withdrawal");
        System.out.println("4) List Account Balance");
        System.out.println("5) Quit");
    }

    // Method to get and validate user input for the menu choice
    private int getInput(){
        int choice = -1;
        do {
            System.out.println("Input Choices:");
            try {
                // Parse user input to integer
                choice = Integer.parseInt(keyboard.nextLine());
            } catch (NumberFormatException e) {
                // Handle invalid input (non-integer)
                System.out.println("Invalid Input, Only Numbers");
            }
            // Check if the choice is within valid range
            if (choice < 0 || choice > 4) {
                System.out.println("Choice Does not Exist");
            }
        } while(choice < 0 || choice > 4); // Repeat until valid input
        return choice;
    }

    // Method to perform the action based on user choice
    private void performAction(int choice){
        switch (choice){
            case 0:
                // Exit the program
                System.out.println("Thank You");
                System.exit(0);
                break;
            case 1:
                // Create a new account
                createAccount();
                break;
            case 2:
                // Make a deposit
                makeDeposit();
                break;
            case 3:
                // Make a withdrawal
                makeWithdrawal();
                break;
            case 4:
                // List account balance
                listAccountBalance();
                break;
            default:
                // Handle unexpected input
                System.out.println("An Error has occured");
        }
    }

    // Method to create a new account
    private void createAccount(){
        String firstName, lastName, ssn, accountType = "";
        double initialDeposit = 0;
        boolean valid = false;
        // Prompt for account type until valid input
        while (!valid) {
            System.out.print("Please Enter an account type(checking or saving):");
            accountType = keyboard.nextLine();
            if(accountType.equalsIgnoreCase("Checking") || accountType.equalsIgnoreCase("Savings")){
                valid = true;
            } else {
                System.out.println("Invalid account Type");
            }
        }
        // Prompt for customer's first name
        System.out.print("Please Enter Your First Name:");
        firstName = keyboard.nextLine();
        // Prompt for customer's last name
        System.out.print("Please Enter Your Last Name:");
        lastName = keyboard.nextLine();
        // Prompt for customer's SSN
        System.out.print("Please Enter Your SSN (Social Security Number):");
        ssn = keyboard.nextLine();
        valid = false;
        // Prompt for initial deposit until valid input
        while(!valid){
            System.out.println("Please Enter Your Initial Deposit:");
            try{
                initialDeposit = Double.parseDouble(keyboard.nextLine());
            } catch(NumberFormatException e){
                System.out.println("Invalid Needs To be A Number:");
            }
            // Check for minimum deposit requirements
            if(accountType.equalsIgnoreCase("Checking")){
                if(initialDeposit < 100){
                    System.out.println("Needs a Minimum of 100 dollars to open");
                } else {
                    valid = true;
                }
            } else if (accountType.equalsIgnoreCase("Savings")) {
                if(initialDeposit < 50) {
                    System.out.println("Needs a Minimum of 50 dollars to open");
                } else {
                    valid = true;
                }
            }
        }
        // Create the account based on type
        Account account;
        if(accountType.equalsIgnoreCase("Checking")){
            account = new Checking(initialDeposit);
        } else {
            account = new Saving(initialDeposit);
        }
        // Create a new customer with the provided information and add to bank
        Customer customer = new Customer(firstName, lastName, ssn, account);
        bank.addCustomer(customer);
    }

    // Method to handle deposit action
    private void makeDeposit() {
        // Select an account to deposit into
        int account = selectAccount();
        if(account >= 0) {
            System.out.println("How much do you want to deposit?:");
            double amount = 0;
            try {
                // Get deposit amount from user input
                amount = Double.parseDouble(keyboard.nextLine());
            } catch (NumberFormatException e) {
                amount = 0;
            }
            // Perform deposit operation on the selected account
            bank.getCustomer(account).getAccount().deposit(amount);
        }
    }

    // Method to select an account from the list of customers
    private int selectAccount() {
        ArrayList<Customer> customers = bank.getCustomers();
        if(customers.size() <= 0) {
            // No accounts available
            System.out.println("No Account:");
            return -1;
        }
        // List all available accounts
        System.out.println("Select an Account:");
        for(int i = 0; i < customers.size(); i++){
            System.out.println((i + 1) + ")" + customers.get(i).basicInfo());
        }
        int account = 0;
        System.out.println("Enter your selection:");
        try {
            // Get the selected account index from user input
            account = Integer.parseInt(keyboard.nextLine()) - 1;
        } catch (NumberFormatException e){
            account = -1;
        }
        // Validate selected account index
        if(account < 0 || account >= customers.size()){
            System.out.println("Invalid Account");
            account = -1;
        }
        return account;
    }

    // Method to list the account balance
    private void listAccountBalance(){
        // Select an account to display balance
        int account = selectAccount();
        if(account >= 0) {
            // Print the account balance
            System.out.println(bank.getCustomer(account).getAccount());
        }
    }

    // Method to handle withdrawal action
    private void makeWithdrawal(){
        // Select an account to withdraw from
        int account = selectAccount();
        if(account >= 0) {
            System.out.println("How much do you want to Withdraw?:");
            double amount = 0;
            try {
                // Get withdrawal amount from user input
                amount = Double.parseDouble(keyboard.nextLine());
            } catch (NumberFormatException e) {
                amount = 0;
            }
            // Perform withdrawal operation on the selected account
            bank.getCustomer(account).getAccount().withdraw(amount);
        }
    }
}
