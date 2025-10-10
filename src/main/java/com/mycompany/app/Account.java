package com.mycompany.app;

//Abstract class Account//
public abstract class Account {
    protected String accountNumber;
    protected double balance;
    protected String branch;
    protected Customer owner;

    public Account(String accountNumber, Customer owner, String branch) {
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.branch = branch;
        this.balance = 0.0;
    }

    // Overloaded deposit methods
    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited " + amount + " BWP.");
    }

    public void deposit(double amount, String source) {
        balance += amount;
        System.out.println("Deposited " + amount + " BWP from " + source + ".");
    }

    // Abstract method to be implemented by subclasses
    public abstract void withdraw(double amount);

    public double getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getBranch() {
        return branch;
    }

    public Customer getOwner() {
        return owner;
    }
}
