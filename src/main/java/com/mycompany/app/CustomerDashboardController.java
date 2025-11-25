package com.mycompany.app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;

public class CustomerDashboardController {

    @FXML private Label welcomeLabel;
    @FXML private ListView<String> accountsList;
    @FXML private ComboBox<String> accountTypeCombo;
    @FXML private TextField amountField;
    @FXML private Label messageLabel;

    private final Bank bank = Bank.getInstance();
    private Customer current;

    @FXML
    public void initialize() {
        current = SessionManager.getCurrentCustomer();

        if (current != null) {
            welcomeLabel.setText("Welcome, " + current.getFullName() + " (ID: " + current.getCustomerId() + ")");
        } else {
            welcomeLabel.setText("Welcome, Guest");
        }

        accountTypeCombo.getItems().addAll("Savings", "Cheque", "Investment");
        refreshAccountsList();
    }

    private void refreshAccountsList() {
        ObservableList<String> items = FXCollections.observableArrayList();

        if (current != null) {
            for (Account a : current.getAccounts()) {
                items.add(
                        a.getAccountNumber() + " - "
                                + a.getClass().getSimpleName()
                                + " - Balance: " + String.format("%.2f", a.getBalance())
                );
            }
        }

        accountsList.setItems(items);
    }


    @FXML
    protected void onCreateAccount() {
        String type = accountTypeCombo.getValue();

        if (type == null) {
            messageLabel.setText("Select account type.");
            return;
        }

        Account acc = bank.createAccount(current, type);

        if (acc != null) {
            messageLabel.setText("Created account: " + acc.getAccountNumber());
            refreshAccountsList();
        } else {
            messageLabel.setText("Account creation failed.");
        }
    }


    @FXML
    protected void onDeposit() {
        Account acc = getSelectedAccount();
        if (acc == null) return;

        double amount;
        try {
            amount = Double.parseDouble(amountField.getText());
        } catch (NumberFormatException e) {
            messageLabel.setText("Invalid amount.");
            return;
        }

        acc.deposit(amount);
        bank.save();

        messageLabel.setText("Deposit successful. New balance: " + acc.getBalance());
        refreshAccountsList();
    }


    @FXML
    protected void onWithdraw() {
        Account acc = getSelectedAccount();
        if (acc == null) return;

        double amount;
        try {
            amount = Double.parseDouble(amountField.getText());
        } catch (NumberFormatException e) {
            messageLabel.setText("Invalid amount.");
            return;
        }

        if (acc instanceof Withdrawable w) {
            w.withdraw(amount);
            bank.save();

            messageLabel.setText("Withdraw successful. New balance: " + acc.getBalance());
            refreshAccountsList();
        } else {
            messageLabel.setText("This account does not support withdrawals.");
        }
    }


    @FXML
    protected void onLogout() {
        try {
            SessionManager.clear();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("WelcomeScreen.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) welcomeLabel.getScene().getWindow();

            stage.setScene(new Scene(loader.load(), 600, 400));
            stage.setTitle("Bank System - Home");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            messageLabel.setText("Error returning to home screen.");
        }
    }


    private Account getSelectedAccount() {
        String sel = accountsList.getSelectionModel().getSelectedItem();

        if (sel == null) {
            messageLabel.setText("Select an account.");
            return null;
        }

        String accNo = sel.split(" - ")[0];
        return bank.findAccount(accNo);
    }
}
