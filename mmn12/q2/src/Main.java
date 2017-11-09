import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Main extends JPanel {
    public static void main(String[] args) {
        JFrame frame = new JFrame("MMN12 Q2 v1.0.0");
        MyPanel panel = new MyPanel();
        Graphics g = panel.getGraphics();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setVisible(true);

        MyOval oval1 = new MyOval(randInt(), randInt(), randInt(), randInt(), Color.CYAN, true);
        MyOval oval2 = new MyOval(randInt(), randInt(), randInt(), randInt(), Color.BLUE, false);
        MyLine line1 = new MyLine(randInt(), randInt(), randInt(), randInt(), Color.BLACK);
        MyLine line2 = new MyLine(randInt(), randInt(), randInt(), randInt(), Color.GREEN);
        MyLine line3 = (MyLine)line1.clone();
        MyRectangle rectangle1 = new MyRectangle(randInt(), randInt(), randInt(), randInt(), Color.YELLOW, true);
        MyRectangle rectangle2 = new MyRectangle(randInt(), randInt(), randInt(), randInt(), Color.RED, false);

        //oval1.draw(g);

        panel.add(oval1);
        panel.add(oval2);
        panel.add(line1);
        panel.add(line2);
        panel.add(line3);
        panel.add(rectangle1);
        panel.add(rectangle2);

        frame.add(panel);
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
