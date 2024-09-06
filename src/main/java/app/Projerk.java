package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.nio.file.Paths;

public class Projerk extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            String fxmlPath = Paths.get("src", "main", "resources", "view", "LoginView.fxml").toAbsolutePath().toString();
            FXMLLoader loader = new FXMLLoader(Paths.get(fxmlPath).toUri().toURL());
            Parent root = loader.load();

            primaryStage.setTitle("Projerk");
            primaryStage.setScene(new Scene(root, 400, 300));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error loading FXML: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}