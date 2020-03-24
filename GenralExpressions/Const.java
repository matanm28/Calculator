package GenralExpressions;

/**
 * The type GenralExpressions.Const.
 */
public class Const extends Num {

    private static final double E = Math.E;
    private static final double PI = Math.PI;
    private static final String E_OPERATOR = "e";
    private static final String PI_OPERATOR = "\u03c0";


    private String parName;

    /**
     * Instantiates a new GenralExpressions.Const.
     *
     * @param parameter the parameter
     */
    public Const(String parameter) {
        if (parameter.equals("e")) {
            this.setNum(E);
            this.parName = E_OPERATOR;
        } else if (parameter.equals("pi")) {
            this.setNum(PI);
            this.parName = PI_OPERATOR;
        } else {
            this.setNum(null);
            this.parName = null;
        }
    }

    @Override
    public String toString() {
        return this.parName;
    }
}
