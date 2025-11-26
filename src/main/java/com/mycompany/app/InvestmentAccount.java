package com.mycompany.app;

public class InvestmentAccount extends Account implements MonthlyInterest, Withdrawable {

    public InvestmentAccount(String accNo, String customerId, String password, double openingBalance) {
        super(accNo, customerId, password);

        if (openingBalance < 500) {
            throw new IllegalArgumentException("You need at least 500 to open an Investment Account.");
        }

        deposit(openingBalance);
    }

    @Override
    public void applyMonthlyInterest() {
        double rate = 0.05; // 5% monthly interest
        deposit(getBalance() * rate);
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount > 0 && getBalance() >= amount) {
            reduceBalance(amount);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Investment Account - " + getAccountNumber();
    }
}
