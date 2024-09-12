package controller;

import app.Projerk;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.Scene;

public class GameController {
    @FXML
    private Canvas gameCanvas;

    private Projerk app;
    private Scene scene;

    private double playerX = 100;
    private double playerY = 100;
    private double playerSpeed = 5;

    private boolean up, down, left, right;

    public void initialize() {
        init();
        startGameLoop();

        gameCanvas.setFocusTraversable(true);
        gameCanvas.requestFocus();
        handlePlayerInput();
        
    }

    private void startGameLoop() {
        GraphicsContext gc = gameCanvas.getGraphicsContext2D();

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
                render(gc);
            }
        }.start();
    }

    private void update() {
        if (up) playerY -= playerSpeed;
        if (down) playerY += playerSpeed;
        if (left) playerX -= playerSpeed;
        if (right) playerX += playerSpeed;
    }

    private void render(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, gameCanvas.getWidth(), gameCanvas.getHeight());

        gc.setFill(Color.BLUE);
        gc.fillOval(playerX, playerY, 30, 30);
    }

    private void init() {
        app = Projerk.getInstance();
        scene = app.getScene();
    }

    private void handlePlayerInput() {
        this.scene.setOnKeyPressed((KeyEvent event) -> {
            switch (event.getCode()) {
                case W -> up = true;
                case S -> down = true;
                case A -> left = true;
                case D -> right = true;
            }
        });

        this.scene.setOnKeyReleased((KeyEvent event) -> {
            switch (event.getCode()) {
                case W -> up = false;
                case S -> down = false;
                case A -> left = false;
                case D -> right = false;
            }
        });
    }
}
