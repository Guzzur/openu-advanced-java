import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // defines frame (window)
        JFrame frame = new JFrame("MMN12 Q2 v1.0.2");

        // defines panel to draw on
        MyPanel panel = new MyPanel();

        // default operation for closing window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // defines window size 400*400
        frame.setSize(400, 400);

        // holds shapes
        ArrayList<MyShape> shapes = new ArrayList<MyShape>();

        // add 2 shapes of each type
        shapes.add(new MyOval(randInt(), randInt(), randInt(), randInt(), Color.RED, true));
        shapes.add(new MyOval(randInt(), randInt(), randInt(), randInt(), Color.BLACK, false));
        shapes.add(new MyLine(randInt(), randInt(), randInt(), randInt(), Color.BLUE));
        shapes.add(new MyLine(randInt(), randInt(), randInt(), randInt(), Color.GREEN));
        shapes.add(new MyRectangle(randInt(), randInt(), randInt(), randInt(), Color.YELLOW, true));
        shapes.add(new MyRectangle(randInt(), randInt(), randInt(), randInt(), Color.CYAN, false));

        // add all shapes and its moved py 10 px clones
        for (MyShape shape: shapes) {
            panel.addShape(shape);
            panel.addClone(shape);
        }

        // add drawing panel to the window
        frame.add(panel);
        // make the window be visible
        frame.setVisible(true);
    }

    /**
     * Returns a pseudo-random number
     *
     * @return random int
     */
    public static int randInt() {
        int rangeMin = 0;
        int rangeMax = 200;
        Random r = new Random();
        int randomValue = Math.abs(r.nextInt() % (rangeMax - rangeMin));
        return randomValue;
    }
}
