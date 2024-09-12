package game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

public abstract class Object implements IObject {

    private double x;
    private double y;
    private double w;
    private double h;

    public Object() {

    }

    public Object(double x, double y, double w, double h) {
        this.w = w;
        this.h = h;
        this.x = x;
        this.y = y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public void setWidth(double w) {
        this.w = w;
    } 

    public void setHeight(double h) {
        this.h = h;
    }

    public double getWidth() {
        return this.w;
    }

    public double getHeight() {
        return this.h;
    }

    public abstract void render(GraphicsContext gc);

}