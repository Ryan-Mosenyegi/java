package com.mycompany.app;

public class InvestmentAccount extends Account implements MonthlyInterest {

    public InvestmentAccount(String accNo, String customerId, String password) {
        super(accNo, customerId, password);
    }

    @Override
    public void applyMonthlyInterest() {
        double rate = 0.02; // 2% interest
        deposit(getBalance() * rate);
    }

    @Override
    public String toString() {
        return "Investment Account - " + getAccountNumber();
    }
}
