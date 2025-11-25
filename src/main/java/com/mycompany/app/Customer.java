package com.mycompany.app;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    private String customerId;
    private String fullName;
    private String password;
    private List<Account> accounts = new ArrayList<>();

    public Customer(String id, String name, String password) {
        this.customerId = id;
        this.fullName = name;
        this.password = password;
    }

    public String getCustomerId() { return customerId; }
    public String getFullName() { return fullName; }
    public String getPassword() { return password; }

    public List<Account> getAccounts() { return accounts; }

    public void setPassword(String p) { this.password = p; }
}
