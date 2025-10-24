package com.mycompany.app;

public abstract class Account {
    protected String accountNumber;
    protected Customer customer;
    protected String branch;
    protected double balance;

    public Account(String accountNumber, Customer customer, String branch) {
        this.accountNumber = accountNumber;
        this.customer = customer;
        this.branch = branch;
        this.balance = 0.0;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public String getAccountNumber() { return accountNumber; }
    public String getBranch() { return branch; }
    public double getBalance() { return balance; }
}
