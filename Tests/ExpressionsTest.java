package Tests;

import BinaryExpressions.Div;
import BinaryExpressions.Log;
import BinaryExpressions.Minus;
import BinaryExpressions.Mult;
import BinaryExpressions.Plus;
import BinaryExpressions.Pow;
import GenralExpressions.Expression;
import UnaryExpressions.Cos;
import UnaryExpressions.Sin;

import java.util.Map;
import java.util.TreeMap;

/**
 * The type GenralExpressions test.
 */
public class ExpressionsTest {
    /**
     * Main.
     *
     * @param args the args
     */
    public static void main(String[] args) {
        Expression e1, d1, s1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11;
        Expression d2, d3, d4, d5, d6, d7, d8, d9, d10;
        Expression s2, s3, s4, s5, s6, s7, s8, s9, s10;
        Map<String, Double> map = new TreeMap<>();

        e2 = new Pow(new Plus(new Plus("x", "y"), new Plus("z", "w")), 4);
        e3 = new Plus(new Plus(new Mult(2, "x"), new Sin(new Mult(2, "y"))), new Pow("e", "x"));
        e4 = new Minus(0, new Div(new Mult(1, "x"), 1));
        e5 = new Plus(new Plus(new Cos("x"), new Log("e", "x")), new Plus(new Div(1, "x"), new Sin(new Pow("x", 2))));
        e6 = new Mult(20, new Pow("x", 5));
        e7 = new Mult(3, new Pow("e", "x"));
        e8 = new Minus("x", "x");
        e9 = new Cos(new Pow("x", 2));
        e10 = new Mult(new Mult(new Mult(new Mult(e6, e7), new Mult(e8, e9)), new Mult(e6, e7)), new Mult(e9, e8));
        e11 = new Sin(new Pow("e", new Div(new Sin("x"), new Cos("x"))));

        map.put("x", 2.0);
        map.put("y", 0.25);
        d2 = e2.differentiate("x");
        s2 = d2.simplify();
        e2.printExpression();
        d2.printExpression();
        s2.printExpression();
        d3 = e3.differentiate("x");
        s3 = d3.simplify();
        e3.printExpression();
        d3.printExpression();
        s3.printExpression();
        s4 = e4.simplify();
        s4.printExpression();
        d5 = e5.differentiate("x");
        s5 = d5.simplify();
        e5.printExpression();
        d5.printExpression();
        s5.printExpression();
        e10.printExpression();
        s10 = e10.simplify();
        s10.printExpression();
        e11.printExpression();
        d9 = e11.differentiate("x");
        d9.printExpression();

    }
}
