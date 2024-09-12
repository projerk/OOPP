package game;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;


public interface IButton {
    public void handleMouseEvent(MouseEvent mouseEvent);
    public void handleMouseHover();
    public void handleMousePressed();
    public void handleMouseReleased();
}