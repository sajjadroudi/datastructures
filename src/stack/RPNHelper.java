package stack;

import java.util.Stack;

public class RPNHelper {

    private static char[] binaryOperators = {'+', '-', '/', '*', '^'};
    private static char[] unaryOperators = { '?' };

    public void toRPN(String expression) {
        int operatorCount = 0;

        Stack<Character> stack = new Stack<>();
        char[] chars = expression.toCharArray();
        expression.split("operatorRegex");
        for(char ch : chars) {
            if(isOperator(ch)) {
                if(operatorCount > 0) {
                    System.out.print(stack.pop() + " ");
                    operatorCount--;
                }
                stack.push(ch);
                operatorCount++;
            } else if(ch == '(') {
                stack.push(ch);
            } else if(ch == ')') {
                while(true) {
                    if(stack.isEmpty()) break;
                    char item = stack.pop();
                    if(item == '(') break;
                    operatorCount--;
                    System.out.print(item + " ");
                }
            } else {
                System.out.print(ch + " ");
            }
        }

        while(!stack.isEmpty()) {
            System.out.println(stack.pop() + " ");
        }
    }

    public void calculate(String expression) {
        Stack<Double> stack = new Stack<>();
        String[] input = expression.split(" ");
        for(String item : input) {
            if(isOperator(item)) {
                double b = stack.pop();
                double a = stack.pop();
                double result = calculate(a, b, item);
                stack.push(result);
            } else {
                stack.push(Double.parseDouble(item));
            }
        }
        while(!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }

    private boolean isOperator(char ch) {
        for(char operator : binaryOperators) {
            if(ch == operator)
                return true;
        }
        return false;
    }

    private boolean isOperator(String str) {
        return isOperator(str.charAt(0));
    }

    private double calculate(double a, double b, char operator) {
        switch (operator) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                return a / b;
            case '^':
                return Math.pow(a, b);
            default:
                return 0;
        }
    }

    private double calculate(double a, double b, String operator) {
        return calculate(a, b, operator.charAt(0));
    }

}
