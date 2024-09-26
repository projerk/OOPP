package controller;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import java.nio.file.Paths;
import javafx.scene.input.MouseEvent;


public class MainController {
    @FXML
    private HBox dashboard;

    @FXML
    private HBox dictionary;

    @FXML
    private HBox game;

    @FXML
    private HBox translate;

    @FXML
    private HBox thesaurus;

    @FXML
    private HBox test;

    @FXML
    private ScrollPane displayArea;

    @FXML
    private VBox displayAreaContainer;

    private static double displayAreaWidth;
    private static double displayAreaHeight;

    private HBox currentSelectedItem;

    @FXML
    private void initialize() {
        dashboard.setOnMouseClicked(this::handleDashboardClick);
        dictionary.setOnMouseClicked(this::handleDictionaryClick);
        game.setOnMouseClicked(this::handleGameClick);
        thesaurus.setOnMouseClicked(this::handleThesaurusClick);
        translate.setOnMouseClicked(this::handleTranslateClick);
        test.setOnMouseClicked(this::handleTestClick);
        game.setOnMouseClicked(this::handleGameClick);

        loadContent("DashboardView.fxml");
        this.currentSelectedItem = this.dashboard;
        dashboard.getStyleClass().add("selected");
        setDisplayValue();
    }

    private void setDisplayValue() {
        displayAreaWidth = displayAreaContainer.getWidth();
        displayAreaHeight = displayAreaContainer.getMaxHeight();
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

    private void handleSidebarClick(HBox clickedItem) {
        if (currentSelectedItem != null) {
            currentSelectedItem.getStyleClass().remove("selected");
        }

        clickedItem.getStyleClass().add("selected");

        currentSelectedItem = clickedItem;
    }

    @FXML
    private void handleDashboardClick(MouseEvent event) {
        handleSidebarClick(dashboard);
        loadContent("DashboardView.fxml");
    }

    @FXML
    private void handleDictionaryClick(MouseEvent event) {
        handleSidebarClick(dictionary);
        loadContent("DictionaryView.fxml");
    }

    // @FXML
    // private void handleGameClick(MouseEvent event) {
    //     handleSidebarClick(game);
    //     loadContent("GameView.fxml");
    // }

    @FXML
    private void handleThesaurusClick(MouseEvent event) {
        handleSidebarClick(thesaurus);
        loadContent("ThesaurusView.fxml");
    }

    @FXML
    private void handleTranslateClick(MouseEvent event) {
        handleSidebarClick(translate);
        loadContent("TranslateView.fxml");
    }

    @FXML
    private void handleGameClick(MouseEvent event) {
        handleSidebarClick(game);
        loadContent("ThesaurusView.fxml");
    }

    @FXML
    private void handleTestClick(MouseEvent event) {
        handleSidebarClick(test);
        loadContent("TestView.fxml");
    }

    public static double getDisplayAreaWidth() {
        return displayAreaWidth;
    }

    public static double getDisplayAreaHeight() {
        return displayAreaHeight;
    }
    // @FXML
    // private void handleTranslateClick(MouseEvent event) {
    //     handleSidebarClick(translate);
    //     loadContent("TranslateView.fxml");
    // }
}