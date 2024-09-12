package game;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;


public interface IButton {
    void handleMouseEvent(MouseEvent mouseEvent);
    void handleMouseHover();
    void handleMousePressed();
    void handleMouseReleased();
}