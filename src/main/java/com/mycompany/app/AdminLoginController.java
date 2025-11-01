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

public class AdminLoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label messageLabel;

    /**
     * Handles admin login when the "Login" button is clicked.
     */
    @FXML
    public void onLogin(ActionEvent event) throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Simple hardcoded credentials (can be replaced with real verification logic)
        if ("admin".equals(username) && "1234".equals(password)) {
            messageLabel.setText("Login successful!");

            // Load Admin Dashboard
            Parent root = FXMLLoader.load(getClass().getResource("admin-dashboard.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Admin Dashboard");
            stage.show();
        } else {
            messageLabel.setText("Invalid username or password!");
        }
    }

    /**
     * Handles the "Back" button click and returns to the home screen.
     */
    @FXML
    public void onBack(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Banking System - Home");
        stage.show();
    }
}
