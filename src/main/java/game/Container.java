package game;
import javafx.scene.canvas.GraphicsContext;
import java.util.ArrayList;
import java.util.List;

public class Container extends Object implements IContainer {
    List<Object> objects = new ArrayList<>();
    private double padding; // the distance between content and border
    private double spacing; // space between element
    private int columns; // number of columns
    private int rows; // number of rows

    /**
     * default constructor with no parameter
     */
    public Container() {
        super();
        this.padding = 0;
        this.spacing = 0;
        this.columns = 0;
        this.rows = 0;
    }


    /**
     * Container with parameter
     * 
     * @param x x position of container
     * @param y y position of container
     * 
     * width and height will be initialized by arrange() so we
     * set it to 0
     * 
     * other attribute also initialize to 0, so you need to set it a value
     * before use
     */
    public Container(double x, double y) {
        super(x,y,0,0);
        this.padding = 0;
        this.spacing = 0;
        this.columns = 0;
        this.rows = 0;
    }

    /**
     * arrange element in container.
     */
    public void arrange() {
        double startX = getX() + getPadding();
        double startY = getY() + getPadding();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                Object object = objects.get(r * columns + c);
                object.setX(startX + c * (object.getWidth() + spacing));
                object.setY(startY + r * (object.getHeight() + spacing));
            }
        }

        Object lastObject = objects.get(columns * rows - 1);

        setWidth(lastObject.getX() + lastObject.getWidth() + padding);
        setHeight(lastObject.getY() + lastObject.getHeight() + padding);
    }

    public double getPadding() {
        return padding;
    }

    public void setPadding(double padding) {
        this.padding = padding;
    }

    public double getSpacing() {
        return spacing;
    }

    public void setSpacing(double spacing) {
        this.spacing = spacing;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public double getRows() {
        return rows;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public double getColumns() {
        return columns;
    }

    /**
     * add object to container.
     */
    public void add(Object object) {
        objects.add(object);
    }

    /**
     * get object from the container.
     * 
     * @param id index of element.
     * @return the element with id.
     */
    public Object get(int id) {
        return objects.get(id);
    }
    
    /**
     * render object wrap by container
     * 
     * @param gc GraphicsContext
     */
    public void render(GraphicsContext gc) {
        for (Object object : objects) {
            object.render(gc);
        }
    }
}
