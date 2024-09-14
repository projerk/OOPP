package controller;

import app.Projerk;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import game.wordle.KeyboardButton;
import game.wordle.Constant;

public class GameController {
    @FXML
    private VBox canvasContainer;
    private Canvas gameCanvas;

    private Projerk app;
    private Scene scene;
    private double canvasWidth;
    private double canvasHeight;

    private KeyboardButton test;

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
        test.render(gc);
    }

    private void init() {
        app = Projerk.getInstance();
        scene = app.getScene();
        canvasWidth = scene.getWidth() - 20 - 20 - 180 - 20 - 10;
        canvasHeight = scene.getHeight() - 40 - 10;
        gameCanvas = new Canvas(canvasWidth, canvasHeight);
        canvasContainer.getChildren().add(gameCanvas);


        test = new KeyboardButton(100, 100, 43.59, 58);
        test.setArcWidth(4);
        test.setArcHeight(4);
        test.setText("A");
        test.setTextColor(Constant.CHARACTER_COLOR);
        test.setBackgroundColor(Constant.COLOR_CORRECT);
        test.setFont("Arial", 58 * 0.34482);
        test.setStrokeColor(Constant.BORDER_COLOR);
        test.setStrokeWidth(0);
        test.setActive(true);
        test.setHasStroke(false);

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
