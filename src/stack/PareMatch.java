package stack;

import java.util.Stack;

public class PareMatch {

    private String expression;

    public PareMatch(String expression) {
        this.expression = expression;
    }

    public void match() {
        Stack<Integer> stack = new Stack<>();
        char[] chars = expression.toCharArray();
        for(int i = 0; i < chars.length; i++) {
            if(chars[i] == '(') {
                stack.push(i);
            } else if(chars[i] == ')') {
                System.out.print("(" + stack.pop() + "," + i + ") ");
            }
        }
    }

}
