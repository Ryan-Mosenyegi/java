package com.mycompany.app;

public abstract class Account {

    private String accountNumber;
    private String customerId;
    private double balance;
    private String password;

    public Account(String accountNumber, String customerId, String password) {
        this.accountNumber = accountNumber;
        this.customerId = customerId;
        this.password = password;
        this.balance = 0.0;
    }

    public String getAccountNumber() { return accountNumber; }
    public String getCustomerId() { return customerId; }
    public double getBalance() { return balance; }
    public String getPassword() { return password; }

    public void setBalance(double b) { this.balance = b; }

    public void deposit(double amount) {
        if (amount > 0) balance += amount;
    }

    protected void reduceBalance(double amount) {
        balance -= amount;
    }
}
