package com.mycompany.app;

public class SavingsAccount extends Account implements Withdrawable, ApplyInterest {

    private double interestRate = 0.005; // 0.5% monthly

    public SavingsAccount(String accNo, String customerId, String password) {
        super(accNo, customerId, password);
    }//CONSTRUCTOR CHAINING

    @Override
    public void withdraw(double amount) {
        if (amount > 0 && getBalance() >= amount) {
            reduceBalance(amount);
        }
    }

    @Override
    public void applyMonthlyInterest() {
        double interest = getBalance() * interestRate;
        deposit(interest);
    }

    @Override
    public String toString() {
        return "Savings Account - " + getAccountNumber();
    }
}
