import java.util.ArrayList;

// The Bank class represents a bank with a list of customers.
public class Bank {
    // ArrayList to store Customer objects.
    ArrayList<Customer> customers = new ArrayList<Customer>();

    // Method to add a new customer to the bank.
    void addCustomer(Customer customer) {
        customers.add(customer);
    }

    // Method to retrieve a customer by their account index.
    // @return the Customer object at the specified index.
    Customer getCustomer(int account) {
        return customers.get(account);
    }

    // Method to retrieve the list of all customers.
    // @return an ArrayList of all Customer objects.
    ArrayList<Customer> getCustomers(){
        return customers;
    }
}

