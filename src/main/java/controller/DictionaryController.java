package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.nio.file.Paths;
import javafx.scene.input.MouseEvent;
import org.json.JSONObject;
import model.Person;
import model.DBHelper;
import model.Word;
import model.Type;
import model.Meaning;
import model.Example;

public class DictionaryController {
    @FXML
    private TextField word;

    @FXML
    private void initialize() {

    }

    @FXML
    public void handleSearchButton() {
        String target = word.getText();

        JSONObject json = DBHelper.searchWord(target);
        if (json.has("word")) {
            System.out.println(json.getString("pronunciation"));
        }
        else {
            System.out.println("Error");
        }
    }
}