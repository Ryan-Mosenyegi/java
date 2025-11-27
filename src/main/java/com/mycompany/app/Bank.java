package com.mycompany.app;

import java.util.ArrayList;
import java.util.List;

public class Bank {

    private static Bank instance;

    private final List<Customer> customers = new ArrayList<>();
    private final List<Account> accounts = new ArrayList<>();
    private final List<Admin> admins = new ArrayList<>();

    public Bank() { }
    public Bank(boolean loading) { }

    public static Bank getInstance() {
        if (instance == null) {
            instance = FileDatabase.load();
        }
        return instance;
    }

    public List<Customer> getCustomers() { return customers; }
    public List<Account> getAccounts() { return accounts; }
    public List<Admin> getAdmins() { return admins; }

    public Customer findCustomerById(String id) {
        for (Customer c : customers) if (c.getCustomerId().equals(id)) return c;
        return null;
    }

    public void createCustomer(String id, String fullName, boolean employed, String employer, String password) {
        Customer c = new Customer(id, fullName, employed, employer, password);
        customers.add(c);
        save();
    }

    public Customer loginCustomer(String id, String pass) {
        for (Customer c : customers)
            if (c.getCustomerId().equals(id) && c.getPassword().equals(pass)) return c;
        return null;
    }

    public boolean createAdmin(String name, String username, String password) {
        for (Admin a : admins) if (a.getUsername().equals(username)) return false;
        Admin a = new Admin(name, username, password);
        admins.add(a);
        save();
        return true;
    }

    public Admin loginAdmin(String username, String password) {
        for (Admin a : admins)
            if (a.getUsername().equals(username) && a.getPassword().equals(password)) return a;
        return null;
    }


    public Account createAccount(Customer c, String type, double initialDeposit) {

        // Investment account needs minimum 500
        if (type.equals("Investment") && initialDeposit < 500) return null;

        // Cheque account requires employment
        if (type.equals("Cheque") && !c.isEmployed()) return null;

        String accNo = "A" + (accounts.size() + 1000);

        Account acc = switch (type) {
            case "Savings" -> new SavingsAccount(accNo, c.getCustomerId(), "pass123");
            case "Cheque" -> new ChequeAccount(accNo, c.getCustomerId(), "pass123");
            case "Investment" -> new InvestmentAccount(accNo, c.getCustomerId(), "pass123", initialDeposit);
            default -> null;
        };

        if (acc != null) {
            accounts.add(acc);
            c.getAccounts().add(acc);
            save();
        }

        return acc;
    }

    public Account findAccount(String accNo) {
        for (Account a : accounts) if (a.getAccountNumber().equals(accNo)) return a;
        return null;
    }


    public boolean deleteCustomer(String customerId) {
        Customer c = findCustomerById(customerId);
        if (c != null) {
            // Remove their accounts first
            accounts.removeIf(a -> a.getCustomerId().equals(customerId));
            return customers.remove(c);
        }
        return false;
    }

    public boolean deleteAccount(String accNo) {
        Account a = findAccount(accNo);
        if (a != null) {
            Customer c = findCustomerById(a.getCustomerId());
            if (c != null) c.getAccounts().remove(a);
            return accounts.remove(a);
        }
        return false;
    }

    public void save() {
        FileDatabase.save(this);
    }
}
