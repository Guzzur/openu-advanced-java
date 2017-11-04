public class AdditionExpression extends CompoundExpression {

    public AdditionExpression(Expression first, Expression second){
        super(first, second);
    }

    @Override
    public double calculate() {
        return super.first.calculate() + super.second.calculate();
    }

    @Override
    public String toString() {
        return this.first + " + " + this.second;
    }
}
