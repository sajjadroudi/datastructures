package queue;

import java.util.Arrays;

public class PriorityQueue {

    private int[] array;
    private int size;

    public PriorityQueue(int capacity) {
        array = new int[capacity];
    }

    public void add(int value) {
        if (isFull())
            throw new IllegalStateException();

        insertionSort(value);
        size++;
    }

    public int remove() {
        if (isEmpty())
            throw new IllegalStateException();

        return array[--size];
    }

    public int peek() {
        if (isEmpty())
            throw new IllegalStateException();

        return array[size-1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == array.length;
    }

    private void insertionSort(int value) {
        int index;
        for (index = size - 1; index >= 0 && array[index] > value; index--)
            array[index + 1] = array[index];
        array[index + 1] = value;
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOfRange(array, 0, size));
    }
}
