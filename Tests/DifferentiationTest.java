package Tests;

import BinaryExpressions.Div;
import BinaryExpressions.Log;
import BinaryExpressions.Minus;
import BinaryExpressions.Mult;
import BinaryExpressions.Plus;
import BinaryExpressions.Pow;
import GenralExpressions.Expression;
import UnaryExpressions.Neg;
import UnaryExpressions.Sin;

/**
 * The type Differentiation test.
 */
public class DifferentiationTest {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Expression e1, e2, e3, e4, e5, e6, e7, e8, e9, e10;
        Expression d1, d2, d3, d4, d5, d6, d7, d8, d9,d10;
        Expression s1, s2, s3, s4, s5, s6, s7, s8, s9, s10;
        e1 = new Pow("x", 4);
        e2 = new Plus("y", 5);
        e3 = new Minus(e1, e2);
        e4 = new Log("y", 5);
        e5 = new Log("y", e1);
        e6 = new Div(new Mult(2, "x"), new Pow(new Plus("x", 5), 2));
        e7 = new Sin(e3);
        e8 = new Neg(e4);
        e9 = new Minus(new Plus(e6, 5), new Plus(new Plus(e6, 3), 2));
        e10 = new Div(new Plus(new Mult(3, e3), -1), new Minus(new Mult(e3, 3), 1));
        d1 = e1.differentiate("x");
        d2 = e2.differentiate("y");
        d3 = e3.differentiate("x");
        d4 = e4.differentiate("y");
        d5 = e5.differentiate("y");
        d6 = e6.differentiate("x");
        d7 = e7.differentiate("y");
        d8 = e8.differentiate("y");
        d9 = e9.differentiate("x");
        d10 = e10.differentiate("x");
        s1 = d1.simplify();
        s2 = d2.simplify();
        s3 = d3.simplify();
        s4 = d4.simplify();
        s5 = d5.simplify();
        s6 = d6.simplify();
        s7 = d7.simplify();
        s8 = d8.simplify();
        s9 = e9.simplify();
        s10 = e10.simplify();
        e1.printExpressionForTests("e1");
        d1.printExpressionForTests("d1");
        s1.printExpressionForTests("s1");
        e2.printExpressionForTests("e2");
        d2.printExpressionForTests("d2");
        s2.printExpressionForTests("s2");
        e3.printExpressionForTests("e3");
        d3.printExpressionForTests("d3");
        s3.printExpressionForTests("s3");
        e4.printExpressionForTests("e4");
        d4.printExpressionForTests("d4");
        s4.printExpressionForTests("s4");
        e5.printExpressionForTests("e5");
        d5.printExpressionForTests("d5");
        s5.printExpressionForTests("s5");
        e6.printExpressionForTests("e6");
        d6.printExpressionForTests("d6");
        s6.printExpressionForTests("s6");
        e7.printExpressionForTests("e7");
        d7.printExpressionForTests("d7");
        s7.printExpressionForTests("s7");
        e8.printExpressionForTests("e8");
        d8.printExpressionForTests("d8");
        s8.printExpressionForTests("s8");
        e2.differentiate("r").printExpressionForTests("fail-1");
        e8.differentiate("r").printExpressionForTests("fail-2");
        e9.printExpressionForTests("e9");
        d9.printExpressionForTests("d9");
        s9.printExpressionForTests("s9");
        e9.printExpressionForTests("e10");
        d9.printExpressionForTests("d10");
        s9.printExpressionForTests("s10");

    }
}
