package com.mycompany.app;

public class SavingsAccount extends Account implements Withdrawable, MonthlyInterest {

    public SavingsAccount(String accNo, String customerId, String password) {
        super(accNo, customerId, password);
    }

    @Override
    public void withdraw(double amount) {
        if (amount > 0 && getBalance() >= amount) {
            reduceBalance(amount);
        }
    }

    @Override
    public void applyMonthlyInterest() {
        double rate = 0.005;
        deposit(getBalance() * rate);
    }

    @Override
    public String toString() {
        return "Savings Account - " + getAccountNumber();
    }
}
