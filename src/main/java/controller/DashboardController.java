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
import java.nio.file.Paths;
import java.io.File;


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
        exitButton.setStyle("-fx-background-color: transparent; -fx-border-color: black; -fx-background-radius: 30; -fx-border-radius: 30;");
        exitButton.setOnAction(event -> {
            flyOut(temp);
        });
        
        VBox bookDetailContainer = new VBox();
        HBox.setHgrow(bookDetailContainer, Priority.ALWAYS);
        bookDetailContainer.setStyle("-fx-background-color: #ce82ff; -fx-background-radius: 10px;");

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);

        ImageView bookImage = createImage("https://bookworm.vnu.edu.vn/ViewEImage.ashx?id=181210", 300);
        VBox bookInformationContainer = createBookInformation("Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...", "sex", "duon9", "Projerk", 1510);
        
        HBox information = new HBox();
        information.setSpacing(20);
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
        imageView.setFitWidth(size);

        return imageView;
    }

    private VBox createBookInformation(String bookName, String bookType, String bookAuthors, String bookPublishingHouses, int bookPublishingYear) {
        VBox temp = new VBox();

        temp.setStyle("-fx-padding: 15 15 15 15;");
        temp.setSpacing(7);
        HBox.setHgrow(temp, Priority.ALWAYS);

        Label bookNameLabel = new Label("");
        Label bookTypeLabel = new Label("Type: ");
        Label bookAuthorLabel = new Label("Authors: ");
        Label bookPublishingHouseLabel = new Label("Publishing House: ");
        Label bookPublishingYearLabel = new Label("Publishing Year: ");

        Label bookNameLabelValue = new Label(bookName);
        Label bookTypeLabelValue = new Label(bookType);
        Label bookAuthorLabelValue = new Label(bookAuthors);
        Label bookPublishingHouseLabelValue = new Label(bookPublishingHouses);
        Label bookPublishingYearLabelValue = new Label(((Integer)bookPublishingYear).toString());

        HBox bookNameLabelContainer = createBookLabelContainer();
        HBox bookTypeLabelContainer = createBookLabelContainer();
        HBox bookAuthorLabelContainer = createBookLabelContainer();
        HBox bookPublishingHouseLabelContainer = createBookLabelContainer();
        HBox bookPublishingYearLabelContainer = createBookLabelContainer();

        bookNameLabelValue.setStyle("-fx-text-fill: black; -fx-font-family: 'Verdana'; -fx-font-size: 30;");
        bookNameLabelValue.setWrapText(true);
        bookNameLabelValue.setMaxWidth(800);

        bookTypeLabelValue = setBookLabelValueStyle(bookTypeLabelValue);
        bookAuthorLabelValue = setBookLabelValueStyle(bookAuthorLabelValue);
        bookPublishingHouseLabelValue = setBookLabelValueStyle(bookPublishingHouseLabelValue);
        bookPublishingYearLabelValue = setBookLabelValueStyle(bookPublishingYearLabelValue);

        bookTypeLabel = setBookLabelStyle(bookTypeLabel);
        bookAuthorLabel = setBookLabelStyle(bookAuthorLabel);
        bookPublishingHouseLabel = setBookLabelStyle(bookPublishingHouseLabel);
        bookPublishingYearLabel = setBookLabelStyle(bookPublishingYearLabel);

        // bookNameLabelContainer.getChildren().add(bookNameLabel);
        bookNameLabelContainer.getChildren().add(bookNameLabelValue);

        bookTypeLabelContainer.getChildren().add(bookTypeLabel);
        bookTypeLabelContainer.getChildren().add(bookTypeLabelValue);

        bookAuthorLabelContainer.getChildren().add(bookAuthorLabel);
        bookAuthorLabelContainer.getChildren().add(bookAuthorLabelValue);

        bookPublishingHouseLabelContainer.getChildren().add(bookPublishingHouseLabel);
        bookPublishingHouseLabelContainer.getChildren().add(bookPublishingHouseLabelValue);

        bookPublishingYearLabelContainer.getChildren().add(bookPublishingYearLabel);
        bookPublishingYearLabelContainer.getChildren().add(bookPublishingYearLabelValue);

        HBox ratingStarContainer = createRatingStarContainer(5);
        HBox optionContainer = createOptionContainer();
        
        temp.getChildren().add(bookNameLabelContainer);
        temp.getChildren().add(bookTypeLabelContainer); 
        temp.getChildren().add(bookAuthorLabelContainer); 
        temp.getChildren().add(bookPublishingHouseLabelContainer); 
        temp.getChildren().add(bookPublishingYearLabelContainer);
        temp.getChildren().add(ratingStarContainer);
        temp.getChildren().add(optionContainer);

        return temp;
    }

    private Label setBookLabelStyle(Label label) {
        label.setStyle("-fx-text-fill: #bebec2; -fx-font-family: 'Verdana'; -fx-font-size: 20;");
        label.setWrapText(true);
        return label;
    }

    private Label setBookLabelValueStyle(Label label) {
        label.setStyle("-fx-text-fill: black; -fx-font-family: 'Verdana'; -fx-font-size: 20;");
        label.setWrapText(true);
        return label;
    }

    private HBox createBookLabelContainer() {
        HBox temp = new HBox();
        temp.setSpacing(10);
        return temp;
    }

    private HBox createRatingStarContainer(int starCount) {
        HBox temp = new HBox();
        temp.setSpacing(3);
        for (int i = 0; i < starCount; i++) {
            Label star = createRatingFullStar();
            temp.getChildren().add(star);
        }

        for (int i = 0; i < 5 - starCount; i++) {
            Label star = createRatingNoStar();
            temp.getChildren().add(star);
        }
        return temp;
    }

    private Label createRatingNoStar() {
        Label temp = new Label();
        // Load image from resources via classpath;
        String imageStarPath = Paths.get("src", "main", "resources","view","images","no-star.png").toAbsolutePath().toString();
        File file = new File(imageStarPath);
        Image starImage = new Image(file.toURI().toString());
        ImageView starImageView = new ImageView(starImage);
        starImageView.setFitWidth(30);
        starImageView.setFitHeight(30);
        temp.setGraphic(starImageView);
        return temp;
    }
    
    private Label createRatingFullStar() {
        Label temp = new Label();
        // Load image from resources via classpath
        String imageStarPath = Paths.get("src", "main", "resources","view","images","full-star.png").toAbsolutePath().toString();
        File file = new File(imageStarPath);
        Image star = new Image(file.toURI().toString());
        ImageView starView = new ImageView(star);
        starView.setFitHeight(30);
        starView.setFitWidth(30);
        temp.setGraphic(starView);
        return temp;
    }

    private HBox createOptionContainer() {
        HBox temp = new HBox();
        temp.setSpacing(10);
        
        Button borrowButton= new Button("Borrow");
        Button shareButton = new Button("Share");


        temp.getChildren().add(borrowButton);
        temp.getChildren().add(shareButton);

        return temp;
    }
}
