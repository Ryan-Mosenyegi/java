package com.mycompany.app;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;
import java.io.IOException;

public class WelcomeController {

    @FXML
    protected void onCustomer() {
        try {
            // Use correct lowercase file name and leading slash
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/app/customer-login.fxml"));
            Scene scene = new Scene(loader.load(), 600, 400);

            Stage stage = (Stage) Window.getWindows().filtered(Window::isShowing).get(0);
            stage.setScene(scene);
            stage.setTitle("Customer Login");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("❌ Could not load customer-login.fxml");
        }
    }

    @FXML
    protected void onClerk() {
        try {
            // Use correct lowercase file name and leading slash
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/app/admin-login.fxml"));
            Scene scene = new Scene(loader.load(), 700, 500);

            Stage stage = (Stage) Window.getWindows().filtered(Window::isShowing).get(0);
            stage.setScene(scene);
            stage.setTitle("Bank Clerk Login");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("❌ Could not load admin-login.fxml");
        }
    }
}
