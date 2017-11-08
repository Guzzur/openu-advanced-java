import java.awt.*;

public class MyRectangle extends MyBoundedShape {
    public MyRectangle(int x1, int y1, int x2, int y2, Color color, boolean filled) {
        super(x1, y1, x2, y2, color, filled);
    }

    public MyRectangle() {super();}

    public void draw(Graphics graphics) {
        graphics.setColor(this.getColor());
        graphics.drawRect(this.getX1(), this.getY1(),
                          Math.abs(this.getX1() - this.getX2()),
                          Math.abs(this.getY1() - this.getY2()));
    }
}
