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
import model.Person;

public class MainController {
    @FXML
    private HBox dashboard;
    
    @FXML
    private HBox dictionary;

    @FXML
    private HBox game;

    @FXML
    private ScrollPane displayArea;

    @FXML
    private void initialize() {
        dashboard.setOnMouseClicked(this::handleDashboardClick);
        dictionary.setOnMouseClicked(this::handleDictionaryClick);
        game.setOnMouseClicked(this::handleGameClick);
        loadContent("DashboardView.fxml");
    }

    private void loadContent(String fxmlFile) {
        try {
            String fxmlPath = Paths.get("src", "main", "resources", "view", fxmlFile).toAbsolutePath().toString();
            FXMLLoader loader = new FXMLLoader(Paths.get(fxmlPath).toUri().toURL());            
            VBox newContent = loader.load();
            displayArea.setContent(newContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleDashboardClick(MouseEvent event) {
        loadContent("DashboardView.fxml");
    }

    @FXML
    private void handleDictionaryClick(MouseEvent event) {
        loadContent("DictionaryView.fxml");
    }

    @FXML
    private void handleGameClick(MouseEvent event) {
        loadContent("DashboardView.fxml");
    }
}