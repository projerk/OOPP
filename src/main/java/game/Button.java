package game;

import javafx.scene.input.MouseEvent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.Font;

public abstract class Button extends Object implements IButton {

    boolean active;
    private double arcWidth;
    private double arcHeight;
    private String text;
    private Color backgroundColor;
    // private Color strokeColor;
    private Font font;

    public Button(double x, double y, double w, double h) {
        super(x,y,w,h);
    }

    public abstract void handleMouseHover();

    public abstract void handleMousePressed();

    public abstract void handleMouseReleased();

    public void handleMouseEvent(MouseEvent mouseEvent) {
        switch (mouseEvent.getEventType().getName()) {
            case "MOUSE_MOVED":
                handleMouseHover();
                break;
            case "MOUSE_PRESSED":
                handleMousePressed();
                break;
            case "MOUSE_RELEASED":
                handleMouseReleased();
                break;
            default:
                System.out.println("Unhandled mouse event: " + event.getEventType());
        }
    }

    public void render(GraphicsContext gc) {
        gc.setFill(Constants.BUTTON_COLOR);
        gc.fillRoundRect(getX(), getY(), getWidth(), getHeight(), getArcWidth(), getArcHeight());
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setArcWidth(double arcWidth) {
        this.arcWidth = arcWidth;
    }

    public void setArcHeight(double arcHeight) {
        this.arcHeight = arcHeight;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getContent() {
        return this.content;
    }

    public double getArcWidth() {
        return this.arcWidth;
    }

    public double getArcHeight() {
        return this.height;
    }

    public Color getColor() {
        return this.color;
    }

    public void setFont(String fontName, int fontSize) {
        this.font = new Font(fontName, fontSize);
    }

    public Font getFont() {
        return font;
    }
}