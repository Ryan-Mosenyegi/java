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

public class AdminLoginController {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label messageLabel;

    @FXML
    protected void onLogin(ActionEvent event) throws Exception {
        String user = usernameField.getText().trim();
        String pass = passwordField.getText();

        // staff credentials (fixed)
        if (user.equals("admin") && pass.equals("admin123")) {
            Parent root = FXMLLoader.load(getClass().getResource("admin-dashboard.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 800, 500));
            stage.show();
        } else {
            messageLabel.setText("Invalid staff credentials.");
        }
    }
}
