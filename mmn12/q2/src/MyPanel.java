import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MyPanel extends JPanel {
    private ArrayList<MyShape> shapes;
    public MyPanel() {
        this.shapes = new ArrayList<MyShape>();
    }

    public void add(MyShape shape) {
        this.shapes.add(shape);
        repaint();
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        for (MyShape shape : shapes) {
            graphics.setColor(shape.getColor());
            if (shape instanceof MyOval)
                graphics.drawOval(shape.getX1(),shape.getX1(),shape.getX2(), shape.getY1());
            else if (shape instanceof MyRectangle)
                graphics.drawRect(shape.getX1(),shape.getX1(),shape.getX2(), shape.getY1());
            else graphics.drawLine(shape.getX1(),shape.getX1(),shape.getX2(), shape.getY1());
        }


        graphics.dispose();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(400,400);
    }
}
