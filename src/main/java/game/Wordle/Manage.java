package game.Wordle;
import game.Wordle.KeyBoardObject;
import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;

import org.bouncycastle.util.Characters;

import com.github.dockerjava.api.model.Event;

public class Manage {
    private ArrayList<Characters> line1;
    private ArrayList<Characters> line2;
    private ArrayList<Characters> line3;
    private ArrayList<Characters> line4;
    private ArrayList<Characters> line5;
    private ArrayList<Characters> line6;

    public void handleEvent(KeyEvent e){
        String character = e.getCharacter();
        // thêm kí tự vào list chưa đủ 5
        if(line1.size() < 5){
            line1.add(character);
        }else //...

    }
// render các line
    public void render(GraphicsContext gc){
        
    }
}
