import java.awt.*;

public class MyLine extends MyShape {
    /**
     * C'tor
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @param color
     */
    public MyLine(int x1, int y1, int x2, int y2, Color color) {
        super(x1, y1, x2, y2, color);
    }

    /**
     * Default C'tor
     */
    public MyLine() {super();}

    @Override
    /**
     * Draws line
     * @param graphics
     */
    public void draw(Graphics graphics) {
        graphics.setColor(this.getColor());
        graphics.drawLine(this.getX1(), this.getY1(), this.getX2(),this.getY2()); // x1, y1, x2, y2
    }

    @Override
    /**
     * Checks if size is equal to other's
     * @param other shape
     */
    public boolean equals(MyShape other) {
        return Math.pow(this.getX1() + this.getX2(), 2) + Math.pow(this.getY1() + this.getY2(), 2.0) ==
               Math.pow(other.getX1() + other.getX2(), 2) + Math.pow(other.getY1() + other.getY2(), 2.0);
    }
}
