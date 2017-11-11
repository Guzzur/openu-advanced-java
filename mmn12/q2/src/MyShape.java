import javax.swing.*;
import java.awt.*;

public abstract class MyShape implements Cloneable {
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private Color color;

    /**
     * C'tor
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @param color
     */
    public MyShape(int x1, int y1, int x2, int y2, Color color) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
    }

    /**
     * Default C'tor
     */
    public MyShape() {
        this.x1 = 0;
        this.y1 = 0;
        this.x2 = 0;
        this.y2 = 0;
        this.color = Color.WHITE;
    }

    /**
     * Abstract, draws the shape
     * @param graphics
     */
    public abstract void draw(Graphics graphics);

    /**
     * Overloads Object's clone method
     * @return cloned shape
     */
    public Object clone() {
        try {
            return super.clone();
        }
        catch (CloneNotSupportedException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    /**
     * @return x1
     */
    public int getX1() {
        return x1;
    }

    /**
     * @return y1
     */
    public int getY1() {
        return y1;
    }

    /**
     * @return x2
     */
    public int getX2() {
        return x2;
    }

    /**
     * @return y2
     */
    public int getY2() {
        return y2;
    }

    /**
     * @return color
     */
    public Color getColor() {
        return color;
    }

    /**
     * @param x1
     */
    public void setX1(int x1) {
        this.x1 = x1;
    }

    /**
     * @param y1
     */
    public void setY1(int y1) {
        this.y1 = y1;
    }

    /**
     * @param x2
     */
    public void setX2(int x2) {
        this.x2 = x2;
    }

    /**
     * @param y2
     */
    public void setY2(int y2) {
        this.y2 = y2;
    }

    /**
     * @param color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Abstract, check if the shapes are equal in size
     * @param other shape to compare with
     * @return true if are equal
     */
    public abstract boolean equals(MyShape other);
}
