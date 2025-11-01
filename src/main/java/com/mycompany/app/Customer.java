package com.mycompany.app;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String customerId;
    private String firstName;
    private String lastName;
    private String address;
    private String password; // Customer login password
    private List<Account> accounts = new ArrayList<>();

    public Customer(String id, String first, String last, String address, String password) {
        this.customerId = id;
        this.firstName = first;
        this.lastName = last;
        this.address = address;
        this.password = password;
    }

    public String getCustomerId() { return customerId; }
    public String getPassword() { return password; }
    public String getFullName() { return firstName + " " + lastName; }

    public void addAccount(Account a) {
        accounts.add(a);
    }

    public List<Account> getAccounts() {
        return accounts;
    }
}
