package UnaryExpressions;

import GenralExpressions.BaseExpression;
import GenralExpressions.Expression;
import GenralExpressions.Num;
import GenralExpressions.Var;

import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.TreeSet;
import java.util.ArrayList;


/**
 * The type Unary expression.
 */
public abstract class UnaryExpression extends BaseExpression {

    private Expression x;
    private Expression publicX;

    /**
     * Instantiates a new Unary expression.
     *
     * @param x the x
     */
    public UnaryExpression(Expression x) {
        this.x = x;
        this.publicX = x.clone();
    }

    /**
     * Instantiates a new Unary expression.
     *
     * @param x the x
     */
    public UnaryExpression(String x) {
        this.x = new Var(x);
        this.publicX = this.x.clone();
    }

    /**
     * Instantiates a new Unary expression.
     *
     * @param x the x
     */
    public UnaryExpression(double x) {
        this.x = new Num(x);
        this.publicX = this.x.clone();
    }

    /**
     * Gets x.
     *
     * @return the x
     */
    public Expression getX() {
        return this.publicX;
    }

    @Override
    public List<Var> getVars() {
        List<Var> varsList = new ArrayList<>();
        varsList.addAll(this.x.getVars());
        return varsList;
    }

    @Override
    public List<Num> getNums() {
        List<Num> numList = new ArrayList<>();
        numList.addAll(this.x.getNums());
        return numList;
    }

    @Override
    public List<Expression> getExpressions() {
        List<Expression> expressionList = new ArrayList<>();
        if (areBasic(this.x, this.x)) {
            expressionList.add(this);
        } else {
            expressionList.addAll(this.x.getExpressions());
        }
        return expressionList;
    }

    @Override
    public double evaluate() throws Exception {
        return this.evaluate(null);
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        double scoreX, finalScore;
        if (this == null || this.x == null) {
            throw new NullPointerException("GenralExpressions.Expression or part of it is null and can not be evaluated");
        } else {
            try {
                scoreX = this.x.evaluate(assignment);
                finalScore = this.arithmeticAction(scoreX, scoreX);
                return finalScore;
            } catch (Exception e) {
                throw e;
            }
        }
    }

    @Override
    public Expression assign(String var, Expression expression) {
        if (this == null || this.x == null) {
            System.out.print("GenralExpressions.Expression or part of it is null and can not be evaluated");
            return this;
        } else if (this.getVariables().contains(var)) {
            return this.specificAssign(var, expression);
        } else {
            return this;
        }
    }

    @Override
    protected Expression specificAssign(String var, Expression expression) {
        Expression newX, newExp;
        newX = this.x.assign(var, expression);
        newExp = this.newExpressionBasedOnCurrent(newX, newX);
        return newExp;
    }

    @Override
    public List<String> getVariables() {
        Set<String> variablesSet = new TreeSet<>();
        variablesSet.addAll(this.x.getVariables());
        List<String> variablesList = new ArrayList<>(variablesSet);
        return variablesList;
    }

    @Override
    public Expression simplifyByRule() {
        Expression simplerExpression = this.x;
        if (this.isInsignificantTrig(simplerExpression, 0, this.operatorName())) {
            simplerExpression = new Num(0);
        } else if (this.isInsignificantTrig(simplerExpression, 1, this.operatorName())) {
            simplerExpression = new Num(1);
        } else if (this.isInsignificantTrig(simplerExpression, -1, this.operatorName())) {
            simplerExpression = new Num(-1);
        } else {
            simplerExpression = this;
        }
        return simplerExpression;
    }

    /**
     * Is insignificant trig boolean.
     *
     * @param toCheck   the to check
     * @param parameter the parameter
     * @param trigFunc  the trig func
     * @return the boolean
     */
    private boolean isInsignificantTrig(Expression toCheck, double parameter, String trigFunc) {
        boolean isInsignificant = false;
        try {
            if (toCheck.isOnlyNum()) {
                double argument = toCheck.evaluate();
                if (trigFunc.equals(Sin.OPERATOR) && Math.sin(argument) == parameter) {
                    isInsignificant = true;
                } else if (trigFunc.equals(Cos.OPERATOR) && Math.cos(argument) == parameter) {
                    isInsignificant = true;
                }
            }
        } catch (Exception e) {
            printException(e);
            isInsignificant = false;
        } finally {
            return isInsignificant;
        }
    }

    @Override
    protected BaseExpression simplifyByKind() {
        Expression simplerX;
        BaseExpression simplerExpression;
        if (areBasic(this.x, this.x)) {
            simplerExpression = this;
        } else {
            simplerX = this.x.simplify();
            simplerExpression = this.newBaseExpressionBasedOnCurrent(simplerX, simplerX);
        }
        return simplerExpression;
    }

    @Override
    public Expression clone() {
        return this.newExpressionBasedOnCurrent(this.x, this.x);
    }

    @Override
    public BaseExpression simplifyAdvanced() {
        return this;
    }
}
