package stack;

import java.util.Arrays;

import javax.swing.CellEditor;

public class MinStack {

    private static class Node {
        public int value;
        public int min;
        public Node next;

        public Node(int value, int min) {
            this.value = value;
            this.min = min;
        }
    }

    private Node top;

    public void push(int value) {
        if(top == null)
            top = new Node(value, value);

        var node = new Node(value, Math.min(value, top.min));
        node.next = top;
        top = node;
    }

    public int pop() {
        if(top == null)
            throw new IllegalStateException();

        int value = top.value;
        top = top.next;
        return value;
    }

    public int peek() {
        if(top == null)
            throw new IllegalStateException();

        return top.value;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public int min() {
        return top.min;
    }

}
