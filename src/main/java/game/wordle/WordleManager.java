package game.wordle;
import java.util.ArrayList;
import game.Label;
import game.Button;
import game.Listener;
import javafx.scene.input.MouseEvent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.Font;

public class WordleManager implements Listener {
    private double canvasWidth;
    private double canvasHeight;

    private ArrayList<ArrayList<Button>> keyboards;
    private ArrayList<ArrayList<Label>> boxs;

    public void setCanvasHeight(double canvasHeight) {
        this.canvasHeight = canvasHeight;
    }

    public void setCanvasWidth(double canvasWidth) {
        this.canvasWidth = canvasWidth;
    }

    public void init() {
        for (int i = 0; i < Constant.ROWS; i++) {
            for (int j = 0; j < Constant.COLS; j++) {

            }
        }
    }

    public void render(GraphicsContext gc) {

    }

    public void update() {

    }

    public void onButtonPressed(Button button) {

    }

    public void generateCharacterBox() {
        for (int i = 0; i < Constant.ROWS; i++) {
            ArrayList<Label> tempbox = new ArrayList<>();
            for (int j = 0; j < Constant.COLS; j++) {
                tempbox.add(generateBox());
            }
            boxs.add(tempbox);
        }
    }

    private Label generateBox() {
        Label box = new CharacterBox(Constant.NONE, Constant.NONE, Constant.BOX_WIDTH_RATIO * canvasHeight, Constant.BOX_HEIGHT_RATIO * canvasHeight);
        box.setArcWidth(Constant.NONE);
        box.setArcHeight(Constant.NONE);
        box.setText(Constant.NONE_TEXT);
        box.setBackgroundColor(Constant.COLOR_UNKNOWN);
        box.setFont(Constant.FONT_NAME, Constant.FONT_SIZE_RATIO * Constant.BOX_WIDTH_RATIO * canvasHeight);
        box.setStrokeColor(Constant.BORDER_COLOR);
        box.setStrokeWidth(Constant.STROKE_WIDTH);
        box.setActive(false);
        return box;
    }

    // private void drawBackground() {

    // }
}