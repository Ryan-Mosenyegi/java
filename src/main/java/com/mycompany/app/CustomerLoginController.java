package com.mycompany.app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class CustomerLoginController {

    @FXML
    private TextField customerIdField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label messageLabel;

    private final Bank bank = Bank.getInstance();

    /**
     * Handles customer login validation and scene transition to the dashboard.
     */
    @FXML
    protected void onLogin(ActionEvent event) throws Exception {
        String id = customerIdField.getText().trim();
        String pass = passwordField.getText();

        if (id.isEmpty() || pass.isEmpty()) {
            messageLabel.setText("Please enter both Customer ID and Password.");
            return;
        }

        Customer c = bank.loginCustomer(id, pass);
        if (c == null) {
            messageLabel.setText("Invalid credentials. Try again.");
            return;
        }

        // Store session info and open customer dashboard
        SessionManager.setCurrentCustomer(c);
        Parent root = FXMLLoader.load(getClass().getResource("customer-dashboard.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 700, 500));
        stage.setTitle("Customer Dashboard");
        stage.show();
    }

    /**
     * Navigates back to the welcome screen.
     */
    @FXML
    protected void onBack(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("WelcomeScreen.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 600, 400));
        stage.setTitle("Banking System - Home");
        stage.show();
    }

    /**
     * Opens the customer registration screen.
     */
    @FXML
    protected void onRegister(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("customer-register.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 600, 400));
        stage.setTitle("Customer Registration");
        stage.show();
    }
}
