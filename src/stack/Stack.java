package stack;

import java.util.Arrays;

public class Stack {

    private int size;
    private int[] elements;

    public Stack(int capacity) {
        elements = new int[capacity];
        size = 0;
    }

    public void push(int value) {
        if(size == elements.length)
            throw new StackOverflowError();
        elements[size++] = value;
    }

    public int pop() {
        if(size == 0)
            throw new IllegalStateException();
        return elements[--size];
    }

    public int peek() {
        if(size == 0)
            throw new IllegalStateException();
        return elements[size-1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == elements.length;
    }

    public int size() {
        return size;
    }

    public int totalSize() {
        return elements.length;
    }

    @Override
    public String toString() {
        var content = Arrays.copyOfRange(elements, 0, size);
        return Arrays.toString(content);
    }
}
