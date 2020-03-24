package GenralExpressions;

import BinaryExpressions.Mult;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.TreeSet;

/**
 * The type GenralExpressions.Var.
 */
public class Var implements Expression, Comparable<Var> {

    public static final String OPERATOR = "GenralExpressions.Var";

    private String varName;

    /**
     * Instantiates a new GenralExpressions.Var.
     *
     * @param varName the var name
     */
    public Var(String varName) {
        if (varName.isEmpty()) {
            this.varName = "x";
        } else if (varName == "@") {
            this.varName = "X1";
        } else if (varName.matches("[a-z]")) {
            if (varName.length() == 1) {
                this.varName = varName;
            } else {
                this.varName = varName.substring(0, 1);
            }
        }
    }

    @Override
    public List<String> getVariables() {
        List<String> varList = new ArrayList<>();
        varList.add(this.varName);
        return varList;
    }

    @Override
    public List<Num> getNums() {
        return new ArrayList<>();
    }

    @Override
    public Set<Var> getVarsAsSet() {
        return new TreeSet<>(this.getVars());
    }

    @Override
    public List<Var> getVars() {
        List<Var> varList = new ArrayList<>();
        varList.add(this);
        return varList;
    }

    @Override
    public Set<Num> getNumsAsSet() {
        return new TreeSet<>();
    }

    @Override
    public boolean isOnlyNum() {
        return false;
    }

    @Override
    public List<Expression> getExpressions() {
        List<Expression> expressionList = new ArrayList<>();
        Expression exp = new Mult(1, this);
        expressionList.add(exp);
        return expressionList;
    }

    @Override
    public Var clone() {
        return new Var(this.varName);
    }

    @Override
    public int compareTo(Var other) {
        if (this.equals(other)) {
            return 0;
        }
        return 1;
    }

    /**
     * Equals boolean.
     *
     * @param other the other
     * @return the boolean
     */
    public boolean equals(Var other) {
        return this.varName.equals(other.varName);
    }

    @Override
    public double evaluate() throws Exception {
        throw new Exception("No variables map given");
    }

    @Override
    public Expression assign(String var, Expression expression) {
        if (this.varName.equals(var)) {
            return expression;
        } else {
            return this;
        }
    }

    @Override
    public Expression differentiate(String var) {
        if (this.varName.equals(var)) {
            Expression derivative = new Num(1);
            return derivative;
        } else {
           //return this;// - fix.
            return new Num(0);
        }
    }

    @Override
    public Expression simplify() {
        return this;
    }

    @Override
    public void printExpression() {
        System.out.println(this.varName);
    }

    @Override
    public void printExpressionForTests(String str) {
        System.out.println(str + ": " + this.varName);
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
    public double evaluate(Map<String, Double> assignment) throws Exception {
        if (assignment != null) {
            if (assignment.containsKey(this.varName)) {
                return assignment.get(this.varName);
            } else {
                throw new Exception("Variable has no assignment");
            }
        } else {
            throw new NullPointerException("No variables map given");
        }
    }

    @Override
    public void printValueForTests(String name) {
        this.printValueForTests(null, name);
    }

    @Override
    public void printValueForTests(Map<String, Double> map, String name) {
        System.out.print(name + "(" + this.operatorName() + ")" + ": " + this.toString() + " ===> ");
        this.printValue(map);
    }

    @Override
    public String operatorName() {
        return OPERATOR;
    }

    @Override
    public String toString() {
        return this.getVarName();
    }

    /**
     * Gets var name.
     *
     * @return the var name
     */
    public String getVarName() {
        return this.varName;
    }

    @Override
    public boolean areSame(Expression other) {
        return this.toString().equals(other.toString());
    }

}
