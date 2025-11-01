package com.mycompany.app;

public interface MonthlyInterest {
    // Each implementing class must provide its interest rate
    double getInterestRate();

    // Default method to apply interest automatically
    default void applyMonthlyInterest(Account account) {
        double interest = account.getBalance() * getInterestRate();
        account.deposit(interest);
    }
}
