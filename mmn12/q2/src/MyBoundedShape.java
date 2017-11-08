import java.awt.*;

public abstract class MyBoundedShape extends MyShape {
    private boolean filled;
    public MyBoundedShape(int x1, int y1, int x2, int y2, Color color, boolean filled) {
        super(x1, y1, x2, y2, color);
        this.filled = filled;
    }

    public MyBoundedShape() {
        super();
        this.filled = false;
    }

    public boolean isFilled() {
        return this.filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    @Override
    public boolean equals(MyShape other) {
        return (Math.abs(this.getX1() - this.getX2()) == Math.abs(other.getX1() - other.getX2())) &&
               (Math.abs(this.getY1() - this.getY2()) == Math.abs(other.getY1() - other.getY2()));
    }
}
