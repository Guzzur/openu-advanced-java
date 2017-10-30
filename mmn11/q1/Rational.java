/**
 * Advanced Java, Maman 11
 * Rational numbers class
 *
 * @author  Felix Razykov
 * @version 1.0
 * @since   2017-10-28
 */
public class Rational {
    private int p; // numerator
    private int q; // denominator

    /**
     * Constructor
     * @param p: numerator
     * @param q: denominator
     */
    public Rational(int p, int q) {
        // check validity of the denominator
        if (q > 0) {
            this.p = p;
            this.q = q;
        }
        // the number is not valid, make it zero
        else
        {
            this.p = 0;
            this.q = 1;
        }
    }

    /**
     * Checks if this > other
     * @param other: comparable
     * @return true if this > other (ad > bc)
     */
    public boolean greaterThan(Rational other) {
        return (this.p * other.q) > (this.q * other.p);
    }

    /**
     * Checks if this == other
     * @param other: comparable
     * @return true if this == other (ad == bc)
     */
    public boolean equals(Rational other) {
        return (this.p * other.q) == (this.q * other.p);
    }

    /**
     * Calculates this + other
     * @param other
     * @return this + other ((ad+bc)/bd)
     */
    public Rational plus(Rational other) {
        return new Rational(((this.p * other.q) + (this.q * other.p)),(this.q * other.q));
    }

    /**
     * Calculates this - other
     * @param other
     * @return this - other ((ad-bc)/bd)
     */
    public Rational minus(Rational other) {
        return new Rational(((this.p * other.q) - (this.q * other.p)),(this.q * other.q));
    }

    /**
     * Calculates this * other
     * @param other
     * @return this * other (ac/bd)
     */
    public Rational multiply(Rational other) {
        return new Rational((this.p * other.p),(this.q * other.q));
    }

    /**
     * @return numerator
     */
    public int getNumerator() { return this.p; }

    /**
     * @return denominator
     */
    public int getDenominator() { return this.q; }

    /**
     * @Override: Object.toString()
     * @return: string for numerator/denominator or 0
     */
    public String toString() {
        // if p is 0 - return "0"
        // if q is 1 - return p
        return (this.p == 0) ? "0" : ((this.q == 1) ? "" + this.p : (this.p + "/" + this.q));
    }

    /**
     * Creates reduced rational number
     * @return reduced rational number
     */
    public Rational reduce() {
        // find GCD for numerator and denominator
        // absolute is needed for this implementation of GCD alg.
        int gcd = findGcd(Math.abs(this.p), this.q);
        return new Rational(this.p / gcd, this.q / gcd);
    }

    /**
     * Euclides (Euclid) GCD algorithm
     * @param p: alg's a param
     * @param q: alg's b param
     * @return GCD
     */
    private static int findGcd(int p, int q) {
        if(q != 0) return findGcd(q, p % q);
        else return p;
    }
}
