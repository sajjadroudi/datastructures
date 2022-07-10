package stack;

import java.util.Arrays;

import stack.rpn.Helper;
import stack.rpn.RPN;

public class Test {

    public static void main(String[] args) {
        /*String expression = "(a+b)^31*2/7";
        String[] output = expression.split("[+*^/?-]");
        System.out.println(Arrays.toString(output));*/
        Helper helper = new Helper();
        /*helper.toRPN("(a+b)^3*2/7");
        helper.toRPN("1-(-2+3)");
        System.out.println();
        helper.calculate("5 1 + 3 ^ 2 * 7 /");*/

//        RPN rpn = helper.toRPN("( ( A + B ) * C ) ^ ( D - E )");
        RPN rpn = helper.toRPN("( ( A + B ) * C ) ^ ( D - E )");
        System.out.println(rpn);
    }

}
