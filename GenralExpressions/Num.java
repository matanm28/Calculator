package GenralExpressions;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.TreeSet;

/**
 * The type GenralExpressions.Num.
 */
public class Num implements Expression, Comparable<Num> {
    public static final String OPERATOR = "GenralExpressions.Num";

    private Double num;

    /**
     * Instantiates a new GenralExpressions.Num.
     */
    protected Num() {

    }

    /**
     * Instantiates a new GenralExpressions.Num.
     *
     * @param num the num
     */
    public Num(double num) {
        this.num = num;
    }

    @Override
    public List<String> getVariables() {
        return new ArrayList<>();
    }

    @Override
    public List<Var> getVars() {
        return new ArrayList<>();
    }

    @Override
    public int compareTo(Num other) {
        if (this.equals(other)) {
            return 0;
        }
        return 1;
    }    /**
     * Gets num.
     *
     * @return the num
     */
    public Double getNum() {
        return this.num;
    }

    /**
     * Equals boolean.
     *
     * @param other the other
     * @return the boolean
     */
    public boolean equals(Num other) {
        return this.num.equals(other.num);
    }

    @Override
    public Expression clone() {
        return new Num(this.num);
    }

    /**
     * Sets num.
     *
     * @param newNum the new num
     */
    protected void setNum(Double newNum) {
        this.num = newNum;
    }



    @Override
    public List<Num> getNums() {
        List<Num> numList = new ArrayList<>();
        numList.add(this);
        return numList;
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return this.evaluate();
    }

    @Override
    public double evaluate() throws Exception {
        if (false) {
            throw new Exception();
        }
        return this.num;
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return this;
    }

    @Override
    public String toString() {
        if (this.num.equals(BaseExpression.E.getNum())) {
            return BaseExpression.E.toString();
        } else if (this.num.equals(BaseExpression.PI.getNum())) {
            return BaseExpression.PI.toString();
        } else if (this.num.doubleValue() == this.num.intValue()) {
            return String.format("%d", this.num.intValue());
        } else if (this.num.toString().length() >= 5) {
            return String.format("%.3f", this.num);
        } else {
            return this.num.toString();
        }
    }

    @Override
    public String operatorName() {
        return OPERATOR;
    }


    @Override
    public Expression simplify() {
        return this;
    }

    @Override
    public Set<Var> getVarsAsSet() {
        return new TreeSet<>();
    }

    @Override
    public Set<Num> getNumsAsSet() {
        return new TreeSet<>(this.getNums());
    }

    @Override
    public void printExpression() {
        System.out.println(this.toString());
    }

    @Override
    public void printExpressionForTests(String str) {
        System.out.println(str + ": " + this.toString());
    }

    @Override
    public void printValueForTests(Map<String, Double> map, String name) {
        this.printValueForTests(name);
    }

    @Override
    public void printValueForTests(String name) {
        System.out.print(name + ": ");
        this.printValue();
    }

    @Override
    public void printValue(Map<String, Double> map) {
        this.printValue();
    }

    @Override
    public void printValue() {
        double score;
        try {
            score = this.evaluate();
            System.out.println(score);
        } catch (Exception c) {
            System.out.println(c.getMessage());
        }
    }

    @Override
    public Expression differentiate(String var) {
        return new Num(0);
    }

    @Override
    public boolean areSame(Expression other) {
        return this.toString().equals(other.toString());
    }


    @Override
    public boolean isOnlyNum() {
        return true;
    }

    @Override
    public List<Expression> getExpressions() {
        return new ArrayList<>();
    }

}
