package Demos;

import BinaryExpressions.Div;
import BinaryExpressions.Log;
import BinaryExpressions.Minus;
import BinaryExpressions.Mult;
import BinaryExpressions.Plus;
import BinaryExpressions.Pow;
import GenralExpressions.Expression;
import UnaryExpressions.Cos;
import UnaryExpressions.Neg;
import UnaryExpressions.Sin;

/**
 * The type Simplification demo 2.
 */
public class SimplificationDemo2 {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Expression e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15;
        Expression e16, e17, e18, e19, e20, e21, e22, e23, e24, e25, e26, d1, div1, e27;
        e1 = new Pow("x", "y");
        e2 = new Pow(e1, "z");
        e3 = e2.simplify();
        e4 = new Pow("x", "z");
        e5 = new Mult(new Mult(e1, e4), 2);
        e6 = e5.simplify();
        e7 = new Plus(new Plus(new Mult(4, "x"), new Mult(5, "y")), new Plus(new Mult("y", 2), new Mult(3, "y")));
        e8 = e7.simplify();
        e9 = new Plus(new Plus(new Plus(6, new Mult(5, new Mult("x", "y"))),
                new Plus(5, new Mult(3, new Mult("x", "y")))), e7);
        e10 = e9.simplify();
        e11 = new Plus(new Cos("x"), new Mult(2, new Cos("x")));
        e12 = e11.simplify();
        e13 = new Cos(e7);
        e14 = e13.simplify();
        e15 = new Mult(new Pow("x", "y"), new Pow("n", "y"));
        e16 = e15.simplify();
        e17 = new Minus(new Log("x", new Mult("y", 5)), new Log("x", 4));
        e18 = e17.simplify();
        e19 = new Neg(new Neg(e15));
        e20 = e19.simplify();
        e21 = new Log(2, 8);
        e22 = new Log("e", "x");
        e23 = new Minus(new Minus(new Mult(4, "x"), new Mult(5, "y")), new Plus(new Mult("y", 2), new Mult(3, "y")));
        e24 = e23.simplify();
        e25 = new Div(new Div(e1, e2), new Div(e3, e4));
        e26 = e25.simplify();
        e27 = new Mult(new Mult(4, "y"), 5);
        d1 = new Plus(new Plus(new Cos("x"), new Log("e", "x")), new Plus(new Div(1, "x"), new Sin(new Pow("x", 2))));
        div1 = new Div(new Plus(5, new Mult(2, "x")), new Plus(new Plus(2, "x"), new Plus("x", 3)));
        System.out.println("(x^y)^z == x^(y*z)");
        e1.printExpression();
        e2.printExpression();
        e3.printExpression();
        System.out.println("x^y * x^z == x^(y+z)");
        e4.printExpression();
        e5.printExpression();
        e6.printExpression();
        e7.printExpression();
        e8.printExpression();
        e9.printExpression();
        e10.printExpression();
        e11.printExpression();
        e12.printExpression();
        e13.printExpression();
        e14.printExpression();
        e15.printExpression();
        e16.printExpression();
        e17.printExpression();
        e18.printExpression();
        e19.printExpression();
        e20.printExpression();
        e21.printExpression();
        e21.simplify().printExpression();
        e22.printExpression();
        e22.differentiate("x").printExpression();
        e22.differentiate("x").simplify().printExpression();
        e23.printExpression();
        e24.printExpression();
        e25.printExpression();
        e26.printExpression();
        div1.printExpression();
        div1.simplify().printExpression();
        e27.printExpression();
        e27.simplify().printExpression();
    }
}
