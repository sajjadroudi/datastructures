package queue;

import java.util.ArrayDeque;
import java.util.Queue;

public class StackWithTwoQueues {

    private Queue<Integer> queue1;
    private Queue<Integer> queue2;

    private int top;

    public StackWithTwoQueues() {
        queue1 = new ArrayDeque<>();
        queue2 = new ArrayDeque<>();
    }

    // O(1)
    public void push(int value) {
        getFullQueue().add(value);

        top = value;
    }

    // O(n)
    public int pop() {
        if (isEmpty())
            throw new IllegalStateException();

        var fullQueue = getFullQueue();
        var emptyQueue = getEmptyQueue();

        while (fullQueue.size() > 1) {
            top = fullQueue.remove();
            emptyQueue.add(top);
        }

        return fullQueue.remove();
    }

    // O(n)
    public int peek() {
        if (isEmpty())
            throw new IllegalStateException();

        return top;
    }

    public int size() {
        return getFullQueue().size();
    }

    public boolean isEmpty() {
        return getFullQueue().isEmpty();
    }

    private Queue<Integer> getFullQueue() {
        return queue1.isEmpty() ? queue2 : queue1;
    }

    private Queue<Integer> getEmptyQueue() {
        return queue1.isEmpty() ? queue1 : queue2;
    }

}
