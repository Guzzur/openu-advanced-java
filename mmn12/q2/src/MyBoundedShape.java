import java.awt.*;

public abstract class MyBoundedShape extends MyShape {
    private boolean filled;

    /**
     * C'tor
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @param color
     * @param filled
     */
    public MyBoundedShape(int x1, int y1, int x2, int y2, Color color, boolean filled) {
        super(x1, y1, x2, y2, color); // x1, y1, width, height, color
        this.filled = filled;
    }

    /**
     * Default C'tor
     */
    public MyBoundedShape() {
        super();
        this.filled = false;
    }

    /**
     * @return true if is filled
     */
    public boolean isFilled() {
        return this.filled;
    }

    /**
     * @param filled
     */
    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    @Override
    /**
     * Checks if size is equal to other's
     * @param other shape
     */
    public boolean equals(MyShape other) {
        return this.getX2() == other.getX2() && this.getY2() == other.getY2(); // width & height are same
    }
}
