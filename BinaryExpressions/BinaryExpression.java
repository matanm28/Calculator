package BinaryExpressions;

import GenralExpressions.*;
import UnaryExpressions.*;


import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.TreeSet;

/**
 * The type Binary expression.
 */
public abstract class BinaryExpression extends BaseExpression {

    private Expression x;
    private Expression y;
    private Expression publicX;
    private Expression publicY;

    /**
     * Instantiates a new Binary expression.
     *
     * @param x the x
     * @param y the y
     */
    public BinaryExpression(Expression x, Expression y) {
        this.x = x;
        this.y = y;
        this.publicX = x.clone();
        this.publicY = y.clone();
    }

    /**
     * Instantiates a new Binary expression.
     *
     * @param x the x
     * @param y the y
     */
    public BinaryExpression(String x, String y) {
        this.x = new Var(x);
        this.y = new Var(y);
        this.publicX = this.x.clone();
        this.publicY = this.y.clone();
    }

    /**
     * Instantiates a new Binary expression.
     *
     * @param x the x
     * @param y the y
     */
    public BinaryExpression(double x, String y) {
        this.x = new Num(x);
        this.y = new Var(y);
        this.publicX = this.x.clone();
        this.publicY = this.y.clone();
    }

    /**
     * Instantiates a new Binary expression.
     *
     * @param x the x
     * @param y the y
     */
    public BinaryExpression(String x, double y) {
        this.x = new Var(x);
        this.y = new Num(y);
        this.publicX = this.x.clone();
        this.publicY = this.y.clone();
    }

    /**
     * Instantiates a new Binary expression.
     *
     * @param x the x
     * @param y the y
     */
    public BinaryExpression(double x, double y) {
        this.x = new Num(x);
        this.y = new Num(y);
        this.publicX = this.x.clone();
        this.publicY = this.y.clone();
    }

    /**
     * Instantiates a new Binary expression.
     *
     * @param x the x
     * @param y the y
     */
    public BinaryExpression(Expression x, String y) {
        this.x = x;
        this.y = new Var(y);
        this.publicX = this.x.clone();
        this.publicY = this.y.clone();
    }

    /**
     * Instantiates a new Binary expression.
     *
     * @param x the x
     * @param y the y
     */
    public BinaryExpression(String x, Expression y) {
        this.x = new Var(x);
        this.y = y;
        this.publicX = this.x.clone();
        this.publicY = this.y.clone();
    }

    /**
     * Instantiates a new Binary expression.
     *
     * @param x the x
     * @param y the y
     */
    public BinaryExpression(Expression x, double y) {
        this.x = x;
        this.y = new Num(y);
        this.publicX = this.x.clone();
        this.publicY = this.y.clone();
    }

    /**
     * Instantiates a new Binary expression.
     *
     * @param x the x
     * @param y the y
     */
    public BinaryExpression(double x, Expression y) {
        this.x = new Num(x);
        this.y = y;
        this.publicX = this.x.clone();
        this.publicY = this.y.clone();
    }

    /**
     * Get associative expressions list.
     *
     * @return the list
     */
    protected List<Expression> getAssociativeExpressions() {
        List<Expression> expressionList = new ArrayList<>();
        expressionList.add(this);
        return expressionList;
    }

    @Override
    public List<Var> getVars() {
        List<Var> varsList = new ArrayList<>();
        varsList.addAll(this.x.getVars());
        varsList.addAll(this.y.getVars());
        return varsList;
    }

    @Override
    public List<Num> getNums() {
        List<Num> numList = new ArrayList<>();
        numList.addAll(this.x.getNums());
        numList.addAll(this.y.getNums());
        return numList;
    }

    @Override
    public List<Expression> getExpressions() {
        List<Expression> expressionList = new ArrayList<>();
        if (BaseExpression.areBasic(this.x, this.y)) {
            expressionList.add(this);
        } else {
            expressionList.addAll(this.x.getExpressions());
            expressionList.addAll(this.y.getExpressions());
        }
        return expressionList;
    }


    /**
     * Make mult list.
     *
     * @param expressionList the expression list
     * @return the list
     */
    protected static List<Mult> makeMult(List<Expression> expressionList) {
        double totalNums = 0;
        List<Mult> multList = new ArrayList<>();
        for (Expression exp : expressionList) {
            if (exp.isOnlyNum()) {
                if (exp instanceof Neg) {
                    totalNums += BaseExpression.sumUpListNeg(exp.getNums());
                } else {
                    totalNums += BaseExpression.sumUpList(exp.getNums());
                }
            } else if (exp instanceof Neg) {
                Expression innerExp = ((Neg) exp).getX();
                if (innerExp instanceof Mult) {
                    Mult negMult = ((Mult) innerExp).numBefore();
                    if (negMult.getX().isOnlyNum()) {
                        try {
                            double num = negMult.getX().evaluate();
                            multList.add(new Mult(-num, negMult.getY()));
                        } catch (Exception e) {
                            BaseExpression.printException(e);
                        }
                    } else {
                        multList.add(new Mult(-1, negMult));
                    }
                } else {
                    multList.add(new Mult(-1, innerExp));
                }
            } else if (exp instanceof Mult) {
                multList.add(((Mult) exp).numBefore());
            } else {
                multList.add(new Mult(1, exp));
            }
        }
        if (totalNums != 0) {
            multList.add(new Mult(1, totalNums));
        }
        return multList;
    }

    /**
     * Gets x.
     *
     * @return the x
     */
    public Expression getX() {
        return this.publicX;
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public Expression getY() {
        return this.publicY;
    }

    /**
     * Make exp from list base expression.
     *
     * @param expressionList the expression list
     * @return the base expression
     */
    protected static BaseExpression makeExpFromList(List<Expression> expressionList) {
        if (expressionList.isEmpty()) {
            return null;
        } else {
            for (int i = 0; i < expressionList.size(); i++) {
                if (expressionList.get(i) instanceof Mult) {
                    Mult tempExp = (Mult) expressionList.get(i);
                    if (tempExp.getX().isOnlyNum()) {
                        try {
                            double num = tempExp.getX().evaluate();
                            if (num == 1) {
                                expressionList.remove(i);
                                expressionList.add(i, tempExp.getY());
                            } else if (num == 0) {
                                expressionList.remove(i);
                            } else if (num < 0) {
                                Expression negExp;
                                if (num == -1) {
                                    negExp = new Neg(tempExp.getY());
                                } else {
                                    negExp = new Neg(new Mult((Math.abs(num)), tempExp.getY()));
                                }
                                expressionList.remove(i);
                                expressionList.add(negExp);
                            }
                        } catch (Exception e) {
                            BaseExpression.printException(e);
                        }
                    }

                }
            }
        }
        if (expressionList.size() == 0) {
            return new Plus(0, 0);
        } else if (expressionList.size() == 1) {
            return new Plus(expressionList.get(0), 0);
        } else if (expressionList.size() == 2) {
            if (expressionList.get(0) instanceof Neg ^ expressionList.get(1) instanceof Neg) {
                if (expressionList.get(0) instanceof Neg) {
                    return new Minus(expressionList.get(1), ((Neg) expressionList.get(0)).getX());
                } else {
                    return new Minus(expressionList.get(0), ((Neg) expressionList.get(1)).getX());
                }
            } else if (expressionList.get(0) instanceof Neg && expressionList.get(1) instanceof Neg) {
                return new Minus(expressionList.get(0), ((Neg) expressionList.get(1)).getX());
            } else {
                return new Plus(expressionList.get(0), expressionList.get(1));
            }
        } else {
            double listSize = expressionList.size();
            BaseExpression tempExp, newExp;
            tempExp = new Plus(expressionList.get(0), expressionList.get(1));
            newExp = tempExp;
            for (int i = 2; i < expressionList.size(); i++) {
                newExp = new Plus(tempExp, expressionList.get(i));
                tempExp = newExp;
            }
            return newExp;
        }
    }

    @Override
    public double evaluate() throws Exception {
        return this.evaluate(null);
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        double scoreX, scoreY, finalScore;
        if (this == null || this.x == null || this.y == null) {
            throw new NullPointerException("GenralExpressions.Expression or part of it is null and can not be evaluated");
        } else {
            try {
                scoreX = this.x.evaluate(assignment);
                scoreY = this.y.evaluate(assignment);
                finalScore = this.arithmeticAction(scoreX, scoreY);
                return finalScore;
            } catch (Exception e) {
                throw e;
            }
        }
    }

    @Override
    public Expression assign(String var, Expression expression) {
        if (this == null || this.x == null || this.y == null) {
            System.out.print("GenralExpressions.Expression or part of it is null and can not be evaluated");
            return this;
        } else if (this.getVariables().contains(var)) {
            return this.specificAssign(var, expression);
        } else {
            return this;
        }
    }

    @Override
    public List<String> getVariables() {
        Set<String> variablesSet = new TreeSet<>();
        variablesSet.addAll(this.x.getVariables());
        variablesSet.addAll(this.y.getVariables());
        List<String> variablesList = new ArrayList<>(variablesSet);
        return variablesList;
    }

    @Override
    protected Expression specificAssign(String var, Expression expression) {
        Expression newX, newY, newExp;
        newX = this.x.assign(var, expression);
        newY = this.y.assign(var, expression);
        newExp = this.newExpressionBasedOnCurrent(newX, newY);
        return newExp;
    }

    @Override
    protected BaseExpression simplifyByKind() {
        Expression simplerX, simplerY;
        BaseExpression simplerExpression;
        if (BaseExpression.areBasic(this.x, this.y)) {
            simplerExpression = this;
        } else {
            simplerX = this.x.simplify();
            simplerY = this.y.simplify();
            simplerExpression = this.newBaseExpressionBasedOnCurrent(simplerX, simplerY);
            simplerExpression = simplerExpression.simplifyAdvanced();
        }
        return simplerExpression;
    }

    @Override
    public Expression clone() {
        return this.newExpressionBasedOnCurrent(this.x, this.y);
    }

}
