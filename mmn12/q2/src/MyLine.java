import java.awt.*;

public class MyLine extends MyShape {
    public MyLine(int x1, int y1, int x2, int y2, Color color) {
        super(x1, y1, x2, y2, color);
    }

    public MyLine() {super();}

    @Override
    public void draw(Graphics graphics) {
        graphics.drawLine(this.getX1(), this.getY1(), this.getX2(),this.getY2());
    }

    @Override
    public boolean equals(MyShape other) {
        return Math.pow(this.getX1() + this.getX2(), 2) + Math.pow(this.getY1() + this.getY2(), 2.0) ==
               Math.pow(other.getX1() + other.getX2(), 2) + Math.pow(other.getY1() + other.getY2(), 2.0);
    }
}
