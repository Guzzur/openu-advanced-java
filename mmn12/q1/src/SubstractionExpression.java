public class SubstractionExpression extends CompoundExpression {
    /**
     * C'tor
     * @param first
     * @param second
     */
    public SubstractionExpression(Expression first, Expression second){
        this.first = first;
        this.second = second;
    }

    /**
     * @return left - right expressions value
     */
    @Override
    public double calculate() {
        return super.first.calculate() - super.second.calculate();
    }

    /**
     * @return String representation of substractin expression
     */
    @Override
    public String toString() {
        return "(" + this.first + " - " + this.second + ")";
    }
}

