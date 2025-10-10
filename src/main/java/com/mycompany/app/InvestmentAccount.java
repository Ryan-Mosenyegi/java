package com.mycompany.app;

/**
 * An investment account that earns higher interest
 * and allows withdrawals if the balance is sufficient.
 */
public class InvestmentAccount extends Account implements MonthlyInterest {
    private double minimumDeposit = 500.0;

    public InvestmentAccount(String accountNumber, Customer owner, String branch, double initialDeposit) {
        super(accountNumber, owner, branch);
        if (initialDeposit >= minimumDeposit) {
            deposit(initialDeposit);
        } else {
            throw new IllegalArgumentException("Minimum initial deposit is 500 BWP.");
        }
    }

    @Override
    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Withdrawn " + amount + " BWP from Investment Account.");
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    @Override
    public void applyMonthlyInterest() {
        double interest = balance * 0.05; // 5%
        balance += interest;
        System.out.println("Investment interest added: " + interest + " BWP.");
    }
}
