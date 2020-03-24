package BinaryExpressions;

import GenralExpressions.*;

import java.util.List;

/**
 * The type BinaryExpressions.Mult.
 */
public class Mult extends BinaryExpression {
    /**
     * The constant OPERATOR.
     */
    public static final String OPERATOR = "BinaryExpressions.Mult";
    private static final String OPERATOR_SIGN = " * ";

    /**
     * Instantiates a new BinaryExpressions.Mult.
     *
     * @param x the x
     * @param y the y
     */
    public Mult(Expression x, Expression y) {
        super(x, y);
    }

    /**
     * Instantiates a new BinaryExpressions.Mult.
     *
     * @param x the x
     * @param y the y
     */
    public Mult(String x, String y) {
        super(x, y);
    }

    /**
     * Instantiates a new BinaryExpressions.Mult.
     *
     * @param x the x
     * @param y the y
     */
    public Mult(double x, String y) {
        super(x, y);
    }

    /**
     * Instantiates a new BinaryExpressions.Mult.
     *
     * @param x the x
     * @param y the y
     */
    public Mult(String x, double y) {
        super(x, y);
    }

    /**
     * Instantiates a new BinaryExpressions.Mult.
     *
     * @param x the x
     * @param y the y
     */
    public Mult(double x, double y) {
        super(x, y);
    }

    /**
     * Instantiates a new BinaryExpressions.Mult.
     *
     * @param x the x
     * @param y the y
     */
    public Mult(Expression x, String y) {
        super(x, y);
    }

    /**
     * Instantiates a new BinaryExpressions.Mult.
     *
     * @param x the x
     * @param y the y
     */
    public Mult(String x, Expression y) {
        super(x, y);
    }

    /**
     * Instantiates a new BinaryExpressions.Mult.
     *
     * @param x the x
     * @param y the y
     */
    public Mult(Expression x, double y) {
        super(x, y);
    }

    /**
     * Instantiates a new BinaryExpressions.Mult.
     *
     * @param x the x
     * @param y the y
     */
    public Mult(double x, Expression y) {
        super(x, y);
    }

    /**
     * Differentiate by rule advanced expression.
     *
     * @param var the var
     * @return the expression
     */
    public Expression differentiateByRuleAdvanced(String var) {
        Expression derivativeX, derivativeY, derivative;
        boolean xIsFunc, yIsFunc;
        xIsFunc = this.getX().getVariables().contains(var);
        yIsFunc = this.getY().getVariables().contains(var);
        if (xIsFunc ^ yIsFunc) {
            //differentiation by x
            if (xIsFunc) {
                derivativeX = this.getX().differentiate(var);
                derivativeY = this.getY();
            } else {
                derivativeX = this.getX();
                derivativeY = this.getY().differentiate(var);
            }
            derivative = new Mult(derivativeX, derivativeY);
            //chain rule differentiation
        } else if (xIsFunc && yIsFunc) {
            derivativeX = new Mult(this.getX().differentiate(var), this.getY());
            derivativeY = new Mult(this.getX(), this.getY().differentiate(var));
            derivative = new Plus(derivativeX, derivativeY);

            //constants differentiation
        } else {
            derivative = new Num(0);
        }
        return derivative;
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
    protected double arithmeticAction(double x, double y) {
        return x * y;
    }

    @Override
    public Expression differentiateByRule(String var) {
        Expression derivativeX, derivativeY, derivative;
        derivativeX = new Mult(this.getX().differentiate(var), this.getY());
        derivativeY = new Mult(this.getX(), this.getY().differentiate(var));
        derivative = new Plus(derivativeX, derivativeY);
        return derivative;
    }

    @Override
    public Expression simplifyByRule() {
        Expression simplerExpression = this.numBefore();
        if (this.isInsignificant(this.getX(), 1)) {
            simplerExpression = this.getY().simplify();
        } else if (this.isInsignificant(this.getY(), 1)) {
            simplerExpression = this.getX().simplify();
        } else if (this.isInsignificant(this.getX(), 0) || this.isInsignificant(this.getY(), 0)) {
            simplerExpression = new Num(0);
        } else if (checkOperatorsMatch(this.getX(), this.getY(), Pow.OPERATOR)) {
            Pow tempExp1, tempExp2;
            tempExp1 = (Pow) this.getX();
            tempExp2 = (Pow) this.getY();
            if (tempExp1.getX().areSame(tempExp2.getX())) {
                Expression tempExponent = new Plus(tempExp1.getY(), tempExp2.getY());
                Expression exponent = tempExponent.simplify();
                simplerExpression = new Pow(tempExp1.getX(), exponent);
            } else if (tempExp1.getY().areSame(tempExp2.getY())) {
                simplerExpression = new Pow(new Mult(tempExp1.getX(), tempExp2.getX()), tempExp1.getY());
            }
        } else if (this.getX().areSame(this.getY())) {
            simplerExpression = new Pow(this.getX(), 2);
        }
        return simplerExpression;
    }

    /**
     * GenralExpressions.Num before mult.
     *
     * @return the mult
     */
    protected Mult numBefore() {
        if (this.getY().isOnlyNum()) {
            return new Mult(this.getY(), this.getX());
        } else {
            return this;
        }
    }

    @Override
    public BaseExpression simplifyAdvanced() {
        Mult newExp = this;
        List<Num> numList;
        if (checkOperatorsMatch(this.getY(), this.getX(), OPERATOR)) {
            Mult tempMultX = (Mult) this.getX();
            Mult tempMultY = (Mult) this.getY();
            if (tempMultX.getX().isOnlyNum()) {
                numList = tempMultX.getX().getNums();
                if (tempMultY.getX().isOnlyNum()) {
                    numList.addAll(tempMultY.getX().getNums());
                    if (tempMultX.getY().areSame(tempMultY.getY())) {
                        newExp = new Mult(sumUpListMult(numList), new Pow(tempMultX.getY(), 2));
                    } else {
                        newExp = new Mult(sumUpListMult(numList), new Mult(tempMultX.getY(), tempMultY.getY()));
                    }
                } else if (tempMultY.getY().isOnlyNum()) {
                    numList.addAll(tempMultY.getY().getNums());
                    if (tempMultX.getY().areSame(tempMultY.getX())) {
                        newExp = new Mult(sumUpListMult(numList), new Pow(tempMultX.getY(), 2));
                    } else {
                        newExp = new Mult(sumUpListMult(numList), new Mult(tempMultX.getY(), tempMultY.getX()));
                    }
                }
            } else if (tempMultX.getY().isOnlyNum()) {
                numList = tempMultX.getY().getNums();
                if (tempMultY.getX().isOnlyNum()) {
                    numList.addAll(tempMultY.getX().getNums());
                    if (tempMultX.getX().areSame(tempMultY.getY())) {
                        newExp = new Mult(sumUpListMult(numList), new Pow(tempMultX.getX(), 2));
                    } else {
                        newExp = new Mult(sumUpListMult(numList), new Mult(tempMultX.getX(), tempMultY.getY()));
                    }
                } else if (tempMultY.getY().isOnlyNum()) {
                    numList.addAll(tempMultY.getY().getNums());
                    if (tempMultX.getX().areSame(tempMultY.getX())) {
                        newExp = new Mult(sumUpListMult(numList), new Pow(tempMultX.getY(), 2));
                    } else {
                        newExp = new Mult(sumUpListMult(numList), new Mult(tempMultX.getY(), tempMultY.getX()));
                    }
                }
            }
        }
        return newExp;
    }
}
