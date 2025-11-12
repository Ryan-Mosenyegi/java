package com.mycompany.app;

public class InvestmentAccount extends Account implements MonthlyInterest {

    public InvestmentAccount(String accNo, String customerId, String password) {
        super(accNo, customerId, password);
    }

    @Override
    public void applyMonthlyInterest(Account account) {
        double interestRate = 0.02; // 2% monthly
        double interest = account.getBalance() * interestRate;
        account.deposit(interest);
    }

    @Override
    public String toString() {
        return "Investment Account - " + getAccountNumber();
    }
}
