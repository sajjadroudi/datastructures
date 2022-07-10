package queue;

public class ArrayQueue {

    private int[] array;

    private int front;
    private int rear;
    private int size;

    public ArrayQueue(int capacity) {
        array = new int[capacity];
        rear = -1;
    }

    public void enqueue(int value) {
        if(isFull())
            throw new IllegalStateException();

        increaseRear();
        array[rear] = value;
        size++;
    }

    public int dequeue() {
        if(isEmpty())
            throw new IllegalStateException();

        int output = array[front];
        increaseFront();
        size--;
        return output;
    }

    public int peek() {
        return array[front];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == array.length;
    }

    private void increaseRear() {
        rear = (rear + 1) % array.length;
    }

    private void increaseFront() {
        front = (front + 1) % array.length;
    }

    @Override
    public String toString() {
        var builder = new StringBuilder("[");
        for(int index = front, counter = 0; counter<size; counter++, index++, index%=array.length) {
            builder.append(array[index]);
            if(index != rear) {
                builder.append(", ");
            }
        }
        builder.append("]");
        return builder.toString();
    }
}
