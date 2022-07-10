package queue;

public class LinkedListQueue {

    private static class Node {
        private int value;
        private Node next;
        public Node(int value) {
            this.value = value;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public void enqueue(int value) {
        var node = new Node(value);

        if(isEmpty()) {
            head = tail = node;
        } else {
            tail.next = node;
            tail = node;
        }

        size++;
    }

    public int dequeue() {
        if(isEmpty())
            throw new IllegalStateException();

        int result = head.value;
        if(head == tail) {
            head = tail = null;
        } else {
            var second = head.next;
            head.next = null;
            head = second;
        }

        size--;
        return result;
    }

    public int peek() {
        if(isEmpty())
            throw new IllegalStateException();

        return head.value;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

}
