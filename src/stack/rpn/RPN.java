package stack.rpn;

import java.util.Stack;

public class RPN {

    private Object[] array;

    public RPN(Stack<Object> stack) {
        array = new Object[stack.size()];
        for(int i = array.length - 1; i >= 0; i--) {
            array[i] = stack.pop();
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for(Object obj : array) {
            builder.append(obj).append(" ");
        }
        return builder.toString();
    }
}
