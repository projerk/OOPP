package game;

import javafx.scene.input.MouseEvent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.Font;

public abstract class Label extends Object {

    boolean active;
    private double arcWidth;
    private double arcHeight;
    private String text;
    private Color backgroundColor;
    private Color strokeColor;
    private Color textColor;
    private double strokeWidth;
    private Font font;

    public Label(double x, double y, double w, double h) {
        super(x,y,w,h);
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.setFill(backgroundColor);
        gc.fillRoundRect(getX(), getY(), getWidth(), getHeight(), getArcWidth(), getArcHeight());

        gc.setStroke(strokeColor);
        gc.setLineWidth(strokeWidth);
        gc.strokeRoundRect(getX(), getY(), getWidth(), getHeight(), getArcWidth(), getArcHeight());

        gc.setFill(textColor);
        gc.setFont(font);

        Text tempText = new Text(text);
        tempText.setFont(font);

        double textWidth = tempText.getLayoutBounds().getWidth();
        double textHeight = tempText.getLayoutBounds().getHeight();

        double textX = getX() + (getWidth() - textWidth) / 2;
        double textY = getY() + (getHeight() + textHeight) / 2 - 5; 

        gc.fillText(text, textX, textY);
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

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String getText() {
        return this.text;
    }

    public double getArcWidth() {
        return this.arcWidth;
    }

    public double getArcHeight() {
        return this.arcHeight;
    }

    public Color getBackgroundColor() {
        return this.backgroundColor;
    }

    public void setFont(String fontName, int fontSize) {
        this.font = new Font(fontName, fontSize);
    }

    public Font getFont() {
        return font;
    }

    public void setStrokeColor(Color strokeColor) {
        this.strokeColor = strokeColor;
    }

    public Color getStrokeColor() {
        return this.strokeColor;
    }

    public void setStrokeWidth(double strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }

    public Color getTextColor() {
        return this.textColor;
    }
}