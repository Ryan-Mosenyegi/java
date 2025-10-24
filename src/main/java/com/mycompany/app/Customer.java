package com.mycompany.app;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String firstName, lastName, address, employer;
    private List<Account> accounts = new ArrayList<>();

    public Customer(String first, String last, String addr, String emp) {
        this.firstName = first;
        this.lastName = last;
        this.address = addr;
        this.employer = emp;
    }

    public String getFullName() { return firstName + " " + lastName; }
    public List<Account> getAccounts() { return accounts; }

    public SavingsAccount openSavings(String accNo, String branch) {
        SavingsAccount acc = new SavingsAccount(accNo, this, branch);
        accounts.add(acc);
        return acc;
    }

    public InvestmentAccount openInvestment(String accNo, String branch) {
        InvestmentAccount acc = new InvestmentAccount(accNo, this, branch);
        accounts.add(acc);
        return acc;
    }

    public ChequeAccount openCheque(String accNo, String branch, String employer) {
        ChequeAccount acc = new ChequeAccount(accNo, this, branch, employer);
        accounts.add(acc);
        return acc;
    }
}
