package BinaryExpressions;

import GenralExpressions.*;

/**
 * The type BinaryExpressions.Ln.
 */
public class Ln extends Log {

    /**
     * The constant OPERATOR.
     */
    public static final String OPERATOR = "BinaryExpressions.Ln";
    private static final String OPERTAOR_SIGN = "ln";

    /**
     * Instantiates a new BinaryExpressions.Ln.
     *
     * @param antilogarithm the antilogarithm
     */
    public Ln(String antilogarithm) {
        super(E.getNum(), antilogarithm);
    }

    /**
     * Instantiates a new BinaryExpressions.Ln.
     *
     * @param antilogarithm the antilogarithm
     */
    public Ln(Expression antilogarithm) {
        super(E.getNum(), antilogarithm);
    }

    /**
     * Instantiates a new BinaryExpressions.Ln.
     *
     * @param antilogarithm the antilogarithm
     */
    public Ln(double antilogarithm) {
        super(E.getNum(), antilogarithm);
    }


    @Override
    public String toString() {
        return (OPERTAOR_SIGN + "(" + this.getY().toString() + ")");
    }

    @Override
    public String operatorName() {
        return OPERATOR;
    }

    @Override
    public Expression differentiateByRule(String var) {
        Expression newLn, derivativeY, derivative, denominator;
        derivativeY = this.getY().differentiate(var);
        //newLn = new BinaryExpressions.Ln(this.getX());
        denominator = this.getY();
        derivative = new Div(derivativeY, denominator);
        return derivative;
    }
}

