package com.mycompany.app;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class WelcomeController {

    @FXML
    protected void onCustomer() {
        loadScreen("CustomerLogin.fxml", "Customer Login");
    }

    @FXML
    protected void onRegister() {
        loadScreen("CustomerRegistration.fxml", "Register New Customer");
    }

    @FXML
    protected void onAdmin() {
        loadScreen("AdminLogin.fxml", "Admin Login");
    }

    private void loadScreen(String fxml, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Scene scene = new Scene(loader.load(), 600, 400);

            Stage stage = (Stage) Stage.getWindows()
                    .filtered(window -> window.isShowing())
                    .get(0);

            stage.setScene(scene);
            stage.setTitle(title);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error loading: " + fxml);
        }
    }
}
