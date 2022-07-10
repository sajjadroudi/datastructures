package stack.rpn;

import java.util.Stack;

public class Helper {

    public RPN toRPN(String expression) {
        Stack<Object> stack = new Stack<>();
        Stack<Object> outputStack = new Stack<>();

        String[] input = expression.split(" ");

        for (int i = 0; i < input.length; i++) {
            String item = input[i];
            if (item.equals("(")) {
                stack.push(item);
            } else if (item.equals(")")) {
                while(!stack.isEmpty()) {
                    var element = stack.pop();
                    if(element.equals("(")) break;
                    outputStack.push(element);
                }
            } else if (i > 0 && isUnaryOperator(item, input[i - 1])) {
                stack.push("?");
            } else if (BinaryOperator.isBinaryOperator(item)) {

                var operator = BinaryOperator.get(item);
                while(!stack.isEmpty()) {
                    var prev = stack.peek();

                    if(prev instanceof Function) {
                        outputStack.push(stack.pop());
                    } else if(prev instanceof BinaryOperator) {
                        var prevOperator = (BinaryOperator) prev;
                        if(prevOperator.isPrecedent(operator))
                            outputStack.push(stack.pop());
                    } else if(prev.equals("?")) {
                        outputStack.push(stack.pop());
                    } else break;

                    if(stack.isEmpty() || prev == stack.peek()) break;
                }

                stack.push(operator);
            } else if (Function.isFunction(item)) {
                var function = Function.get(item);
                stack.push(function);
            } else {
                outputStack.push(item);
            }
        }

        while (!stack.isEmpty()) {
            outputStack.push(stack.pop());
        }

        return new RPN(outputStack);
    }

    private boolean isUnaryOperator(String input, String prev) {
        return input.equals("-") && prev.equals("(");
    }


}
