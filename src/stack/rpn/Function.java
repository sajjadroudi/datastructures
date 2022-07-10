package stack.rpn;

public enum Function {

    SIN("sin"),
    COS("cos"),
    TAN("tan"),
    COT("cot"),
    ATAN("atan"),
    ASIN("asin"),
    ACOS("acos");

    public final String symbol;

    Function(String symbol) {
        this.symbol = symbol;
    }

    public static boolean isFunction(String input) {
        return get(input) != null;
    }

    public static Function get(String input) {
        Function[] functions = values();
        for(Function function : functions) {
            if(input.equals(function.symbol))
                return function;
        }
        return null;
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}