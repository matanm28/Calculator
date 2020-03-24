package UnaryExpressions;

import BinaryExpressions.Mult;
import GenralExpressions.*;


/**
 * The type UnaryExpressions.Sin.
 */
public class Sin extends UnaryExpression {

    /**
     * The constant OPERATOR.
     */
    public static final String OPERATOR = "UnaryExpressions.Sin";
    private static final String OPERATOR_SIGN = "sin";

    /**
     * Instantiates a new UnaryExpressions.Sin.
     *
     * @param x the x
     */
    public Sin(Expression x) {
        super(x);
    }

    /**
     * Instantiates a new UnaryExpressions.Sin.
     *
     * @param x the x
     */
    public Sin(String x) {
        super(x);
    }

    /**
     * Instantiates a new UnaryExpressions.Sin.
     *
     * @param x the x
     */
    public Sin(double x) {
        super(x);
    }

    @Override
    public String operatorName() {
        return OPERATOR;
    }

    @Override
    protected double arithmeticAction(double x, double y) throws ArithmeticException {
        return Math.sin(x);
    }

    @Override
    public Expression differentiateByRule(String var) {
        Expression derivativeX, derivative;
        derivativeX = this.getX().differentiate(var);
        derivative = new Mult(new Cos(this.getX()), derivativeX);
        return derivative;
    }

    @Override
    public String toString() {
        return (OPERATOR_SIGN + "(" + this.getX().toString() + ")");
    }
}
