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
import org.json.JSONArray;
import javafx.scene.layout.FlowPane;
import model.APIService;
import javafx.scene.layout.Priority;

public class ThesaurusController {
    @FXML
    private TextField word;

    @FXML
    private VBox resultContainer;

    @FXML
    private FlowPane synonymPane;

    @FXML
    private FlowPane antonymPane;

    private APIService api = new APIService();

    @FXML
    private void initialize() {

    }

    @FXML
    public void handleSearchButton() {
        String target = word.getText();
        JSONObject json = this.api.APIThesaurus(target);
        String result = json.getString("word");

        JSONArray synonyms = json.getJSONArray("synonyms");
        JSONArray antonyms = json.getJSONArray("antonyms");
        synonymPane.getChildren().clear();
        antonymPane.getChildren().clear();

        for (int i = 0; i < synonyms.length(); i++) {
            String synonym = synonyms.getString(i);
            Label synonymLabel = new Label(synonym);
            synonymLabel.setStyle("-fx-background-color: #148761; -fx-font-family: 'Verdana'; -fx-padding: 5px; -fx-text-fill: #e9e9ea; -fx-font-size: 14px; -fx-background-radius: 12;");
            synonymPane.getChildren().add(synonymLabel);
        }

        for (int i = 0; i < antonyms.length(); i++) {
            String antonym = antonyms.getString(i);
            Label antonymLabel = new Label(antonym);
            antonymLabel.setStyle("-fx-background-color: #148761; -fx-font-family: 'Verdana'; -fx-padding: 5px; -fx-text-fill: #e9e9ea; -fx-font-size: 14px; -fx-background-radius: 12;");
            antonymPane.getChildren().add(antonymLabel);
        }

    }
}