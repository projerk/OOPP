package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Authorization;

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

        Authorization author = new Authorization(username, password);
        resultLabel.setText("Welcome back, " + author.getUsername() + "!");
    }
}