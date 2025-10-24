package com.mycompany.app;

public class SavingsAccount extends Account implements Withdrawable, MonthlyInterest {
    public SavingsAccount(String accNo, Customer customer, String branch) {
        super(accNo, customer, branch);
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= balance)
            balance -= amount;
    }

    @Override
    public void applyMonthlyInterest() {
        balance += balance * 0.005;
    }
}
