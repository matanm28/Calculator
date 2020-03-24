package BinaryExpressions;

import GenralExpressions.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The type BinaryExpressions.Plus.
 */
public class Plus extends BinaryExpression {
    /**
     * The constant OPERATOR.
     */
    public static final String OPERATOR = "BinaryExpressions.Plus";
    /**
     * The constant OPERATOR_SIGN.
     */
    protected static final String OPERATOR_SIGN = " + ";

    /**
     * Instantiates a new BinaryExpressions.Plus.
     *
     * @param x the x
     * @param y the y
     */
    public Plus(Expression x, Expression y) {
        super(x, y);
    }

    /**
     * Instantiates a new BinaryExpressions.Plus.
     *
     * @param x the x
     * @param y the y
     */
    public Plus(String x, String y) {
        super(x, y);
    }

    /**
     * Instantiates a new BinaryExpressions.Plus.
     *
     * @param x the x
     * @param y the y
     */
    public Plus(double x, String y) {
        super(x, y);
    }

    /**
     * Instantiates a new BinaryExpressions.Plus.
     *
     * @param x the x
     * @param y the y
     */
    public Plus(String x, double y) {
        super(x, y);
    }

    /**
     * Instantiates a new BinaryExpressions.Plus.
     *
     * @param x the x
     * @param y the y
     */
    public Plus(double x, double y) {
        super(x, y);
    }

    /**
     * Instantiates a new BinaryExpressions.Plus.
     *
     * @param x the x
     * @param y the y
     */
    public Plus(Expression x, String y) {
        super(x, y);
    }

    /**
     * Instantiates a new BinaryExpressions.Plus.
     *
     * @param x the x
     * @param y the y
     */
    public Plus(String x, Expression y) {
        super(x, y);
    }

    /**
     * Instantiates a new BinaryExpressions.Plus.
     *
     * @param x the x
     * @param y the y
     */
    public Plus(Expression x, double y) {
        super(x, y);
    }

    /**
     * Instantiates a new BinaryExpressions.Plus.
     *
     * @param x the x
     * @param y the y
     */
    public Plus(double x, Expression y) {
        super(x, y);
    }

    @Override
    protected List<Expression> getAssociativeExpressions() {
        List<Expression> expressionsList = new ArrayList<>();
        if (areBasic(this.getX(), this.getY())) {
            expressionsList.add(this.getX());
            expressionsList.add(this.getY());
            return expressionsList;
        } else {
            if (this.getX() instanceof Plus) {
                expressionsList.addAll(((Plus) this.getX()).getAssociativeExpressions());
            } else if (this.getX() instanceof Minus) {
                expressionsList.addAll(((Minus) this.getX()).getAssociativeExpressions());
            } else {
                expressionsList.add(this.getX());
            }
            if (this.getY() instanceof Plus) {
                expressionsList.addAll(((Plus) this.getY()).getAssociativeExpressions());
            } else if (this.getY() instanceof Minus) {
                expressionsList.addAll(((Minus) this.getY()).getAssociativeExpressions());
            } else {
                expressionsList.add(this.getY());
            }
        }
        return expressionsList;
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
    public Expression differentiateByRule(String var) {
        Expression derivativeX, derivativeY, derivative;
        derivativeX = this.getX().differentiate(var);
        derivativeY = this.getY().differentiate(var);
        derivative = new Plus(derivativeX, derivativeY);
        return derivative;
    }

    @Override
    protected double arithmeticAction(double x, double y) {
        return x + y;
    }

    @Override
    public Expression simplifyByRule() {
        Expression simplerExpression = this;
        if (this.isInsignificant(this.getX(), 0)) {
            simplerExpression = this.getY().simplify();
        } else if (this.isInsignificant(this.getY(), 0)) {
            simplerExpression = this.getX().simplify();
        } else if (this.getX().areSame(this.getY())) {
            simplerExpression = new Mult(2, this.getX());
        } else if (checkOperatorsMatch(this.getX(), this.getY(), Log.OPERATOR)) {
            Log logX, logY;
            logX = (Log) this.getX();
            logY = (Log) this.getY();
            if (logX.getX().areSame(logY.getX())) {
                simplerExpression = new Log(logX.getX(), new Mult(logX.getY(), logY.getY()));
            }
        } else {
            BaseExpression simpler = this.simplifyAdvanced();
            simplerExpression = simpler;
        }
        return simplerExpression;
    }

    @Override
    public BaseExpression simplifyAdvanced() {
        boolean flag = false;
        Expression newExpression;
        List<Mult> expressionList = makeMult(this.getAssociativeExpressions());
        List<Expression> newExpressionsList = new ArrayList<>();
        List<Expression> usedExpressions = new ArrayList<>();
        for (Mult exp : expressionList) {
            if (exp.isOnlyNum()) {
                newExpressionsList.add(exp.getY());
                continue;
            }
            usedExpressions.add(exp);
            newExpressionsList.add(exp);
            for (Mult other : expressionList) {
                if (!(usedExpressions.contains(other)) && exp.getY().areSame(other.getY())) {
                    Expression tempPlus = new Plus(exp.getX(), other.getX());
                    if (exp.getX().isOnlyNum() && other.getX().isOnlyNum()) {
                        try {
                            newExpression = new Mult(tempPlus.evaluate(), exp.getY());
                        } catch (Exception e) {
                            printException(e);
                            newExpression = new Mult(tempPlus, exp.getY());
                        }
                    } else {
                        newExpression = new Mult(tempPlus, exp.getY());
                    }
                    newExpressionsList.add(newExpression);
                    newExpressionsList.remove(exp);
                    usedExpressions.add(other);
                    flag = true;
                }
            }
            if (!flag) {
                usedExpressions.remove(exp);
            }
            newExpressionsList.removeAll(usedExpressions);
        }
        BaseExpression simpleExp = makeExpFromList(newExpressionsList);
        if (simpleExp != null) {
            return simpleExp;
        } else {
            return this;
        }
    }


}
