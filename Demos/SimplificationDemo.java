package Demos;

import BinaryExpressions.Div;
import BinaryExpressions.Ln;
import BinaryExpressions.Log;
import BinaryExpressions.Minus;
import BinaryExpressions.Mult;
import BinaryExpressions.Plus;
import BinaryExpressions.Pow;
import GenralExpressions.Const;
import GenralExpressions.Expression;
import UnaryExpressions.Cos;

/**
 * The type Simplification demo.
 */
public class SimplificationDemo {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Expression numBefore, logToLn, usingPi, areSame, areDouble, areSameDiv, zeroDiv, logBases, multToPower;
        Expression powerRule1, powerRule2, associative, assExp, logBasesMinus;
        numBefore = new Mult("x", 4);
        logToLn = new Log(new Mult(1, "e"), "x");
        usingPi = new Cos(new Mult(2, new Const("pi")));
        areSame = new Minus(new Plus("x", 5), new Plus(5, "x"));
        areDouble = new Plus(new Mult(17, "x"), new Mult(17, "x"));
        areSameDiv = new Div(new Plus(5, new Mult(2, "x")), new Plus(new Plus(3, "x"), new Plus("x", 2)));
        zeroDiv = new Div(0, areSame);
        logBases = new Plus(new Log("x", 4), new Log("x", "y"));
        logBasesMinus = new Minus(new Log("x", 4), new Log("x", "y"));
        multToPower = new Mult("x", "x");
        powerRule1 = new Pow(new Pow("x", "y"), "z");
        powerRule2 = new Mult(new Pow("x", "y"), new Pow("x", "z"));
        associative = new Plus(new Plus(new Mult("x", 2), new Mult(7, "y")),
                new Plus(new Mult(2, "y"), new Mult(5, "x")));
        assExp = new Plus(new Plus(new Ln(new Pow("x", 2)), "y"), new Plus(new Mult(3, new Ln(new Pow("x", 2))), "y"));
        System.out.println("A few examples of my bonus part (see readme file for more info) : ");
        System.out.println("Nums come before vars in multifaction:");
        printForDemo(numBefore);
        System.out.println("If a BinaryExpressions.Log's base is 'e' we change it to BinaryExpressions.Ln: ");
        printForDemo(logToLn);
        System.out.println("We print 'e' and 'pi' as symbols instead of numbers");
        printForDemo(usingPi);
        String s = "we cancel out (and add up also) expressions even if their strings don't match but are equal:";
        System.out.println(s);
        printForDemo(areSame);
        printForDemo(areDouble);
        printForDemo(areSameDiv);
        System.out.println("if 0 is the numerator we return 0");
        printForDemo(zeroDiv);
        System.out.println("BinaryExpressions.Log rules for adding and subtracting");
        printForDemo(logBases);
        printForDemo(logBasesMinus);
        System.out.println("multiplying the same expressions gives a power expression ");
        printForDemo(multToPower);
        printForDemo(new Mult(areDouble, areDouble));
        System.out.println("power rules");
        printForDemo(powerRule1);
        printForDemo(powerRule2);
        System.out.println("associative and commutative properties");
        printForDemo(associative);
        printForDemo(assExp);

    }

    /**
     * Print for demo.
     *
     * @param expression the expression
     */
    private static void printForDemo(Expression expression) {
        System.out.println(expression.toString() + " ---> " + expression.simplify().toString());
    }
}
