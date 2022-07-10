package stack.rpn;

public enum BinaryOperator {

    POWER('^', 4, Assoc.RIGHT),
    MULTIPLY('*', 3, Assoc.LEFT),
    DIVISION('/', 3, Assoc.LEFT),
    SUM('+', 2, Assoc.LEFT),
    SUBTRACT('-', 2, Assoc.LEFT);

    public final int precedence;
    public final Assoc assoc;
    public final char symbol;

    BinaryOperator(char symbol, int precedence, Assoc assoc) {
        this.symbol = symbol;
        this.precedence = precedence;
        this.assoc = assoc;
    }

    public static boolean isBinaryOperator(String input) {
        return get(input) != null;
    }

    public static BinaryOperator get(String input) {
        BinaryOperator[] operators = values();
        for(BinaryOperator operator : operators) {
            if(input.charAt(0) == operator.symbol)
                return operator;
        }
        return null;
    }

    public boolean isPrecedent(BinaryOperator another) {
        return precedence > another.precedence ||
                (precedence == another.precedence && another.assoc == Assoc.LEFT);
    }

    @Override
    public String toString() {
        return String.valueOf(symbol);
    }

}

enum Assoc {
    RIGHT,
    LEFT
}