package GenralExpressions;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The interface GenralExpressions.Expression.
 */
public interface Expression {
    /**
     * Gets variables.
     *
     * @return the variables
     */
// Returns a list of the variables in the expression.
    List<String> getVariables();

    /**
     * Gets vars.
     *
     * @return the vars
     */
    List<Var> getVars();

    /**
     * Gets nums.
     *
     * @return the nums
     */
    List<Num> getNums();

    /**
     * Gets vars as set.
     *
     * @return the vars as set
     */
    Set<Var> getVarsAsSet();

    /**
     * Gets nums as set.
     *
     * @return the nums as set
     */
    Set<Num> getNumsAsSet();

    /**
     * Is only num boolean.
     *
     * @return the boolean
     */
    boolean isOnlyNum();

    /**
     * Evaluate double.
     *
     * @param assignment the assignment
     * @return the double
     * @throws Exception the exception
     */
// Evaluate the expression using the variable values provided
    // in the assignment, and return the result.  If the expression
    // contains a variable which is not in the assignment, an exception
    // is thrown.
    double evaluate(Map<String, Double> assignment) throws Exception;

    /**
     * Evaluate double.
     *
     * @return the double
     * @throws Exception the exception
     */
// A convenience method. Like the `evaluate(assignment)` method above,
    // but uses an empty assignment.
    double evaluate() throws Exception;

    /**
     * Operator name string.
     *
     * @return the string
     */
    String operatorName();

    /**
     * Assign expression.
     *
     * @param var        the var
     * @param expression the expression
     * @return the expression
     */
// Returns a new expression in which all occurrences of the variable
    // var are replaced with the provided expression (Does not modify the
    // current expression).
    Expression assign(String var, Expression expression);
    //GenralExpressions.Expression specificAssign(String var, GenralExpressions.Expression expression);


    /**
     * Print expression.
     */
    void printExpression();

    /**
     * Print expression for tests.
     *
     * @param str the str
     */
    void printExpressionForTests(String str);

    /**
     * Print value for tests.
     *
     * @param map  the map
     * @param name the name
     */
    void printValueForTests(Map<String, Double> map, String name);

    /**
     * Print value for tests.
     *
     * @param name the name
     */
    void printValueForTests(String name);

    /**
     * Print value.
     *
     * @param map the map
     */
    void printValue(Map<String, Double> map);

    /**
     * Print value.
     */
    void printValue();

    /**
     * Differentiate expression.
     *
     * @param var the var
     * @return the expression
     */
// Returns the expression tree resulting from differentiating
    // the current expression relative to variable `var`.
    Expression differentiate(String var);
    //GenralExpressions.Expression differentiateByRule(String var);

    /**
     * Simplify expression.
     *
     * @return the expression
     */
// Returned a simplified version of the current expression.
    Expression simplify();
    //GenralExpressions.Expression simplifyForBonus();

    /**
     * Are same boolean.
     *
     * @param other the other
     * @return the boolean
     */
    boolean areSame(Expression other);

    /**
     * Creates and returns a copy of this object.  The precise meaning
     * of "copy" may depend on the class of the object.
     * @return     a clone of this instance.
     */
    Expression clone();

    @Override
    String toString();

    /**
     * gets a list of all the base expressions from a given expression.
     *
     * @return the list of all the expressions.
     */
    List<Expression> getExpressions();

}
