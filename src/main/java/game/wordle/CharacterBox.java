package game.wordle;

import game.Label;

public class CharacterBox extends Label {
    private Constant.State state = Constant.State.UNKNOWN;

    public CharacterBox(double x, double y, double w, double h) {
        super(x,y,w,h);
    }

    public void setState(Constant.State state) {
        this.state = state;
    }
}