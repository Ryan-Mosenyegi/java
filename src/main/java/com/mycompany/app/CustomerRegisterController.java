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

public class CustomerRegisterController {

    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField addressField;
    @FXML private PasswordField passwordField;
    @FXML private Label messageLabel;

    private final Bank bank = Bank.getInstance();

    /**
     * Handles customer registration.
     */
    @FXML
    protected void onRegister() {
        String first = firstNameField.getText().trim();
        String last = lastNameField.getText().trim();
        String addr = addressField.getText().trim();
        String pass = passwordField.getText().trim();

        if (first.isEmpty() || last.isEmpty() || addr.isEmpty() || pass.isEmpty()) {
            messageLabel.setText("All fields are required.");
            return;
        }

        Customer c = bank.registerCustomer(first, last, addr, pass);
        messageLabel.setStyle("-fx-text-fill:green;");
        messageLabel.setText("Registered successfully! Your Customer ID: " + c.getCustomerId());

        // clear fields
        firstNameField.clear();
        lastNameField.clear();
        addressField.clear();
        passwordField.clear();
    }

    /**
     * Handles the Back button — returns to the home screen.
     */
    @FXML
    protected void onBack(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 600, 400));
        stage.setTitle("Banking System - Home");
        stage.show();
    }
}
