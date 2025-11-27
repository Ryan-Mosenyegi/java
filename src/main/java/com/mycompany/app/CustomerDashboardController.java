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
            welcomeLabel.setText(
                    "Welcome, " + current.getFullName() +
                            " (ID: " + current.getCustomerId() + ")"
            );
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
                        a.getAccountNumber() + " - " +
                                a.getClass().getSimpleName() + " - Balance: " +
                                String.format("%.2f", a.getBalance())
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

        double initialDeposit = 0;

        // Investment account requires initial deposit
        if (type.equals("Investment")) {

            try {
                initialDeposit = Double.parseDouble(amountField.getText());
            } catch (Exception e) {
                messageLabel.setText("Enter valid initial deposit for Investment.");
                return;
            }

            if (initialDeposit < 500) {
                messageLabel.setText(" Investment requires MINIMUM P500.");
                return;
            }
        }

        // Cheque account requires employment
        if (type.equals("Cheque") && !current.isEmployed()) {
            messageLabel.setText(" Only employed customers can create a Cheque account.");
            return;
        }

        Account acc = bank.createAccount(current, type, initialDeposit);

        if (acc != null) {
            messageLabel.setText(type + " account created: " + acc.getAccountNumber());
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

        if (amount <= 0) {
            messageLabel.setText("Amount must be positive.");
            return;
        }

        acc.deposit(amount);
        bank.save();

        messageLabel.setText("Deposit successful. Balance: " + acc.getBalance());
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

        if (amount <= 0) {
            messageLabel.setText("Amount must be positive.");
            return;
        }

        // Savings rule: cannot withdraw
        if (acc instanceof SavingsAccount) {
            messageLabel.setText(" Savings Accounts CANNOT withdraw.");
            return;
        }

        if (acc instanceof Withdrawable w) {

            boolean ok = w.withdraw(amount);

            if (!ok) {
                messageLabel.setText(" Insufficient funds.");
                return;
            }

            bank.save();
            messageLabel.setText("Withdraw successful. Balance: " + acc.getBalance());
            refreshAccountsList();

        } else {
            messageLabel.setText("This account does not support withdrawals.");
        }
    }

    @FXML
    protected void onLogout() {
        try {
            SessionManager.clear();

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/mycompany/app/WelcomeScreen.fxml")
            );

            Stage stage = (Stage) welcomeLabel.getScene().getWindow();
            stage.setScene(new Scene(loader.load(), 600, 400));
            stage.setTitle("Bank System - Home");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            messageLabel.setText("Error returning to home screen.");
        }
    }

    /* ---------------------------------------------------------
                        HELPERS
    --------------------------------------------------------- */
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
