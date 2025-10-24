package com.mycompany.app;

public class InvestmentAccount extends Account implements Withdrawable, MonthlyInterest {
    public InvestmentAccount(String accNo, Customer customer, String branch) {
        super(accNo, customer, branch);
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= balance)
            balance -= amount + 20; // penalty fee
    }

    @Override
    public void applyMonthlyInterest() {
        balance += balance * 0.05;
    }
}
