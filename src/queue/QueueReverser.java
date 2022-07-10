package queue;

import java.util.Queue;
import java.util.Stack;

public class QueueReverser {

    // 10, 20, 30, 40, 50

    // 40, 50
    // 10, 20, 30

    // 40, 50, 30, 20, 10

    // 30, 20, 10, 40, 50
    public static void reverse(Queue<Integer> queue, int k) {
        var stack = new Stack<Integer>();

        for (int i = 0; i < k; k++)
            stack.push(queue.remove());

        while (!stack.isEmpty())
            queue.add(stack.pop());

        for (int i = 0; i < queue.size() - k; i++)
            queue.add(queue.remove());

    }

}
