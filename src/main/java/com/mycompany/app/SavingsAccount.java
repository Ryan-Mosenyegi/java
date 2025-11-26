package com.mycompany.app;

public class SavingsAccount extends Account implements MonthlyInterest {

    public SavingsAccount(String accNo, String customerId, String password) {
        super(accNo, customerId, password);
    }

    // Savings accounts CANNOT withdraw. Logic handled in CustomerDashboardController.
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
