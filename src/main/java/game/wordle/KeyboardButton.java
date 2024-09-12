package game.wordle;
import game.Listener;
import game.Button;

public class KeyboardButton extends Button {

    Constant.State state = Constant.State.UNKNOWN;

    public KeyboardButton(double x, double y, double w, double h) {
        super(x,y,w,h);
    }

    @Override
    public void handleMouseHover() {

    }

    @Override
    public void handleMousePressed() {
        Listener mListener = getListener();
        if (mListener != null) {
            mListener.onButtonPressed(this);
        }
    }

    @Override
    public void handleMouseReleased() {

    }

    public void setState(Constant.State state) {
        this.state = state;
    }
}