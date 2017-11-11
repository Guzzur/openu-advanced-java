import java.awt.*;

public class MyRectangle extends MyBoundedShape {
    /**
     * C'tor
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @param color
     * @param filled
     */
    public MyRectangle(int x1, int y1, int x2, int y2, Color color, boolean filled) {
        super(x1, y1, x2, y2, color, filled);
    }

    /**
     * Default C'tor
     */
    public MyRectangle() {super();}

    @Override
    /**
     * Draws rectangle
     * @param graphics
     */
    public void draw(Graphics graphics) {
        graphics.setColor(this.getColor());
        if(this.isFilled())
            graphics.fillRect(this.getX1(), this.getY1(), this.getX2(), this.getY2());  // x, y, width, height
        graphics.drawRect(this.getX1(), this.getY1(), this.getX2(), this.getY2());      // x, y, width, height
    }
}
