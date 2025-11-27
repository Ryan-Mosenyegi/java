package com.mycompany.app;

public class SavingsAccount extends Account implements MonthlyInterest {

    public SavingsAccount(String accNo, String customerId, String password) {
        super(accNo, customerId, password);
    }

    @Override
    public void applyMonthlyInterest() {
        double rate = 0.005; // 0.5% monthly interest
        deposit(getBalance() * rate);
    }

    @Override
    public String toString() {
        return "Savings Account - " + getAccountNumber();
    }
}
