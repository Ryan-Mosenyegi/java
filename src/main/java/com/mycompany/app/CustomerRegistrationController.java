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

public class CustomerRegistrationController {

    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField addressField;
    @FXML private TextField employerField;
    @FXML private PasswordField passwordField;
    @FXML private Label messageLabel;

    private final Bank bank = Bank.getInstance();

    @FXML
    protected void onRegister(ActionEvent event) throws Exception {

        String first = firstNameField.getText().trim();
        String last = lastNameField.getText().trim();
        String address = addressField.getText().trim();
        String employerInput = employerField.getText().trim();
        String pass = passwordField.getText().trim();

        if (first.isEmpty() || last.isEmpty() || address.isEmpty() || pass.isEmpty()) {
            messageLabel.setText("All fields are required.");
            return;
        }

        // Determine employment status
        boolean employed = !employerInput.isEmpty(); // employed if they typed employer name

        String id = generateId();

        // Create the customer
        bank.createCustomer(id, first + " " + last, employed, employerInput, pass);

        messageLabel.setStyle("-fx-text-fill: green;");
        messageLabel.setText("✔ Registration successful! Your ID: " + id);
    }

    private String generateId() {
        int max = 1000;  // starting point

        for (Customer c : bank.getCustomers()) {
            try {
                String raw = c.getCustomerId().substring(1); // remove "C"
                int num = Integer.parseInt(raw);
                if (num > max) max = num;
            } catch (Exception ignored) { }
        }

        return "C" + (max + 1);
    }

    @FXML
    protected void onBack(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/com/mycompany/app/WelcomeScreen.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }
}
