package com.mycompany.app;

public class ChequeAccount extends Account implements Withdrawable {

    public ChequeAccount(String accNo, String customerId, String password) {
        super(accNo, customerId, password);
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
        return "Cheque Account - " + getAccountNumber();
    }
}
