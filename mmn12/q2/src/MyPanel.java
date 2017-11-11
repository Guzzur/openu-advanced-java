import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Extends JPanel. Draws shapes.
 */
public class MyPanel extends JPanel {
    // holds shapes
    protected ArrayList<MyShape> shapes;

    //holds shapes clones
    protected ArrayList<MyShape> clones;

    /**
     * Default C'tor
     */
    public MyPanel() {
        super();
        this.shapes = new ArrayList<MyShape>();
        this.clones = new ArrayList<MyShape>();
    }

    /**
     * Adds shape to the array
     * @param shape
     */
    public void addShape(MyShape shape) {
        this.shapes.add(shape);
        repaint();
    }

    /**
     * Creates shape's clone
     * Moves clone by 10 pixels in each axis
     * Adds clone to the array
     * @param shape
     */
    public void addClone(MyShape shape) {
        MyShape clone = (MyShape)shape.clone();
        clone.setX1(clone.getX1()+10);
        clone.setY1(clone.getY1()+10);

        // if it is a line - move x2, y2 too
        if (clone instanceof MyLine) {
            clone.setX2(clone.getX2()+10);
            clone.setY2(clone.getY2()+10);
        }

        // invert filling status
        if (clone instanceof MyBoundedShape)
            ((MyBoundedShape) clone).setFilled(!((MyBoundedShape) clone).isFilled());

        this.clones.add(clone);

        // draw the updated picture
        repaint();
    }

    @Override
    /**
     * Called automatically
     * @param graphics
     */
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        // draws shapes
        for (MyShape shape : shapes)
            shape.draw(graphics);

        //draws clones
        for (MyShape clone : clones)
            clone.draw(graphics);
    }
}
