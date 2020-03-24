package BinaryExpressions;

import GenralExpressions.*;

/**
 * The type BinaryExpressions.Pow.
 */
public class Pow extends BinaryExpression {
    /**
     * The constant OPERATOR.
     */
    public static final String OPERATOR = "BinaryExpressions.Pow";
    private static final String OPERATOR_SIGN = "^";

    /**
     * Instantiates a new BinaryExpressions.Pow.
     *
     * @param x the x
     * @param y the y
     */
    public Pow(Expression x, Expression y) {
        super(x, y);
    }

    /**
     * Instantiates a new BinaryExpressions.Pow.
     *
     * @param x the x
     * @param y the y
     */
    public Pow(String x, String y) {
        super(x, y);
    }

    /**
     * Instantiates a new BinaryExpressions.Pow.
     *
     * @param x the x
     * @param y the y
     */
    public Pow(double x, String y) {
        super(x, y);
    }

    /**
     * Instantiates a new BinaryExpressions.Pow.
     *
     * @param x the x
     * @param y the y
     */
    public Pow(String x, double y) {
        super(x, y);
    }

    /**
     * Instantiates a new BinaryExpressions.Pow.
     *
     * @param x the x
     * @param y the y
     */
    public Pow(double x, double y) {
        super(x, y);
    }

    /**
     * Instantiates a new BinaryExpressions.Pow.
     *
     * @param x the x
     * @param y the y
     */
    public Pow(Expression x, String y) {
        super(x, y);
    }

    /**
     * Instantiates a new BinaryExpressions.Pow.
     *
     * @param x the x
     * @param y the y
     */
    public Pow(String x, Expression y) {
        super(x, y);
    }

    /**
     * Instantiates a new BinaryExpressions.Pow.
     *
     * @param x the x
     * @param y the y
     */
    public Pow(Expression x, double y) {
        super(x, y);
    }

    /**
     * Instantiates a new BinaryExpressions.Pow.
     *
     * @param x the x
     * @param y the y
     */
    public Pow(double x, Expression y) {
        super(x, y);
    }

    @Override
    public String toString() {
        return ("(" + this.getX().toString() + OPERATOR_SIGN + this.getY().toString() + ")");
    }

    @Override
    public String operatorName() {
        return OPERATOR;
    }

    @Override
    protected double arithmeticAction(double base, double exponent) throws ArithmeticException {
        double product;
        if (exponent == 0 && base == 0) {
            throw new ArithmeticException("the expression 0 by the power 0 is not supported");
        } else {
            product = Math.pow(base, exponent);
            return product;
        }
    }

    @Override
    public Expression differentiateByRule(String var) {
        Expression derivativeX, derivativeY, derivative, innerDerivative, innerDerivative1, innerDerivative2;
        derivativeX = this.getX().differentiate(var);
        derivativeY = this.getY().differentiate(var);
        innerDerivative1 = new Mult(derivativeX, new Div(this.getY(), this.getX()));
        innerDerivative2 = new Mult(derivativeY, new Log(E, this.getX()));
        innerDerivative = new Plus(innerDerivative1, innerDerivative2);
        derivative = new Mult(this, innerDerivative);
        return derivative;
    }

    @Override
    public Expression simplifyByRule() {
        Expression simplerExpression = this;
        if (this.isInsignificant(this.getY(), 1)) {
            simplerExpression = this.getX().simplify();
        } else if (this.isInsignificant(this.getY(), 0)) {
            simplerExpression = new Num(1);
        } else if (this.getX().operatorName().equals(Pow.OPERATOR)) {
            Pow tempExp = (Pow) this.getX();
            simplerExpression = new Pow(tempExp.getX(), new Mult(tempExp.getY(), this.getY()));
        }
        return simplerExpression;
    }

    @Override
    public BaseExpression simplifyAdvanced() {
        return this;
    }
}
