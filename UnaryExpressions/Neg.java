package UnaryExpressions;

import GenralExpressions.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The type UnaryExpressions.Neg.
 */
public class Neg extends UnaryExpression {
    /**
     * The constant OPERATOR.
     */
    public static final String OPERATOR = "UnaryExpressions.Neg";
    private static final String OPERATOR_SIGN = "-";

    /**
     * Instantiates a new UnaryExpressions.Neg.
     *
     * @param x the x
     */
    public Neg(Expression x) {
        super(x);
    }

    /**
     * Instantiates a new UnaryExpressions.Neg.
     *
     * @param x the x
     */
    public Neg(String x) {
        super(x);
    }

    /**
     * Instantiates a new UnaryExpressions.Neg.
     *
     * @param x the x
     */
    public Neg(double x) {
        super(x);
    }

    @Override
    public List<Num> getNums() {
        List<Num> numList = new ArrayList<>();
        numList.addAll(this.getX().getNums());
        return numList;
    }

    @Override
    public String toString() {
        return ("(" + OPERATOR_SIGN + this.getX().toString() + ")");
    }

    @Override
    public String operatorName() {
        return OPERATOR;
    }

    @Override
    protected double arithmeticAction(double x, double y) throws ArithmeticException {
        return -x;
    }

    @Override
    public Expression differentiateByRule(String var) {
        Expression derivativeX, derivative;
        derivativeX = this.getX().differentiate(var);
        derivative = new Neg(derivativeX);
        return derivative;
    }

    @Override
    public Expression simplifyByRule() {
        Expression simplerExpression = this;
        if (this.isInsignificant(simplerExpression, 0)) {
            simplerExpression = new Num(0);
        } else if (this.isInsignificant(simplerExpression, 1)) {
            simplerExpression = new Neg(new Num(1));
        } else if (this.isInsignificant(simplerExpression, -1)) {
            simplerExpression = new Num(1);
        }
        if (this.getX().operatorName().equals(Neg.OPERATOR)) {
            Neg innerNeg = (Neg) this.getX();
            simplerExpression = innerNeg.getX();
        }
        return simplerExpression;
    }
}
