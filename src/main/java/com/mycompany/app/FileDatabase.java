package com.mycompany.app;

import java.io.*;

public class FileDatabase {

    private static final String CUSTOMER_FILE = "customers.txt";
    private static final String ACCOUNT_FILE = "accounts.txt";

    public static void save(Bank bank) {
        saveCustomers(bank);
        saveAccounts(bank);
    }

    private static void saveCustomers(Bank bank) {
        try (PrintWriter out = new PrintWriter(new FileWriter(CUSTOMER_FILE))) {
            for (Customer c : bank.getCustomers()) {
                out.println(c.getCustomerId() + "," + c.getFullName() + "," + c.getPassword());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void saveAccounts(Bank bank) {
        try (PrintWriter out = new PrintWriter(new FileWriter(ACCOUNT_FILE))) {
            for (Account a : bank.getAccounts()) {
                out.println(
                        a.getAccountNumber() + "," +
                                a.getCustomerId() + "," +
                                a.getBalance() + "," +
                                a.getPassword() + "," +
                                a.getClass().getSimpleName()
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Bank load() {
        Bank bank = new Bank(true);

        loadCustomers(bank);
        loadAccounts(bank);

        return bank;
    }

    private static void loadCustomers(Bank bank) {
        try (BufferedReader br = new BufferedReader(new FileReader(CUSTOMER_FILE))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");
                if (p.length == 3) {
                    Customer c = new Customer(p[0], p[1], p[2]);
                    bank.getCustomers().add(c);
                }
            }
        } catch (Exception ignored) {}
    }

    private static void loadAccounts(Bank bank) {
        try (BufferedReader br = new BufferedReader(new FileReader(ACCOUNT_FILE))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");
                if (p.length != 5) continue;

                String accNo = p[0];
                String customerId = p[1];
                double balance = Double.parseDouble(p[2]);
                String password = p[3];
                String type = p[4];

                Account acc = switch (type) {
                    case "SavingsAccount" -> new SavingsAccount(accNo, customerId, password);
                    case "ChequeAccount" -> new ChequeAccount(accNo, customerId, password);
                    case "InvestmentAccount" -> new InvestmentAccount(accNo, customerId, password);
                    default -> null;
                };

                if (acc != null) {
                    acc.setBalance(balance);
                    bank.getAccounts().add(acc);

                    Customer c = bank.findCustomerById(customerId);
                    if (c != null) c.getAccounts().add(acc);
                }
            }
        } catch (Exception ignored) {}
    }
}
