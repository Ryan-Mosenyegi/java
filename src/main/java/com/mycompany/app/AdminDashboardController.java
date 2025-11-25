package com.mycompany.app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminDashboardController {

    @FXML private ListView<String> customersList;
    @FXML private ListView<String> allAccountsList;
    @FXML private TextField searchField;
    @FXML private Label messageLabel;

    private final Bank bank = Bank.getInstance();

    @FXML
    public void initialize() {
        refresh();

        customersList.getSelectionModel().selectedItemProperty().addListener((obs, oldV, newV) -> {
            if (newV != null) {
                filterAccountsForSelectedCustomer(newV);
            }
        });
    }

    private void refresh() {
        loadCustomers(bank.getCustomers());
        loadAccounts(bank.getAccounts());
    }

    private void loadCustomers(Iterable<Customer> list) {
        ObservableList<String> items = FXCollections.observableArrayList();
        for (Customer c : list) {
            items.add(c.getCustomerId() + " - " + c.getFullName());
        }
        customersList.setItems(items);
    }

    private void loadAccounts(Iterable<Account> list) {
        ObservableList<String> items = FXCollections.observableArrayList();
        for (Account a : list) {
            items.add(
                    a.getAccountNumber() + " - " +
                            a.getClass().getSimpleName() +
                            " - Balance: " + String.format("%.2f", a.getBalance())
            );
        }
        allAccountsList.setItems(items);
    }


    private void filterAccountsForSelectedCustomer(String selected) {
        String customerId = selected.split(" - ")[0];

        ObservableList<String> filtered = FXCollections.observableArrayList();

        for (Account a : bank.getAccounts()) {
            if (a.getCustomerId().equals(customerId)) {
                filtered.add(
                        a.getAccountNumber() + " - " +
                                a.getClass().getSimpleName() +
                                " - Balance: " + String.format("%.2f", a.getBalance())
                );
            }
        }

        allAccountsList.setItems(filtered);
    }

    @FXML
    protected void onApplyInterestAll() {
        int count = 0;

        for (Account a : bank.getAccounts()) {
            if (a instanceof MonthlyInterest i) {
                i.applyMonthlyInterest();
                count++;
            }
        }

        bank.save();
        messageLabel.setText("Interest applied to " + count + " accounts.");
        refresh();
    }

    @FXML
    protected void onSearch() {
        String q = searchField.getText().toLowerCase();
        ObservableList<Customer> filtered = FXCollections.observableArrayList();

        for (Customer c : bank.getCustomers()) {
            if (c.getFullName().toLowerCase().contains(q) ||
                    c.getCustomerId().toLowerCase().contains(q)) {
                filtered.add(c);
            }
        }

        loadCustomers(filtered);
    }

    @FXML
    protected void onBack() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("WelcomeScreen.fxml"));
        Stage stage = (Stage) customersList.getScene().getWindow();
        stage.setScene(new Scene(loader.load(), 600, 400));
    }
}
