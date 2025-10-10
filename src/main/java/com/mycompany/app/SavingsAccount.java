package com.mycompany.app;

/**
 * A savings account that earns a small monthly interest
 * and does not allow withdrawals.
 */
public class SavingsAccount extends Account implements MonthlyInterest {

    public SavingsAccount(String accountNumber, Customer owner, String branch) {
        super(accountNumber, owner, branch);
    }

    @Override
    public void withdraw(double amount) {
        System.out.println("Withdrawals are not allowed from a Savings Account.");
    }

    @Override
    public void applyMonthlyInterest() {
        double interest = balance * 0.0005; // 0.05%
        balance += interest;
        System.out.println("Monthly interest added: " + interest + " BWP.");
    }
}
