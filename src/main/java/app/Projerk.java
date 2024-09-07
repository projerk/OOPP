package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.nio.file.Paths;

public class Projerk extends Application {
    private static Projerk instance;
    private Stage primaryStage;

    public Projerk() {}

    public static Projerk getInstance() {
        return instance;
    }

    @Override
    public void start(Stage primaryStage) {
        instance = this;
        this.primaryStage = primaryStage;
        loadScene("LoginView.fxml", 800, 600);
        setMaximized(true);
    }

    public void changeScene(String fxmlFile, int width, int height) {
        try {
            String fxmlPath = Paths.get("src", "main", "resources", "view", fxmlFile).toAbsolutePath().toString();
            FXMLLoader loader = new FXMLLoader(Paths.get(fxmlPath).toUri().toURL());
            Parent root = loader.load();
            Scene scene = new Scene(root, width, height);
            primaryStage.setScene(scene);
            setMaximized(true);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error loading FXML: " + e.getMessage());
        }
    }

    private void loadScene(String fxmlFile, int width, int height) {
        changeScene(fxmlFile, width, height);
    }

    public void setMaximized(boolean state) {
        primaryStage.setMaximized(state);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
