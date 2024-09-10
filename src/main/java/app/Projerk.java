package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.nio.file.Paths;
import javafx.stage.Screen;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import javafx.geometry.Rectangle2D;


public class Projerk extends Application {
    private ExecutorService executor;
    private static Projerk instance;
    private Stage primaryStage;
    private Screen screen = Screen.getPrimary();
    private double screenWidth;
    private double screenHeight;
    Rectangle2D bounds = screen.getVisualBounds();
    public Projerk() {}

    public static Projerk getInstance() {
        return instance;
    }

    @Override
    public void start(Stage primaryStage) {
        System.out.println(System.getProperty("java.class.path"));
        instance = this;
        screenWidth = bounds.getWidth();
        screenHeight = bounds.getHeight();
        this.primaryStage = primaryStage;
        loadScene("LoginView.fxml", screenWidth, screenHeight);
        setMaximized(true);
    }

    public void changeScene(String fxmlFile, double width, double height) {
        
        try {
            String fxmlPath = Paths.get("src", "main", "resources", "view", fxmlFile).toAbsolutePath().toString();
            FXMLLoader loader = new FXMLLoader(Paths.get(fxmlPath).toUri().toURL());
            Parent root = loader.load();
            Scene scene = new Scene(root, screenWidth, screenHeight);
            primaryStage.setScene(scene);
            setMaximized(true);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error loading FXML: " + e.getMessage());
        }
    }

    private void loadScene(String fxmlFile, double width, double height) {
        changeScene(fxmlFile, screenWidth, screenHeight);
    }

    public void setMaximized(boolean state) {
        primaryStage.setMaximized(state);
    }

    @Override
    public void stop() {
        shutdownExecutor();
    }

    private void shutdownExecutor() {
        if (executor != null && !executor.isShutdown()) {
            executor.shutdownNow(); 
            try {
                if (!executor.awaitTermination(60, java.util.concurrent.TimeUnit.SECONDS)) {
                    System.out.println("Executor chưa dừng. Thực hiện shutdownNow...");
                    executor.shutdownNow();
                    if (!executor.awaitTermination(60, java.util.concurrent.TimeUnit.SECONDS)) {
                        System.err.println("Executor không thể dừng đúng cách");
                    }
                }
            } catch (InterruptedException ex) {
                System.err.println("Ngắt quãng khi chờ Executor dừng");
                executor.shutdownNow();
                Thread.currentThread().interrupt(); 
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
