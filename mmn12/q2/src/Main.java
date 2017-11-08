import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        MyOval oval1 = new MyOval(randInt(), randInt(), randInt(), randInt(), Color.CYAN, true);
        MyOval oval2 = new MyOval(randInt(), randInt(), randInt(), randInt(), Color.BLUE, false);
        MyLine line1 = new MyLine(randInt(), randInt(), randInt(), randInt(), Color.BLACK);
        MyLine line2 = new MyLine(randInt(), randInt(), randInt(), randInt(), Color.BLACK);
        //MyLine line2 = (MyLine)line1.clone();
        MyRectangle rectangle1 = new MyRectangle(randInt(), randInt(), randInt(), randInt(), Color.YELLOW, true);
        MyRectangle rectangle2 = new MyRectangle(randInt(), randInt(), randInt(), randInt(), Color.RED, false);

        JFrame window = new JFrame();
        MyPanel drawing = new MyPanel();

        window.add(drawing);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(400, 400);
        window.setVisible(true);
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
        int randomValue = rangeMin + (rangeMax - rangeMin) * r.nextInt();
        return randomValue;
    }
}
