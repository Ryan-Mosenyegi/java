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

public class AdminLoginController {

    @FXML private TextField adminIdField;
    @FXML private PasswordField passwordField;
    @FXML private Label messageLabel;

    private final Bank bank = Bank.getInstance();

    @FXML
    protected void onLogin(ActionEvent event) throws Exception {

        String username = adminIdField.getText().trim();
        String pass = passwordField.getText().trim();

        Admin a = bank.loginAdmin(username, pass);

        if (a == null) {
            messageLabel.setText("Invalid Username or Password.");
            return;
        }

        SessionManager.setCurrentAdmin(a);

        Parent root = FXMLLoader.load(getClass().getResource("AdminDashboard.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 700, 500));
        stage.show();
    }

    @FXML
    protected void onRegister(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("AdminRegistration.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    @FXML
    protected void onBack(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("WelcomeScreen.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }
}
