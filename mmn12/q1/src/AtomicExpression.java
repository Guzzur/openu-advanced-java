public class AtomicExpression extends Expression {
    // holds element value
    private double elem;

    /**
     * C'tor
     * @param value will define elem value
     */
    public AtomicExpression(double value){
        this.elem = value;
    }

    /**
     * Returns elem value, that's this type of expression value
     * @return
     */
    @Override
    public double calculate() {
        return this.elem;
    }

    /**
     * @return string representation of atomic expression
     */
    @Override
    public String toString() {
        return "" + this.elem;
    }
}
