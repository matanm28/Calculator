package Tests;

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

import java.util.Map;
import java.util.TreeMap;

/**
 * The type Tests.Test.
 */
public class Test {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Expression p1 = new Plus("x", 5);
        Expression p2 = new Minus("y", 8);
        Expression p3 = new Minus(p1, p2);
        Expression p4 = new Plus(p1, 2);
        Expression m1 = new Mult(3, "z");
        Expression d1 = new Div("x", 3);
        Expression d2 = new Div(3, "d");
        Expression pow1 = new Pow(0, 0);
        Expression pow2 = new Pow("x", "y");
        Expression l1 = new Log(2, 8);
        Expression l2 = new Log(new Div("x", 2), 27);
        Expression l3 = new Log("x", "t");
        Expression l4 = new Log(0, 0);
        Expression s1 = new Sin(Math.PI / 2);
        Expression s2 = new Sin("s");
        Expression c1 = new Cos(0);
        Expression c2 = new Cos("c");
        Expression n1 = new Neg("n");
        Expression n2 = new Neg("z");
        Map<String, Double> map = new TreeMap<>();
        map.put("x", 6.0);
        map.put("y", 12.0);
        map.put("z", 4.0);
        map.put("d", 3.0);
        try {
            p1.printValueForTests(map, "p1");
            p2.printValueForTests(map, "p2");
            p3.printValueForTests(map, "p3");
            p4.printValueForTests(map, "p4");
            Expression e1 = p1.assign("x", p4);
            e1.printValueForTests(map, "e1");
            Expression e2 = p3.assign("x", p4);
            e2.printValueForTests(map, "e2");
            Expression e3 = e1.assign("x", p2);
            e3.printValueForTests(map, "e3");
            Expression e4 = p3.assign("x", p3);
            e4.printValueForTests(map, "e4");
            p3.assign("z", p3).printValueForTests(map, "false try");
            m1.printValueForTests(map, "m1");
            Expression e5 = m1.assign("z", p3);
            e5.printValueForTests(map, "e5");
            Expression e6 = m1.assign("z", new Minus(new Plus(m1, e1), new Mult(-7.5, p2)));
            e6.printValueForTests(map, "e6");
            d1.printValueForTests(map, "d1");
            d2.printValueForTests(map, "d2");
            Expression e7 = d2.assign("d", m1.assign("z", new Minus(new Plus(m1, e1), new Mult(7.5, p2))));
            e7.printValueForTests(map, "e7");
            pow1.printValueForTests(map, "pow1");
            Expression e8 = pow2.assign("y", p2);
            e8.printValueForTests(map, "e8");
            l1.printValueForTests(map, "l1");
            l2.printValueForTests(map, "l2");
            l3 = l3.assign("t", e8);
            l3.printValueForTests(map, "e9");
            l4.printValueForTests(map, "l4");
            s1.printValueForTests("s1");
            s2.assign("s", l3).printValueForTests(map, "s2");
            c1.printValueForTests("c1");
            c2.assign("c", e8);
            n1.assign("n", e6).printValueForTests(map, "n1");
            n2.printValueForTests(map, "n2");
            n2.printValueForTests("n2 - no map");
        } catch (Exception c) {
            System.out.println(c.getMessage());
            c.printStackTrace();
        }
    }
}
