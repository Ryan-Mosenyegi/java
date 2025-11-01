package com.mycompany.app;

public class ChequeAccount extends Account implements Withdrawable {

    public ChequeAccount(String accNo, String customerId, String password) {
        super(accNo, customerId, password);
    }

    @Override
    public void withdraw(double amount) {
        if (amount > 0 && getBalance() >= amount) {
            reduceBalance(amount);
        }
    }

    @Override
    public String toString() {
        return "Cheque Account - " + getAccountNumber();
    }
}
