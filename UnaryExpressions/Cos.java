package UnaryExpressions;

import BinaryExpressions.Mult;
import GenralExpressions.*;

/**
 * The type UnaryExpressions.Cos.
 */
public class Cos extends UnaryExpression {
    /**
     * The constant OPERATOR.
     */
    public static final String OPERATOR = "UnaryExpressions.Cos";
    private static final String OPERATOR_SIGN = "cos";


    /**
     * Instantiates a new UnaryExpressions.Cos.
     *
     * @param x the x
     */
    public Cos(Expression x) {
        super(x);
    }

    /**
     * Instantiates a new UnaryExpressions.Cos.
     *
     * @param x the x
     */
    public Cos(String x) {
        super(x);
    }

    /**
     * Instantiates a new UnaryExpressions.Cos.
     *
     * @param x the x
     */
    public Cos(double x) {
        super(x);
    }

    @Override
    protected double arithmeticAction(double x, double y) throws ArithmeticException {
        return Math.cos(x);
    }

    @Override
    public Expression differentiateByRule(String var) {
        Expression derivativeX, derivative;
        derivativeX = this.getX().differentiate(var);
        derivative = new Neg(new Mult(new Sin(this.getX()), derivativeX));
        return derivative;
    }

    @Override
    public String toString() {
        return (OPERATOR_SIGN + "(" + this.getX().toString() + ")");
    }

    @Override
    public String operatorName() {
        return OPERATOR;
    }

}
