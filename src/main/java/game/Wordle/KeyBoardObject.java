package game.Wordle;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import game.IEntity;
public class KeyBoardObject implements IEntity{
    enum State{
        red,
        green,
        gray,
        common
    }
    private int x;
    private int y;
    private int w;
    private int h;
    private State state = State.common; 
    private String content;

    @Override
    public void render(GraphicsContext gc){
        if(state == State.common){
            gc.setFill(Color.GRAY);
        
        // Vẽ hình chữ nhật bo góc với arcWidth và arcHeight là 30
            gc.fillRoundRect(50, 50, 200, 100, 30, 30);
        }else if(state == State.red){
            gc.setFill(Color.GRAY);
        
        // Vẽ hình chữ nhật bo góc với arcWidth và arcHeight là 30
            gc.fillRoundRect(50, 50, 200, 100, 30, 30);
        }

    }

    public void setState(State state){
        this.state = state;
    }
    
    public void setContent(String content){
        this.content = content;
    }

    public String getContent(){
        return content;
    }

    public void renderContent(){
        
    }
}
