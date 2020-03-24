package BinaryExpressions;

import GenralExpressions.*;

/**
 * The type BinaryExpressions.Div.
 */
public class Div extends BinaryExpression {

    /**
     * The constant OPERATOR.
     */
    public static final String OPERATOR = "BinaryExpressions.Div";
    private static final String OPERATOR_SIGN = " / ";

    /**
     * Instantiates a new BinaryExpressions.Div.
     *
     * @param x the x
     * @param y the y
     */
    public Div(Expression x, Expression y) {
        super(x, y);
    }

    /**
     * Instantiates a new BinaryExpressions.Div.
     *
     * @param x the x
     * @param y the y
     */
    public Div(String x, String y) {
        super(x, y);
    }

    /**
     * Instantiates a new BinaryExpressions.Div.
     *
     * @param x the x
     * @param y the y
     */
    public Div(double x, String y) {
        super(x, y);
    }

    /**
     * Instantiates a new BinaryExpressions.Div.
     *
     * @param x the x
     * @param y the y
     */
    public Div(String x, double y) {
        super(x, y);
    }

    /**
     * Instantiates a new BinaryExpressions.Div.
     *
     * @param x the x
     * @param y the y
     */
    public Div(double x, double y) {
        super(x, y);
    }

    /**
     * Instantiates a new BinaryExpressions.Div.
     *
     * @param x the x
     * @param y the y
     */
    public Div(Expression x, String y) {
        super(x, y);
    }

    /**
     * Instantiates a new BinaryExpressions.Div.
     *
     * @param x the x
     * @param y the y
     */
    public Div(String x, Expression y) {
        super(x, y);
    }

    /**
     * Instantiates a new BinaryExpressions.Div.
     *
     * @param x the x
     * @param y the y
     */
    public Div(Expression x, double y) {
        super(x, y);
    }

    /**
     * Instantiates a new BinaryExpressions.Div.
     *
     * @param x the x
     * @param y the y
     */
    public Div(double x, Expression y) {
        super(x, y);
    }

    @Override
    protected double arithmeticAction(double x, double y) throws ArithmeticException {
        double quotition;
        if (y != 0) {
            quotition = x / y;
            return quotition;
        } else {
            throw new ArithmeticException("Division by 0 is not supported");
        }
    }

    @Override
    public Expression differentiateByRule(String var) {
        Expression derivativeX, derivativeX1, derivativeX2, derivativeY, derivative;
        derivativeX1 = new Mult(this.getX().differentiate(var), this.getY());
        derivativeX2 = new Mult(this.getX(), this.getY().differentiate(var));
        derivativeX = new Minus(derivativeX1, derivativeX2);
        derivativeY = new Pow(this.getY(), 2);
        derivative = new Div(derivativeX, derivativeY);
        return derivative;
    }

    @Override
    public Expression simplifyByRule() {
        Expression simplerExpression = this;
        if (this.isInsignificant(this.getX(), 0)) {
            simplerExpression = new Num(0);
        } else if (this.isInsignificant(this.getY(), 1)) {
            simplerExpression = this.getX().simplify();
        } else if (this.getX().areSame(this.getY())) {
            simplerExpression = new Num(1);
        } else if (checkOperatorsMatch(this.getX(), this.getY(), Div.OPERATOR)) {
            Div newFraction = this.fractionsDivision();
            return newFraction.simplify();
        }
        return simplerExpression;
    }

    /**
     * Fractions division div.
     *
     * @return the div
     */
    public Div fractionsDivision() {
        Div fraction, top, bottom;
        if (checkOperatorsMatch(this.getX(), this.getY(), Div.OPERATOR)) {
            top = (Div) this.getX();
            bottom = (Div) this.getY();
            fraction = new Div(new Mult(top.getX(), bottom.getY()), new Mult(top.getY(), bottom.getX()));
            return fraction;
        } else {
            return this;
        }
    }


    @Override
    public String operatorName() {
        return OPERATOR;
    }

    @Override
    public String toString() {
        return ("(" + this.getX().toString() + OPERATOR_SIGN + this.getY().toString() + ")");
    }

    @Override
    public BaseExpression simplifyAdvanced() {
        return this;

    }
}
