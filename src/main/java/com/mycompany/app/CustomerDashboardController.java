package com.mycompany.app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class CustomerDashboardController {
    @FXML private Label welcomeLabel;
    @FXML private ListView<String> accountsList;
    @FXML private ComboBox<String> accountTypeCombo;
    @FXML private TextField amountField;
    @FXML private PasswordField acctPasswordField;
    @FXML private Label messageLabel;

    private Bank bank = Bank.getInstance();
    private Customer current;

    @FXML
    public void initialize() {
        current = SessionManager.getCurrentCustomer();
        welcomeLabel.setText("Welcome, " + current.getFullName() + " (ID: " + current.getCustomerId() + ")");

        accountTypeCombo.getItems().addAll("Cheque", "Savings", "Investment");
        refreshAccountsList();
    }

    private void refreshAccountsList() {
        ObservableList<String> items = FXCollections.observableArrayList();
        for (Account a : current.getAccounts()) {
            items.add(a.getAccountNumber() + " - " + a.getClass().getSimpleName() + " - Balance: " + String.format("%.2f", a.getBalance()));
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
            messageLabel.setText("Created " + acc.getAccountNumber());
            refreshAccountsList();
        } else {
            messageLabel.setText("Failed to create account.");
        }
    }

    @FXML
    protected void onDeposit() {
        String sel = accountsList.getSelectionModel().getSelectedItem();
        if (sel == null) { messageLabel.setText("Select an account."); return; }
        String accNo = sel.split(" - ")[0];
        Account acc = bank.findAccount(accNo);
        if (acc == null) { messageLabel.setText("Account not found."); return; }

        String pwd = acctPasswordField.getText();
        if (!acc.getPassword().equals(pwd)) { messageLabel.setText("Incorrect account password."); return; }

        double amount;
        try {
            amount = Double.parseDouble(amountField.getText());
        } catch (NumberFormatException e) { messageLabel.setText("Invalid amount."); return; }

        acc.deposit(amount);
        messageLabel.setText("Deposit successful. New balance: " + String.format("%.2f", acc.getBalance()));
        refreshAccountsList();
    }

    @FXML
    protected void onWithdraw() {
        String sel = accountsList.getSelectionModel().getSelectedItem();
        if (sel == null) { messageLabel.setText("Select an account."); return; }
        String accNo = sel.split(" - ")[0];
        Account acc = bank.findAccount(accNo);
        if (acc == null) { messageLabel.setText("Account not found."); return; }

        String pwd = acctPasswordField.getText();
        if (!acc.getPassword().equals(pwd)) { messageLabel.setText("Incorrect account password."); return; }

        double amount;
        try {
            amount = Double.parseDouble(amountField.getText());
        } catch (NumberFormatException e) { messageLabel.setText("Invalid amount."); return; }

        if (acc instanceof Withdrawable w) {
            w.withdraw(amount);
            messageLabel.setText("Withdraw successful. New balance: " + String.format("%.2f", acc.getBalance()));
            refreshAccountsList();
        } else {
            messageLabel.setText("This account does not allow withdrawal.");
        }
    }

    @FXML
    protected void onApplyInterest() {
        String sel = accountsList.getSelectionModel().getSelectedItem();
        if (sel == null) { messageLabel.setText("Select an account."); return; }
        String accNo = sel.split(" - ")[0];
        Account acc = bank.findAccount(accNo);
        if (acc == null) { messageLabel.setText("Account not found."); return; }

        if (acc instanceof ApplyInterest ai) {
            ai.applyMonthlyInterest();
            messageLabel.setText("Interest applied. New balance: " + String.format("%.2f", acc.getBalance()));
            refreshAccountsList();
        } else {
            messageLabel.setText("This account does not earn interest.");
        }
    }
}
