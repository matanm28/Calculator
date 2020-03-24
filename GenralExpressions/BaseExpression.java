package GenralExpressions;

import BinaryExpressions.Div;
import BinaryExpressions.Ln;
import BinaryExpressions.Log;
import BinaryExpressions.Minus;
import BinaryExpressions.Mult;
import BinaryExpressions.Plus;
import BinaryExpressions.Pow;
import UnaryExpressions.Cos;
import UnaryExpressions.Neg;
import UnaryExpressions.Sin;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.TreeMap;


/**
 * The type Base expression.
 */
public abstract class BaseExpression implements Expression {

    /**
     * The constant E.
     */
    public static final Const E = new Const("e");
    /**
     * The constant PI.
     */
    public static final Const PI = new Const("pi");
    private static final double EQUALIZATION_ASSIGNMENT = 2;

    @Override
    public Set<Num> getNumsAsSet() {
        Set<Num> numsSet = new TreeSet<>();
        numsSet.addAll(this.getNums());
        return numsSet;
    }

    /**
     * Is insignificant boolean.
     *
     * @param toCheck   the to check
     * @param parameter the parameter
     * @return the boolean
     */
    public boolean isInsignificant(Expression toCheck, double parameter) {
        try {
            if (toCheck.isOnlyNum() && toCheck.evaluate() == parameter) {
                return true;
            }
        } catch (Exception e) {
            printException(e);
            return false;
        }
        return false;
    }

    /**
     * Print exception.
     *
     * @param e the e
     */
    protected static void printException(Exception e) {
        System.out.println(e.getMessage());
        e.printStackTrace();
    }

    /**
     * Arithmetic action double.
     *
     * @param x the x
     * @param y the y
     * @return the double
     * @throws ArithmeticException the arithmetic exception
     */
    protected abstract double arithmeticAction(double x, double y) throws ArithmeticException;

    /**
     * New expression based on current expression.
     *
     * @param x the x
     * @param y the y
     * @return the expression
     */
    protected Expression newExpressionBasedOnCurrent(Expression x, Expression y) {
        return this.newBaseExpressionBasedOnCurrent(x, y);
    }

    /**
     * New base expression based on current base expression.
     *
     * @param x the x
     * @param y the y
     * @return the base expression
     */
    protected BaseExpression newBaseExpressionBasedOnCurrent(Expression x, Expression y) {
        BaseExpression newBaseExpression;
        if (this.operatorName().equals(Plus.OPERATOR)) {
            newBaseExpression = new Plus(x, y);
        } else if (this.operatorName().equals(Minus.OPERATOR)) {
            newBaseExpression = new Minus(x, y);
        } else if (this.operatorName().equals(Mult.OPERATOR)) {
            newBaseExpression = new Mult(x, y);
        } else if (this.operatorName().equals(Div.OPERATOR)) {
            newBaseExpression = new Div(x, y);
        } else if (this.operatorName().equals(Ln.OPERATOR)) {
            newBaseExpression = new Ln(y);
        } else if (this.operatorName().equals(Log.OPERATOR)) {
            newBaseExpression = new Log(x, y);
        } else if (this.operatorName().equals(Pow.OPERATOR)) {
            newBaseExpression = new Pow(x, y);
        } else if (this.operatorName().equals(Cos.OPERATOR)) {
            newBaseExpression = new Cos(x);
        } else if (this.operatorName().equals(Sin.OPERATOR)) {
            newBaseExpression = new Sin(x);
        } else if (this.operatorName().equals(Neg.OPERATOR)) {
            newBaseExpression = new Neg(x);
        } else {
            newBaseExpression = this;
        }
        return newBaseExpression;
    }

    /**
     * Specific assign expression.
     *
     * @param var        the var
     * @param expression the expression
     * @return the expression
     */
    protected abstract Expression specificAssign(String var, Expression expression);

    /**
     * Simplify advanced base expression.
     *
     * @return the base expression
     */
    public abstract BaseExpression simplifyAdvanced();

    /**
     * Convert to expression expression.
     *
     * @param obj the obj
     * @return the expression
     */
    protected static Expression convertToExpression(Object obj) {
        Expression exprObj = null;
        if (obj == null) {
            exprObj = new Var("@");
        } else if (obj instanceof Expression) {
            return (Expression) obj;
        } else if (obj instanceof String) {
            exprObj = new Var((String) obj);
        } else if (obj instanceof Double) {
            exprObj = new Num((double) obj);
        } else if (obj instanceof Integer) {
            exprObj = new Num((int) obj);
        }
        return exprObj;
    }

    /**
     * Are basic boolean.
     *
     * @param x the x
     * @param y the y
     * @return the boolean
     */
    protected static boolean areBasic(Expression x, Expression y) {
        String strX, strY;
        strX = x.operatorName();
        strY = y.operatorName();
        return (strX.equals(Var.OPERATOR) || strX.equals(Num.OPERATOR))
                && ((strY.equals(Var.OPERATOR) || strY.equals(Num.OPERATOR)));
    }

    /**
     * Check operators match boolean.
     *
     * @param e1       the e 1
     * @param e2       the e 2
     * @param operator the operator
     * @return the boolean
     */
    public static boolean checkOperatorsMatch(Expression e1, Expression e2, String operator) {
        return (e1.operatorName().equals(operator) && e1.operatorName().equals(e2.operatorName()));
    }

    /**
     * Check operators match boolean.
     *
     * @param e1       the e 1
     * @param operator the operator
     * @return the boolean
     */
    public static boolean checkOperatorsMatch(Expression e1, String operator) {
        return (e1.operatorName().equals(operator));
    }

    /**
     * Sum up list double.
     *
     * @param numList the num list
     * @return the double
     */
    protected static double sumUpList(List<Num> numList) {
        double score = 0;
        for (Num num : numList) {
            score += num.getNum();
        }
        return score;
    }

    /**
     * Sum up list mult double.
     *
     * @param numList the num list
     * @return the double
     */
    protected static double sumUpListMult(List<Num> numList) {
        double score = 1;
        for (Num num : numList) {
            score *= num.getNum();
        }
        return score;
    }

    /**
     * Sum up list double.
     *
     * @param numList the num list
     * @return the double
     */
    protected static double sumUpListNeg(List<Num> numList) {
        double score = 0;
        for (Num num : numList) {
            score -= num.getNum();
        }
        return score;
    }

    @Override
    public void printExpression() {
        String expressionToPrint;
        expressionToPrint = this.toString();
        System.out.println(expressionToPrint);
    }

    @Override
    public abstract Expression clone();

    @Override
    public void printExpressionForTests(String str) {
        String expressionToPrint;
        expressionToPrint = this.toString();
        System.out.println(str + ": " + expressionToPrint);
    }

    @Override
    public void printValueForTests(String name) {
        this.printValueForTests(null, name);
    }

    @Override
    public void printValueForTests(Map<String, Double> map, String name) {
        double score;
        try {
            score = this.evaluate(map);
            System.out.println(name + "(" + this.operatorName() + ")" + ": " + this.toString() + " ===> " + score);
        } catch (Exception c) {
            System.out.println(name + "(" + this.operatorName() + ")" + ": " + " ===> " + c.getMessage());
        }
    }

    @Override
    public void printValue() {
        this.printValue(null);

    }

    @Override
    public void printValue(Map<String, Double> map) {
        double score;
        try {
            score = this.evaluate(map);
            System.out.println(score);
        } catch (Exception c) {
            System.out.println(c.getMessage());
        }
    }

    @Override
    public Expression differentiate(String var) {
        Expression derivative;
        if (this.getVariables().contains(var)) {
            derivative = this.differentiateByRule(var);
            return derivative;
        } else {
            derivative = new Num(0);
        }
        return derivative;
    }

    /**
     * Differentiate by rule expression.
     *
     * @param var the var
     * @return the expression
     */
    public abstract Expression differentiateByRule(String var);

    @Override
    public Expression simplify() {
        BaseExpression tempExpression;
        Expression simplerExpression = this;
        if (this.isOnlyNum()) {
            double score;
            try {
                score = this.evaluate();
            } catch (Exception e) {
                printException(e);
                simplerExpression = this;
                return simplerExpression;
            }
            if (this.operatorName().equals(Log.OPERATOR) || this.operatorName().equals(Ln.OPERATOR)) {
                if (score % 1 != 0) {
                    simplerExpression = this;
                }
            } else {
                simplerExpression = new Num(score);
            }
        } else {
            tempExpression = this.simplifyByKind();
            simplerExpression = tempExpression.simplifyByRule();
        }
        return simplerExpression;
    }

    /**
     * Simplify by rule expression.
     *
     * @return the expression
     */
    protected abstract Expression simplifyByRule();

    @Override
    public boolean isOnlyNum() {
        List<Var> varList = this.getVars();
        List<Num> numList = this.getNums();
        return (varList.isEmpty() && numList.size() > 0);
    }

    /**
     * Simplify by kind base expression.
     *
     * @return the base expression
     */
    protected abstract BaseExpression simplifyByKind();

    @Override
    public boolean areSame(Expression other) {
        Map<String, Double> assignment;
        Set<Var> varList1 = this.getVarsAsSet();
        Set<Var> varList2 = other.getVarsAsSet();

        if (varList1.equals(varList2)) {
            assignment = createAssignment(varList1);
            try {
                return (Math.abs(this.evaluate(assignment) - other.evaluate(assignment)) == 0);
            } catch (Exception e) {
                //printException(e);
                return false;
            }
        }
        return false;
    }

    @Override
    public Set<Var> getVarsAsSet() {
        Set<Var> varsSet = new TreeSet<>();
        varsSet.addAll(this.getVars());
        return varsSet;
    }

    /**
     * Create assignment map.
     *
     * @param variables the variables
     * @return the map
     */
    protected static Map<String, Double> createAssignment(Iterable<Var> variables) {
        double temp = EQUALIZATION_ASSIGNMENT;
        Map<String, Double> assignment = new TreeMap<>();
        for (Var var : variables) {
            assignment.put(var.getVarName(), temp);
            temp++;
        }
        return assignment;
    }


}
