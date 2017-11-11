import java.awt.*;

public class MyOval extends MyBoundedShape {
    /**
     * C'tor
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @param color
     * @param filled
     */
    public MyOval(int x1, int y1, int x2, int y2, Color color, boolean filled) {
        super(x1, y1, x2, y2, color, filled);
    }

    /**
     * Default C'tor
     */
    public MyOval() {super();}

    @Override
    /**
     * Draws oval
     * @param graphics
     */
    public void draw(Graphics graphics) {
        graphics.setColor(this.getColor());
        if(this.isFilled())
            graphics.fillOval(this.getX1(), this.getY1(), this.getX2(), this.getY2());  // x, y, width, height
        graphics.drawOval(this.getX1(), this.getY1(), this.getX2(), this.getY2());      // x, y, width, height
    }
}
