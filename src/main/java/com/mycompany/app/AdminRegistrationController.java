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

public class AdminRegistrationController {

    @FXML private TextField fullNameField;
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label messageLabel;

    private final Bank bank = Bank.getInstance();

    @FXML
    protected void onRegister(ActionEvent event) throws Exception {

        String name = fullNameField.getText().trim();
        String username = usernameField.getText().trim();
        String pass = passwordField.getText().trim();

        if (name.isEmpty() || username.isEmpty() || pass.isEmpty()) {
            messageLabel.setText("All fields are required.");
            return;
        }

        boolean success = bank.createAdmin(name, username, pass);

        if (!success) {
            messageLabel.setText("Admin username already exists!");
            return;
        }

        messageLabel.setText("Admin registered successfully!");
    }

    @FXML
    protected void onBack(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("AdminLogin.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }
}
