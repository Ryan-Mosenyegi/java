package com.mycompany.app;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

public class CustomerLoginController {
    @FXML private TextField customerIdField;
    @FXML private PasswordField passwordField;
    @FXML private Label messageLabel;

    private Bank bank = Bank.getInstance();

    @FXML
    protected void onLogin(ActionEvent event) throws Exception {
        String id = customerIdField.getText().trim();
        String pass = passwordField.getText();

        if (id.isEmpty() || pass.isEmpty()) {
            messageLabel.setText("Enter ID and password.");
            return;
        }

        Customer c = bank.loginCustomer(id, pass);
        if (c == null) {
            messageLabel.setText("Invalid credentials.");
            return;
        }

        // set session and open customer dashboard
        SessionManager.setCurrentCustomer(c);
        Parent root = FXMLLoader.load(getClass().getResource("customer-dashboard.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 700, 500));
        stage.show();
    }
}
