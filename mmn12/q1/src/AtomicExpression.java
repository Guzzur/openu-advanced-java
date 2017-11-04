public class AtomicExpression extends Expression {
    private double elem;

    public AtomicExpression(double value){
        this.elem = value;
    }

    @Override
    public double calculate() {
        return this.elem;
    }

    @Override
    public String toString() {
        return "" + this.elem;
    }
}
