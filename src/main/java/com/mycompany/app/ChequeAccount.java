package com.mycompany.app;

/**
 * A cheque account mainly for employees, allows normal withdrawals.
 */
public class ChequeAccount extends Account {
    private String employer;

    public ChequeAccount(String accountNumber, Customer owner, String branch, String employer) {
        super(accountNumber, owner, branch);
        this.employer = employer;
    }

    @Override
    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Withdrawn " + amount + " BWP from Cheque Account.");
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    public String getEmployer() {
        return employer;
    }
}
