package game;

import javafx.scene.input.MouseEvent;

public abstract class Button extends Label implements IButton {
    private Listener listener;

    public Button(double x, double y, double w, double h) {
        super(x,y,w,h);
    }
    
    public abstract void handleMouseHover();

    public abstract void handleMousePressed();

    public abstract void handleMouseReleased();

    /**
     * This function is used to track event of mouse, and call the function
     * for each case.
     * 
     * There are 3 events: hovered, pressed and released
     * 
     * @param mouseEvent the mouseEvent of canvas
     */
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

    /**
     * This function is used to set the lister of the button,
     * the listener is manager class of each game.
     * 
     * Look up observer design pattern for more information
     * 
     * @param listener the listener of the button, it will receive a
     * signal, and also a instance of pressed button.
     */
    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public Listener getListener() {
        return this.listener;
    }
}