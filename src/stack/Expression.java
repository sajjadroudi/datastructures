package stack;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Expression {

    private String expression;
    final List<Character> leftBrackets;
    final List<Character> rightBrackets;

    public Expression(String expression) {
        if(expression == null)
            throw new IllegalArgumentException();
        this.expression = expression;
        leftBrackets = Arrays.asList('(', '[', '<', '{');
        rightBrackets = Arrays.asList(')', ']', '>', '}');
    }

    public boolean isBalanced() {
        Stack<Character> stack = new Stack<>();
        char[] chars = expression.toCharArray();
        for(var ch : chars) {
            if(isLeftBracket(ch))
                stack.push(ch);
            else if(isRightBracket(ch)) {
                if(stack.empty()) return false;

                var top = stack.pop();
                if(!matchBrackets(top, ch)) return false;
            }
        }
        return true;
    }

    private boolean isLeftBracket(char ch) {
        return leftBrackets.contains(ch);
    }

    private boolean isRightBracket(char ch) {
        return rightBrackets.contains(ch);
    }

    private boolean matchBrackets(char left, char right) {
        return leftBrackets.indexOf(left) == rightBrackets.indexOf(right);
    }

}
