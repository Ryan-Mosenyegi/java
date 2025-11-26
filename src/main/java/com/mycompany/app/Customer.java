package com.mycompany.app;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    private String customerId;
    private String fullName;
    private boolean employed;
    private String employer;  // null or empty if unemployed
    private String password;
    private List<Account> accounts = new ArrayList<>();

    public Customer(String customerId, String fullName, boolean employed, String employer, String password) {
        this.customerId = customerId;
        this.fullName = fullName;
        this.employed = employed;
        this.employer = employed ? employer : "";  // enforce empty if unemployed
        this.password = password;
    }

    public String getCustomerId() { return customerId; }

    public String getFullName() { return fullName; }

    public boolean isEmployed() { return employed; }

    public String getEmployer() { return employer; }

    public String getPassword() { return password; }

    public List<Account> getAccounts() { return accounts; }

    public void setPassword(String password) { this.password = password; }
}
