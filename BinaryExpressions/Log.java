package BinaryExpressions;

import GenralExpressions.*;

/**
 * The type BinaryExpressions.Log.
 */
public class Log extends BinaryExpression {
    /**
     * The constant OPERATOR.
     */
    public static final String OPERATOR = "BinaryExpressions.Log";
    private static final String OPERTAOR_SIGN = "log";

    private boolean isLn = false;
    private Ln equivalentLn = null;


    /**
     * Instantiates a new BinaryExpressions.Log.
     *
     * @param x the x
     * @param y the y
     */
    public Log(Expression x, Expression y) {
        super(x, y);
        if (!(this.operatorName().equals(Ln.OPERATOR)) && this.getX().toString() == "e") {
            this.isLn = true;
            this.equivalentLn = new Ln(this.getY());
        }
    }

    /**
     * Instantiates a new BinaryExpressions.Log.
     *
     * @param x the x
     * @param y the y
     */
    public Log(String x, String y) {
        super(x, y);
        if (!(this.operatorName().equals(Ln.OPERATOR)) && this.getX().toString() == "e") {
            this.isLn = true;
            this.equivalentLn = new Ln(this.getY());
        }
    }

    /**
     * Instantiates a new BinaryExpressions.Log.
     *
     * @param x the x
     * @param y the y
     */
    public Log(double x, String y) {
        super(x, y);
        if (!(this.operatorName().equals(Ln.OPERATOR)) && this.getX().toString() == "e") {
            this.isLn = true;
            this.equivalentLn = new Ln(this.getY());
        }
    }

    /**
     * Instantiates a new BinaryExpressions.Log.
     *
     * @param x the x
     * @param y the y
     */
    public Log(String x, double y) {
        super(x, y);
        if (!(this.operatorName().equals(Ln.OPERATOR)) && this.getX().toString() == "e") {
            this.isLn = true;
            this.equivalentLn = new Ln(this.getY());
        }
    }

    /**
     * Instantiates a new BinaryExpressions.Log.
     *
     * @param x the x
     * @param y the y
     */
    public Log(double x, double y) {
        super(x, y);
        if (!(this.operatorName().equals(Ln.OPERATOR)) && this.getX().toString() == "e") {
            this.isLn = true;
            this.equivalentLn = new Ln(this.getY());
        }
    }

    /**
     * Instantiates a new BinaryExpressions.Log.
     *
     * @param x the x
     * @param y the y
     */
    public Log(Expression x, String y) {
        super(x, y);
        if (!(this.operatorName().equals(Ln.OPERATOR)) && this.getX().toString() == "e") {
            this.isLn = true;
            this.equivalentLn = new Ln(this.getY());
        }
    }

    /**
     * Instantiates a new BinaryExpressions.Log.
     *
     * @param x the x
     * @param y the y
     */
    public Log(String x, Expression y) {
        super(x, y);
        if (!(this.operatorName().equals(Ln.OPERATOR)) && this.getX().toString() == "e") {
            this.isLn = true;
            this.equivalentLn = new Ln(this.getY());
        }
    }

    /**
     * Instantiates a new BinaryExpressions.Log.
     *
     * @param x the x
     * @param y the y
     */
    public Log(Expression x, double y) {
        super(x, y);
        if (!(this.operatorName().equals(Ln.OPERATOR)) && this.getX().toString() == "e") {
            this.isLn = true;
            this.equivalentLn = new Ln(this.getY());
        }
    }

    /**
     * Instantiates a new BinaryExpressions.Log.
     *
     * @param x the x
     * @param y the y
     */
    public Log(double x, Expression y) {
        super(x, y);
        if (!(this.operatorName().equals(Ln.OPERATOR)) && this.getX().toString() == "e") {
            this.isLn = true;
            this.equivalentLn = new Ln(this.getY());
        }
    }

    @Override
    public String operatorName() {
        return OPERATOR;
    }

    @Override
    public String toString() {
        if (this.isLn) {
            return this.equivalentLn.toString();
        } else {
            return (OPERTAOR_SIGN + "(" + this.getX().toString() + ", " + this.getY().toString() + ")");
        }
    }

    @Override
    protected double arithmeticAction(double base, double antilogarithm) throws ArithmeticException {
        double log;
        if (base != 0 && base == antilogarithm) {
            return 1;
        } else if (base > 0 && base != 1 && antilogarithm >= 0) {
            log = logByBase(base, antilogarithm);
            return log;
        } else {
            String str;
            if (base == 1) {
                str = "BinaryExpressions.Log base is not defined for 1";
            } else if (base <= 0) {
                str = "BinaryExpressions.Log base must be greater than 0 and different from 1";
            } else {
                str = "BinaryExpressions.Log antilogarithm must be 0 or greater";
            }
            throw new ArithmeticException(str);
        }
    }

    /**
     * calculates a log by a given base and antilogarithm.
     *
     * @param base          the base
     * @param antilogarithm the antilogarithm
     * @return the double
     */
    private static double logByBase(double base, double antilogarithm) {
        return (Math.log(antilogarithm) / Math.log(base));
    }

    @Override
    public BaseExpression simplifyAdvanced() {
        if (this.isLn) {
            return this.equivalentLn;
        } else {
            return this;
        }
    }

    @Override
    public Expression differentiateByRule(String var) {
        if (this.isLn) {
            return this.equivalentLn.differentiateByRule(var);
        } else {
            Expression newLn, derivativeY, derivative, denominator;
            boolean baseIsFunc = this.getX().getVariables().contains(var);
            if (baseIsFunc) {
                return this.logDifferentiationHelp(var);
            } else {
                derivativeY = this.getY().differentiate(var);
                newLn = new Ln(this.getX());
                denominator = new Mult(newLn, this.getY());
                derivative = new Div(derivativeY, denominator);
                return derivative;
            }
        }
    }

    /**
     * BinaryExpressions.Log differentiation help expression.
     *
     * @param var the var
     * @return the expression
     */
    public Expression logDifferentiationHelp(String var) {
        Expression newX, newY, lnForm, derivative;
        newX = new Ln(this.getY());
        newY = new Ln(this.getX());
        lnForm = new Div(newX, newY);
        derivative = lnForm.differentiate(var);
        return derivative;
    }

    @Override
    public Expression simplifyByRule() {
        Expression simplerExpression = this;
        if (this.isInsignificant(this.getY(), 1)) {
            simplerExpression = new Num(0);
        } else if (this.getX().areSame(this.getY())) {
            simplerExpression = new Num(1);
        }
        return simplerExpression;
    }

}
