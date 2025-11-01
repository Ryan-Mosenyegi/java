package com.mycompany.app;

public abstract class Account {
    private String accountNumber;
    private double balance;
    private String password; // Customer transaction password
    private String customerId; // Links account to customer login

    public Account(String accountNumber, String customerId, String password) {
        this.accountNumber = accountNumber;
        this.customerId = customerId;
        this.password = password;
        this.balance = 0.0;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getPassword() {
        return password;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0)
            balance += amount;
    }

    protected void reduceBalance(double amount) {
        balance -= amount;
    }
}
