package game;

import javafx.scene.input.MouseEvent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.Font;

public abstract class Button extends Label implements IButton {
    private Listener listener;

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
                System.out.println("Unhandled mouse event: " + mouseEvent.getEventType());
        }
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public Listener getListener() {
        return this.listener;
    }
}