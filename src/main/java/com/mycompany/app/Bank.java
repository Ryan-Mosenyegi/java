package com.mycompany.app;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Bank {

    private static Bank instance;

    private List<Customer> customers = new ArrayList<>();
    private List<Account> accounts = new ArrayList<>();
    private List<Admin> admins = new ArrayList<>();

    private final String ADMIN_FILE = "admins.txt";

    public Bank() {
        loadAdmins();  // Load admins from admins.txt
    }

    public Bank(boolean loading) { }

    public static Bank getInstance() {
        if (instance == null) {
            instance = FileDatabase.load();
            instance.loadAdmins(); // Ensure admins load after FileDatabase
        }
        return instance;
    }

    public List<Customer> getCustomers() { return customers; }
    public List<Account> getAccounts() { return accounts; }
    public List<Admin> getAdmins() { return admins; }

    public Customer findCustomerById(String id) {
        for (Customer c : customers) {
            if (c.getCustomerId().equals(id))
                return c;
        }
        return null;
    }

    public Customer createCustomer(String id, String fullName, String password) {
        Customer c = new Customer(id, fullName, password);
        customers.add(c);
        save();
        return c;
    }

    public Customer loginCustomer(String id, String pass) {
        for (Customer c : customers) {
            if (c.getCustomerId().equals(id) && c.getPassword().equals(pass))
                return c;
        }
        return null;
    }

    public Admin findAdminByUsername(String username) {
        for (Admin a : admins) {
            if (a.getUsername().equals(username)) {
                return a;
            }
        }
        return null;
    }

    public boolean createAdmin(String name, String username, String password) {

        // Prevent duplicate usernames
        for (Admin a : admins) {
            if (a.getUsername().equals(username)) {
                return false;
            }
        }

        Admin a = new Admin(name, username, password);
        admins.add(a);
        saveAdmins();
        return true;
    }

    public Admin loginAdmin(String username, String password) {
        for (Admin a : admins) {
            if (a.getUsername().equals(username) &&
                    a.getPassword().equals(password)) {
                return a;
            }
        }
        return null;
    }

    public Account createAccount(Customer c, String type) {
        String accNo = "A" + (accounts.size() + 1000);

        Account acc = switch (type) {
            case "Savings" -> new SavingsAccount(accNo, c.getCustomerId(), "pass123");
            case "Cheque" -> new ChequeAccount(accNo, c.getCustomerId(), "pass123");
            case "Investment" -> new InvestmentAccount(accNo, c.getCustomerId(), "pass123");
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
        for (Account a : accounts) {
            if (a.getAccountNumber().equals(accNo)) return a;
        }
        return null;
    }

    private void saveAdmins() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ADMIN_FILE))) {
            for (Admin a : admins) {
                pw.println(a.getName() + "," + a.getUsername() + "," + a.getPassword());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadAdmins() {
        admins.clear();

        File file = new File(ADMIN_FILE);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");
                if (p.length == 3) {
                    admins.add(new Admin(p[0], p[1], p[2]));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save() {
        FileDatabase.save(this);
        saveAdmins();
    }
}
