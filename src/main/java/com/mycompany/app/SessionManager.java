package com.mycompany.app;

public class SessionManager {

    private static Customer currentCustomer;
    private static Admin currentAdmin;

    public static void setCurrentCustomer(Customer c) { currentCustomer = c; }
    public static Customer getCurrentCustomer() { return currentCustomer; }
    public static void clear() { currentCustomer = null; }

    public static void setCurrentAdmin(Admin a) { currentAdmin = a; }
    public static Admin getCurrentAdmin() { return currentAdmin; }
    public static void clearAdmin() { currentAdmin = null; }
}
