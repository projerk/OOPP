package controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import app.Projerk;
import io.opentelemetry.sdk.trace.samplers.SamplingResult;

public class DashboardController {

    private Projerk app = Projerk.getInstance();

    @FXML
    private StackPane mainPane;

    @FXML
    public void initialize() {
    }

    @FXML
    private void testButton() {
        VBox popup = createBookDetailPage(0);
        // popup.setStyle("-fx-background-color: #fbfbfc; -fx-padding: 10; -fx-background-radius: 10px; -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.1), 10, 0, 0, 1); ");
        // Button exit = new Button("Exit");
        // popup.getChildren().add(exit);
        popup.setTranslateY(mainPane.getHeight());;
        // exit.setOnAction(event -> {
        //     flyOut(popup);
        // });

        mainPane.getChildren().add(popup);
        flyIn(popup);
    }

    private void flyOut(Node node) {
        Scene scene = app.getScene();
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(node);
        transition.setDuration(Duration.seconds(0.2));

        transition.setToY(scene.getHeight());

        transition.setOnFinished(event -> {
            mainPane.getChildren().remove(node);
        });

        transition.play();

    }

    private void flyIn(Node node) {
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(node);
        transition.setDuration(Duration.seconds(0.2));
        transition.setToY(0);
        transition.play();
    }

    private VBox createBookDetailPage(int id) {
        id++;
        VBox temp = new VBox();
        temp.setStyle("-fx-background-color: #fbfbfc; -fx-padding: 10; -fx-background-radius: 10px; -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.1), 10, 0, 0, 1); ");
        Button exitButton = new Button("x");

        exitButton.setOnAction(event -> {
            flyOut(temp);
        });
        
        VBox bookDetailContainer = new VBox();
        HBox.setHgrow(bookDetailContainer, Priority.ALWAYS);
        bookDetailContainer.setStyle("-fx-background-color: #ce82ff; -fx-background-radius: 10px;");

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);

        // ImageView imageView = new ImageView();
        // Image image = new Image("https://bookworm.vnu.edu.vn/ViewEImage.ashx?id=181210");
        // imageView.setImage(image);
        // imageView.setPreserveRatio(true);
        // imageView.setFitWidth(200);
        ImageView bookImage = createImage("https://bookworm.vnu.edu.vn/ViewEImage.ashx?id=181210", 200);
        VBox bookInformationContainer = createBookInformation("Sapiens", "sex", "duon9", "Projerk", 1510);
        HBox information = new HBox();
        bookDetailContainer.setStyle("-fx-padding: 10 10 10 10");
        information.getChildren().add(bookImage);
        information.getChildren().add(bookInformationContainer);
        bookDetailContainer.getChildren().add(information);
        scrollPane.setContent(bookDetailContainer);


        temp.getChildren().add(exitButton);
        temp.getChildren().add(scrollPane);
        return temp;
    }

    private ImageView createImage(String url, double size) {
        ImageView imageView = new ImageView();
        Image image = new Image(url);
        imageView.setImage(image);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(200);

        return imageView;
    }

    private VBox createBookInformation(String bookName, String bookType, String bookAuthors, String bookPublishingHouses, int bookPublishingYear) {
        VBox temp = new VBox();
        HBox.setHgrow(temp, Priority.ALWAYS);
        Label bookNameLabel = new Label("Name: ");
        Label bookTypeLabel = new Label("Type: ");
        Label bookAuthorLabel = new Label("Authors: ");
        Label bookPublishingHouseLabel = new Label("Publishing House: ");
        Label bookPublishingYearLabel = new Label("Publishing Year: ");

        temp.getChildren().add(bookNameLabel);
        temp.getChildren().add(bookTypeLabel); 
        temp.getChildren().add(bookAuthorLabel); 
        temp.getChildren().add(bookPublishingHouseLabel); 
        temp.getChildren().add(bookPublishingYearLabel);    

        return temp;
    }
}
