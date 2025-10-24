package com.mycompany.app;

public class ChequeAccount extends Account implements Withdrawable {
    private String employer;

    public ChequeAccount(String accNo, Customer customer, String branch, String employer) {
        super(accNo, customer, branch);
        this.employer = employer;
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= balance)
            balance -= amount;
    }
}
