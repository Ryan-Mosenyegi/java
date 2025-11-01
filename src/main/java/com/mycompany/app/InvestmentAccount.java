package com.mycompany.app;

public class InvestmentAccount extends Account implements ApplyInterest {
    //FIX THIS IT suppose to have 0.05 in its inetrface
    private double interestRate = 0.05; // 5% monthly

    public InvestmentAccount(String accNo, String customerId, String password) {
        super(accNo, customerId, password);
    }

    @Override
    public void applyMonthlyInterest() {
        double interest = getBalance() * interestRate;
        deposit(interest);
    }

    @Override
    public String toString() {
        return "Investment Account - " + getAccountNumber();
    }
}
