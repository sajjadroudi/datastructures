package queue;

import java.util.Stack;

public class QueueWithTwoStack {

    private Stack<Integer> enqueueStack;
    private Stack<Integer> dequeueStack;

    public QueueWithTwoStack() {
        enqueueStack = new Stack<>();
        dequeueStack = new Stack<>();
    }

    // O(1)
    public void enqueue(int value) {
        enqueueStack.push(value);
    }

    // O(n)
    public int dequeue() {
        if(isEmpty())
            throw new IllegalStateException();

        moveEnqueueStackToDequeueStackIfRequired();
        return dequeueStack.pop();
    }

    // O(n)
    public int peek() {
        if(isEmpty())
            throw new IllegalStateException();

        moveEnqueueStackToDequeueStackIfRequired();
        return dequeueStack.peek();
    }

    // O(1)
    public boolean isEmpty() {
        return enqueueStack.isEmpty() && dequeueStack.isEmpty();
    }

    private void moveEnqueueStackToDequeueStackIfRequired() {
        if(dequeueStack.isEmpty()) {
            while(!enqueueStack.isEmpty()) {
                dequeueStack.push(enqueueStack.pop());
            }
        }
    }

}
