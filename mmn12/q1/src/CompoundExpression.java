public abstract class CompoundExpression extends Expression {
    // holds "left" part of expression
    protected Expression first;

    // holds "right" part of expression
    protected Expression second;

    /**
     * C'tor
     * @param first
     * @param second
     */
    public CompoundExpression(Expression first, Expression second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Default C'tor
     */
    public CompoundExpression() {
        this.first = new AtomicExpression(0);
        this.second = new AtomicExpression(0);
    }
}
