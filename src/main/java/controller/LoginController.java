package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Authorization;
import model.APIService;

public class LoginController {
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private Label resultLabel;

    @FXML
    private void handleSubmit() {
        String username =usernameField.getText();
        String password = passwordField.getText();
        APIService apiService = new APIService();
        Authorization authorization = new Authorization(username, password);

        String response = "";

        if (authorization.isPasswordValid() && authorization.isUsernameValid()) {
            try {
                response = apiService.APISignin(username, password);
            } catch (Exception e) {
                e.printStackTrace();
            } 
        }

        resultLabel.setText(response);
    }
}