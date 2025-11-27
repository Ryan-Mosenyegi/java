package com.mycompany.app;

import java.io.*;

public class FileDatabase {

    private static final String CUSTOMER_FILE = "customers.txt";
    private static final String ACCOUNT_FILE = "accounts.txt";
    private static final String ADMIN_FILE = "admins.txt";

    public static void save(Bank bank) {
        saveCustomers(bank);
        saveAccounts(bank);
        saveAdmins(bank);
    }

    private static void saveCustomers(Bank bank) {
        try (PrintWriter out = new PrintWriter(new FileWriter(CUSTOMER_FILE))) {
            for (Customer c : bank.getCustomers()) {
                out.println(
                        c.getCustomerId() + "," +
                                c.getFullName() + "," +
                                c.isEmployed() + "," +
                                (c.getEmployer() == null ? "" : c.getEmployer()) + "," +
                                c.getPassword()
                );
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

    private static void saveAdmins(Bank bank) {
        try (PrintWriter out = new PrintWriter(new FileWriter(ADMIN_FILE))) {
            for (Admin a : bank.getAdmins()) {
                out.println(a.getName() + "," + a.getUsername() + "," + a.getPassword());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** -------------------- LOAD -------------------- */
    public static Bank load() {
        Bank bank = new Bank(true);

        loadCustomers(bank);
        loadAccounts(bank);
        loadAdmins(bank);

        return bank;
    }

    private static void loadCustomers(Bank bank) {
        try (BufferedReader br = new BufferedReader(new FileReader(CUSTOMER_FILE))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");
                if (p.length == 5) {
                    String id = p[0];
                    String fullName = p[1];
                    boolean employed = Boolean.parseBoolean(p[2]);
                    String employer = p[3];
                    String password = p[4];

                    Customer c = new Customer(id, fullName, employed, employer, password);
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
                    case "InvestmentAccount" -> new InvestmentAccount(accNo, customerId, password, balance);
                    default -> null;
                };

                if (acc != null) {
                    if (!(acc instanceof InvestmentAccount)) acc.setBalance(balance);
                    bank.getAccounts().add(acc);

                    Customer c = bank.findCustomerById(customerId);
                    if (c != null) c.getAccounts().add(acc);
                }
            }
        } catch (Exception ignored) {}
    }

    private static void loadAdmins(Bank bank) {
        bank.getAdmins().clear();

        try (BufferedReader br = new BufferedReader(new FileReader(ADMIN_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");
                if (p.length == 3) {
                    bank.getAdmins().add(new Admin(p[0], p[1], p[2]));
                }
            }
        } catch (Exception ignored) {}
    }
}
