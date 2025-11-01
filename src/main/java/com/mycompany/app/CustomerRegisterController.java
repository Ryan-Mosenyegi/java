package com.mycompany.app;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class CustomerRegisterController {
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField addressField;
    @FXML private PasswordField passwordField;
    @FXML private Label messageLabel;

    private Bank bank = Bank.getInstance();

    @FXML
    protected void onRegister() {
        String first = firstNameField.getText().trim();
        String last = lastNameField.getText().trim();
        String addr = addressField.getText().trim();
        String pass = passwordField.getText();

        if (first.isEmpty() || last.isEmpty() || pass.isEmpty()) {
            messageLabel.setText("First name, last name and password are required.");
            return;
        }

        Customer c = bank.registerCustomer(first, last, addr, pass);
        messageLabel.setText("Registered! Your Customer ID: " + c.getCustomerId());

        // clear fields
        firstNameField.clear();
        lastNameField.clear();
        addressField.clear();
        passwordField.clear();
    }
}
