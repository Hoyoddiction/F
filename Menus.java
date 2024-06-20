import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Menus extends JFrame {
    private Bank bank; // Bank object to manage customers
    private JTextArea displayArea; // Area to display customer information
    private JTextField accountField; // Text field for account number input
    private JTextField firstNameField; // Text field for first name input
    private JTextField lastNameField; // Text field for last name input
    private JTextField ssnField; // Text field for SSN input
    private JTextField initialDepositField; // Text field for initial deposit input
    private JComboBox<String> accountTypeBox; // Dropdown to select account type (Saving or Checking)

    public Menus() {
        bank = new Bank();
        setTitle("Bank Application");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel to hold the input fields and buttons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 2));

        // Adding components to the panel
        panel.add(new JLabel("Account Number:"));
        accountField = new JTextField();
        panel.add(accountField);

        panel.add(new JLabel("First Name:"));
        firstNameField = new JTextField();
        panel.add(firstNameField);

        panel.add(new JLabel("Last Name:"));
        lastNameField = new JTextField();
        panel.add(lastNameField);

        panel.add(new JLabel("SSN:"));
        ssnField = new JTextField();
        panel.add(ssnField);

        panel.add(new JLabel("Initial Deposit:"));
        initialDepositField = new JTextField();
        panel.add(initialDepositField);

        panel.add(new JLabel("Account Type:"));
        accountTypeBox = new JComboBox<>(new String[]{"Saving", "Checking"});
        panel.add(accountTypeBox);

        // Button to add a new customer
        JButton addButton = new JButton("Add Customer");
        addButton.addActionListener(new AddCustomerActionListener());
        panel.add(addButton);

        // Button to view all customers
        JButton viewButton = new JButton("View All Customers");
        viewButton.addActionListener(new ViewAllCustomersActionListener());
        panel.add(viewButton);

        // Text area to display customer information
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        // Adding the panel and the text area to the frame
        add(panel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    // Action listener for adding a new customer
    private class AddCustomerActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                // Getting input values from the text fields
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String ssn = ssnField.getText();
                double initialDeposit = Double.parseDouble(initialDepositField.getText());
                String accountType = (String) accountTypeBox.getSelectedItem();

                // Creating the appropriate account based on the selected account type
                Account account;
                if ("Saving".equals(accountType)) {
                    account = new Saving(initialDeposit);
                } else {
                    account = new Checking(initialDeposit);
                }

                // Creating a new customer and adding to the bank
                Customer customer = new Customer(firstName, lastName, ssn, account);
                bank.addCustomer(customer);

                // Displaying confirmation in the text area
                displayArea.setText("Customer added:\n" + customer);
            } catch (NumberFormatException ex) {
                // Displaying error message if the initial deposit is not a valid number
                JOptionPane.showMessageDialog(Menus.this, "Invalid deposit amount.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Action listener for viewing all customers
    private class ViewAllCustomersActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Getting the list of all customers from the bank
            ArrayList<Customer> customers = bank.getCustomers();
            StringBuilder sb = new StringBuilder("All Customers:\n");
            for (Customer customer : customers) {
                sb.append(customer).append("\n");
            }
            // Displaying the list of all customers in the text area
            displayArea.setText(sb.toString());
        }
    }

    public static void main(String[] args) {
        // Launching the application
        SwingUtilities.invokeLater(() -> {
            Menus app = new Menus();
            app.setVisible(true);
        });
    }
}
