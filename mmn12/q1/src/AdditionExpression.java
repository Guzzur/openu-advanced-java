public class AdditionExpression extends CompoundExpression {
    /**
     * C'tor
     * @param first
     * @param second
     */
    public AdditionExpression(Expression first, Expression second){
        super(first, second);
    }

    /**
     * @return left + right expressions value
     */
    @Override
    public double calculate() {
        return super.first.calculate() + super.second.calculate();
    }

    /**
     * @return String representation of addition expression
     */
    @Override
    public String toString() {
        return "(" + this.first + " + " + this.second + ")";
    }
}
