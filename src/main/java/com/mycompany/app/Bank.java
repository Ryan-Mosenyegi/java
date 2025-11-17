package com.mycompany.app;

import java.util.ArrayList;
import java.util.List;

public class Bank {

    private static Bank instance = new Bank();
    private List<Customer> customers = new ArrayList<>();
    private int customerCounter = 1000;
    private int accountCounter = 2000;

    private Bank() {}

    public static Bank getInstance() {
        return instance;
    }

    //Customer Registration
    public Customer registerCustomer(String first, String last, String address, String password) {
        String newId = "C" + (++customerCounter);
        Customer c = new Customer(newId, first, last, address, password);
        customers.add(c);
        return c;
    }

    //Customer Login Check
    public Customer loginCustomer(String id, String password) {
        for (Customer c : customers)
            if (c.getCustomerId().equals(id) && c.getPassword().equals(password))
                return c;
        return null;
    }

    // Create Account for Customer
    public Account createAccount(Customer customer, String type) {
        String accNo = "A" + (++accountCounter);
        String pass = customer.getPassword(); // Same password for simplicity

        Account acc = switch (type) {
            case "Cheque" -> new ChequeAccount(accNo, customer.getCustomerId(), pass);
            case "Savings" -> new SavingsAccount(accNo, customer.getCustomerId(), pass);
            case "Investment" -> new InvestmentAccount(accNo, customer.getCustomerId(), pass);
            default -> null;
        };

        if (acc != null)
            customer.addAccount(acc);

        return acc;
    }

    // Find Account by Number
    public Account findAccount(String accNo) {
        for (Customer c : customers) {
            for (Account a : c.getAccounts()) {
                if (a.getAccountNumber().equalsIgnoreCase(accNo)) {
                    return a;
                }
            }
        }
        return null;
    }

    // Get all customers
    public List<Customer> getCustomers() {
        return customers;
    }

    // Get all bank accounts
    public List<Account> getAllAccounts() {
        List<Account> all = new ArrayList<>();
        for (Customer c : customers) {
            all.addAll(c.getAccounts());
        }
        return all;
    }
}
