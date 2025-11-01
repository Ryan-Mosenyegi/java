package com.mycompany.app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HomeController {
    private Stage stage;

    @FXML
    protected void onCustomerRegister(ActionEvent event) throws Exception {
        switchScene(event, "customer-register.fxml");
    }

    @FXML
    protected void onCustomerLogin(ActionEvent event) throws Exception {
        switchScene(event, "customer-login.fxml");
    }

    @FXML
    protected void onAdminLogin(ActionEvent event) throws Exception {
        switchScene(event, "admin-login.fxml");
    }

    private void switchScene(ActionEvent event, String fxml) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }
}
