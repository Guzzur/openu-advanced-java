public abstract class CompoundExpression extends Expression {
    protected Expression first;
    protected Expression second;

    public CompoundExpression(Expression first, Expression second) {
        this.first = first;
        this.second = second;
    }

    public CompoundExpression() {
        this.first = new AtomicExpression(0);
        this.second = new AtomicExpression(0);
    }
}
