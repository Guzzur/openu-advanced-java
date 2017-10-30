import java.util.Scanner;

/**
 * Advanced Java, Maman 11
 * Rational numbers tester class
 *
 * @author  Felix Razykov
 * @version 1.0
 * @since   2017-10-28
 */
public class Tester {
    public static void main(String[] args) {
        // stores validation answer for denominators
        boolean validInput;
        // store p/q of the first and the second numbers
        int firstNumerator, firstDenominator = 0, secondNumerator, secondDenominator = 0;
        // used for reading inputs from user
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter 1st number numerator:");
        // read first number's p (numerator)
        firstNumerator = scanner.nextInt();

        // prepare validation check with 'false' value
        validInput = false;
        // check until valid denominator is given
        while (!validInput) {
            System.out.println("Please enter 1st number denominator:");
            // read first number's q (denominator)
            firstDenominator = scanner.nextInt();

            // found negative denominator or zero
            if (firstDenominator <= 0){
                System.out.println("The denominator should be greater than zero!");
            }
            // valid denominator
            else validInput = true;
        }

        System.out.println("Please enter 2nd number numerator:");
        // read second number's p (numerator)
        secondNumerator = scanner.nextInt();

        // prepare validation check with 'false' value
        validInput = false;
        // check until valid denominator is given
        while (!validInput) {
            System.out.println("Please enter 2nd number denominator:");
            // read first number's q (denominator)
            secondDenominator = scanner.nextInt();

            // found negative denominator or zero
            if (secondDenominator <= 0){
                System.out.println("The denominator should be greater than zero!");
            }
            // valid denominator
            else validInput = true;
        }

        // creates first rational number
        Rational firstNum = new Rational(firstNumerator, firstDenominator);
        // creates second rational number
        Rational secondNum = new Rational(secondNumerator, secondDenominator);
        // creates temporary result rational number, initialized to zero
        Rational resultNum = new Rational(0,1);
        // stores boolean answer as 'is equal to' or 'is greater than'
        boolean resultBool = false;

        // I think the input reducing is not needed by if I wrong
        // please unmark the 2 following lines
        /*
        firstNum = firstNum.reduce();
        secondNum = secondNum.reduce();
        */

        // first + second
        System.out.println(firstNum + " + " + secondNum + " = " + firstNum.plus(secondNum).reduce());
        // first - second
        System.out.println(firstNum + " - " + secondNum + " = " + firstNum.minus(secondNum).reduce());
        // first * second
        System.out.println(firstNum + " * " + secondNum + " = " + firstNum.multiply(secondNum).reduce());
        // first > second
        System.out.println(firstNum + " is" + (firstNum.greaterThan(secondNum) ? " " : " not ") + "greater than " + secondNum);
        // first = second
        System.out.println(firstNum + " is" + (firstNum.equals(secondNum) ? " " : " not ") + "equals to " + secondNum);
    }
}
