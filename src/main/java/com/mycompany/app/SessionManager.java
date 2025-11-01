package com.mycompany.app;

public class SessionManager {
    private static Customer currentCustomer;

    public static void setCurrentCustomer(Customer c) { currentCustomer = c; }
    public static Customer getCurrentCustomer() { return currentCustomer; }
    public static void clear() { currentCustomer = null; }
}
