package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Person;

public class SidebarController {
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private Label resultLabel;

    @FXML
    private void handleSubmit() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();

        Person person = new Person(firstName, lastName);
        resultLabel.setText("Hello, " + person.getFirstName() + " " + person.getLastName() + "!");
    }

    //
    // return 
    // 
    private void showPennis() {

    }
}