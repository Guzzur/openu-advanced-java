public class SubstractionExpression extends CompoundExpression {

    public SubstractionExpression(Expression first, Expression second){
        this.first = first;
        this.second = second;
    }

    @Override
    public double calculate() {
        return super.first.calculate() - super.second.calculate();
    }

    @Override
    public String toString() {
        return this.first + " - " + this.second;
    }
}

