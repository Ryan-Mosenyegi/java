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

public class CustomerLoginController {

    @FXML private TextField customerIdField;
    @FXML private PasswordField passwordField;
    @FXML private Label messageLabel;

    private final Bank bank = Bank.getInstance();

    @FXML
    protected void onLogin(ActionEvent event) throws Exception {

        String id = customerIdField.getText().trim();
        String pass = passwordField.getText();

        Customer c = bank.loginCustomer(id, pass);

        if (c == null) {
            messageLabel.setText("Invalid credentials.");
            return;
        }

        SessionManager.setCurrentCustomer(c);

        // ✅ FIXED FXML PATH
        Parent root = FXMLLoader.load(getClass().getResource("/com/mycompany/app/CustomerDashboard.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 700, 500));
        stage.show();
    }

    @FXML
    protected void onBack(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/com/mycompany/app/WelcomeScreen.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }
}
