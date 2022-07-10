package heap;

public class PriorityQueue {

    private final Heap heap;

    public PriorityQueue() {
        heap = new Heap();
    }

    public void enqueue(int value) {
        heap.insert(value);
    }

    public int dequeue() {
        return heap.remove();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

}
