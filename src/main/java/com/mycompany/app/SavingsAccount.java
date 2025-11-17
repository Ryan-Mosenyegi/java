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
    public void applyMonthlyInterest(Account account) {
        double interestRate = 0.005; // 0.05% for savings
        double interest = account.getBalance() * interestRate;
        account.deposit(interest);
    }

    @Override
    public String toString() {
        return "Savings Account - " + getAccountNumber();
    }
}
