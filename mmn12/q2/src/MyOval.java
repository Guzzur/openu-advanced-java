import java.awt.*;

public class MyOval extends MyBoundedShape {
    public MyOval(int x1, int y1, int x2, int y2, Color color, boolean filled) {
        super(x1, y1, x2, y2, color, filled);
    }

    public MyOval() {super();}

    @Override
    public void draw(Graphics graphics) {
        System.out.println("[Oval] color: " + getColor() + "\n(" + getX1() + "," +
                getY1() + "," + getX2() + "," + getY2() + ")");
        graphics.setColor(getColor());
        graphics.drawOval(getX1(), getY1(), getX2(), getY2());
    }
}
