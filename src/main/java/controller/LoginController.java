package controller;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Authorization;
import model.APIService;
import app.Projerk;

public class LoginController {

    private Projerk app = Projerk.getInstance();
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private Label resultLabel;

    private int status;

    @FXML
    private void handleSubmit() {
        String username =usernameField.getText();
        String password = passwordField.getText();
        APIService apiService = new APIService();
        Authorization authorization = new Authorization(username, password);

        String response = "";

        if (authorization.isPasswordValid() && authorization.isUsernameValid()) {
            try {
                status = apiService.APISignin(username, password);

                if (status == 200) {
                    
                }
                else if (status == 401) {
                    response = "Signin Failed";
                }
            } catch (Exception e) {
                e.printStackTrace();
            } 
        }
        else {
            response = "Please ensure your password is longer than 6 characters";
        }
        if (status == 200) {
            app.changeScene("MainView.fxml", 10000, 10000);
            app.setMaximized(true);
        }
    }
}