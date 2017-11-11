import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        // holds all the expressions
        ArrayList<Expression> expressions = new ArrayList<>();

        // (rand + rand) + (rand - rand)
        expressions.add(new AdditionExpression(new AdditionExpression(new AtomicExpression(randDouble()), new AtomicExpression(randDouble())),
                        new SubstractionExpression(new AtomicExpression(randDouble()), new AtomicExpression(randDouble()))));

        // (rand - rand) - (rand - rand)
        expressions.add(new SubstractionExpression(new SubstractionExpression(new AtomicExpression(randDouble()), new AtomicExpression(randDouble())),
                new SubstractionExpression(new AtomicExpression(randDouble()), new AtomicExpression(randDouble()))));

        // (1 + 2) + (3 + 4) = 10
        expressions.add(new AdditionExpression(new AdditionExpression(new AtomicExpression(1), new AtomicExpression(2)),
                new AdditionExpression(new AtomicExpression(3), new AtomicExpression(4))));

        // (20 - 5) - (3 + 2) = 10
        expressions.add(new SubstractionExpression(new SubstractionExpression(new AtomicExpression(20), new AtomicExpression(5)),
                new AdditionExpression(new AtomicExpression(3), new AtomicExpression(2))));

        for (Expression e : expressions) {
            // print the expression
            System.out.println(e);

            // print the calculated value of it
            System.out.println(e.calculate());

            // O(n^2)
            for (Expression e_other : expressions) {
                // check if it is equal to anoter one
                if (e != e_other && e.equals(e_other))
                    System.out.println("Equal: " + e + " = " + e_other + " = " + e.calculate());
            }
        }
    }

    /**
     * Returns a pseudo-random number
     *
     * @return random double
     */
    public static double randDouble() {
        //double rangeMin = 0.0;
        //double rangeMax = 200.0;
        Random r = new Random();
        //double randomValue = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
        //return randomValue;
        return r.nextDouble();
    }
}


