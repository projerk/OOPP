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
    private double fontSize;
    private String fontName;
    private boolean hasStroke;


    /**
     * Constructor of label.
     * use this element to display text, colorful graphic.
     * at this moment, we do not support display iamge.
     * 
     * @param x x offset of label
     * @param y y offset of label
     * @param w width of label(px)
     * @param h height of label(px)
     * 
     * remember that the (0,0) coordinator is on the top left of the screen
     * not the center of the screen.
     */
    public Label(double x, double y, double w, double h) {
        super(x,y,w,h);
    }

    /**
     * This function is used to render the label.
     * 
     * In this function, we have to use the gc.scale() in order to enhance
     * the quality of graphic, so that width, height and text size will be
     * divided by 2.
     * 
     * 
     */
    @Override
    public void render(GraphicsContext gc) {
        gc.save();
        gc.scale(2,2);

        gc.setFill(backgroundColor);
        gc.fillRoundRect(getX(), getY(), getWidth() / 2, getHeight() / 2, getArcWidth(), getArcHeight());

        if (hasStroke) {
            gc.setStroke(strokeColor);
            gc.setLineWidth(strokeWidth);
            gc.strokeRoundRect(getX(), getY(), getWidth() / 2, getHeight() / 2, getArcWidth(), getArcHeight());
        }

        gc.setFill(textColor);
        gc.setFont(font);

        Text tempText = new Text(text);
        tempText.setFont(font);

        double textWidth = tempText.getLayoutBounds().getWidth();
        double textHeight = tempText.getLayoutBounds().getHeight();

        double textX = getX() + (getWidth() / 2 - textWidth) / 2;
        double textY = getY() + (getHeight() / 2 + textHeight) / 2; 

        gc.fillText(text, textX, textY);
        gc.restore();
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

    public void setFont(String fontName, double fontSize) {
        this.font = new Font(fontName, fontSize / 2);
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

    public void setFontName(String fontName) {
        this.fontName = fontName;
    }

    public String getFontName() {
        return fontName;
    }

    public void setFontSize(double fontSize) {
        this.fontSize = fontSize;
    }

    public double getFontSize() {
        return fontSize;
    }

    public boolean isHasStroke() {
        return hasStroke;
    }

    public void setHasStroke(boolean hasStroke) {
        this.hasStroke = hasStroke;
    }
}