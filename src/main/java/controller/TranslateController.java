package controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.concurrent.Task;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import model.Person;
import app.AppTask;

public class TranslateController {
    private ExecutorService executor;
    private Future<String> futureResult;

    @FXML
    private Label outputText;

    @FXML
    private TextArea inputText;

    @FXML
    private void initialize() {
        executor = Executors.newFixedThreadPool(1);
    }

    @FXML
    private void handleSearchButton() {
        String target = inputText.getText();
        AppTask task = new AppTask("Task 1", target);
        outputText.setText("Please wait");

        futureResult = executor.submit(task);

        new Thread(() -> {
            try {
                String result = futureResult.get(); 
                Platform.runLater(() -> outputText.setText(result)); 
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}