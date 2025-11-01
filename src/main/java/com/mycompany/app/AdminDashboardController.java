package com.mycompany.app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class AdminDashboardController {
    @FXML private ListView<String> customersList;
    @FXML private ListView<String> allAccountsList;
    @FXML private Label messageLabel;

    private Bank bank = Bank.getInstance();

    @FXML
    public void initialize() {
        refresh();
    }

    private void refresh() {
        ObservableList<String> custItems = FXCollections.observableArrayList();
        for (Customer c : bank.getCustomers()) {
            custItems.add(c.getCustomerId() + " - " + c.getFullName());
        }
        customersList.setItems(custItems);

        ObservableList<String> acctItems = FXCollections.observableArrayList();
        for (Account a : bank.getAllAccounts()) {
            acctItems.add(a.getAccountNumber() + " - " + a.getClass().getSimpleName() + " - Balance: " + String.format("%.2f", a.getBalance()));
        }
        allAccountsList.setItems(acctItems);
    }

    @FXML
    protected void onApplyInterestAll() {
        int count = 0;
        for (Account a : bank.getAllAccounts()) {
            if (a instanceof ApplyInterest ai) {
                ai.applyMonthlyInterest();
                count++;
            }
        }
        messageLabel.setText("Applied interest to " + count + " accounts.");
        refresh();
    }
}
