package game.wordle;
import javafx.scene.paint.Color;

public class Constant {
    public enum State {
        CORRECT,
        PRESENT,
        ABSENT,
        UNKNOWN
    }

    public static final Color COLOR_CORRECT = Color.web("#538d4e");
    public static final Color COLOR_PRESENT = Color.web("#b59f3b");
    public static final Color COLOR_ABSENT = Color.web("#3a3a3c");
    public static final Color COLOR_UNKNOWN = Color.web("#818384");
    public static final Color CHARACTER_COLOR = Color.web("#f8f8f8");
    public static final Color BACKGROUND_COLOR = Color.web("#121213");
    public static final Color BORDER_COLOR = Color.web("#565758");
    public static final int ROWS = 6;
    public static final int COLS = 5;
    public static final double BOX_WIDTH_RATIO = 0.1;
    public static final double BOX_HEIGHT_RATIO = 0.1;
    public static final double FONT_SIZE_RATIO = 0.6;
    public static final int NONE = 0;
    public static final String NONE_TEXT = "";
    public static final String FONT_NAME = "";
    public static final double STROKE_WIDTH = 1;
    public static final double MARGIN_RATIO = 0.12;

    public static void doNothing() {

    }

}