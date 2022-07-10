package heap;

public class MinHeap {

    private static class Node {
        private final int key;
        private final String value;
        public Node(int key, String value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return key + ":" + value;
        }
    }

    private static final int DEF_SIZE = 10;

    private final Node[] nodes;
    private int size;

    public MinHeap(int size) {
        nodes = new Node[size];
    }

    public MinHeap() {
        this(DEF_SIZE);
    }

    public void insert(int key, String value) {
        if(isFull())
            throw new IllegalStateException();

        nodes[size++] = new Node(key, value);

        bubbleUp();
    }

    public String remove() {
        if(isEmpty())
            throw new IllegalStateException();

        String max = nodes[0].value;
        nodes[0] = nodes[--size];

        bubbleDown();

        return max;
    }

    public int size() {
        return size;
    }

    public boolean isFull() {
        return size == nodes.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void bubbleUp() {
        int index = size - 1;
        int parentIndex = parentIndex(index);
        while(nodes[index].key < nodes[parentIndex].key && index > 0) {
            swap(index, parentIndex);

            index = parentIndex;
            parentIndex = parentIndex(index);
        }
    }

    private void bubbleDown() {
        int index = 0;
        while(index <= size && !isValidParent(index)) {
            int largerChildIndex = largerChildIndex(index);
            swap(index, largerChildIndex);
            index = largerChildIndex;
        }
    }

    private int largerChildIndex(int index) {
        if(!hasLeftChild(index))
            return index;
        if(!hasRightChild(index))
            return leftChildIndex(index);

        return (leftChild(index).key < rightChild(index).key) ?
                leftChildIndex(index) : rightChildIndex(index);
    }

    private boolean isValidParent(int index) {
        if(!hasLeftChild(index))
            return true;
        if(!hasRightChild(index))
            return nodes[index].key <= leftChild(index).key;

        return nodes[index].key <= leftChild(index).key && nodes[index].key <= rightChild(index).key;
    }

    private int leftChildIndex(int index) {
        int leftChildIndex = (index * 2) + 1;
        if(leftChildIndex >= size)
            throw new IllegalArgumentException();
        return leftChildIndex;
    }

    private boolean hasLeftChild(int index) {
        return (index * 2) + 1 < size;
    }

    private int rightChildIndex(int index) {
        int rightChildIndex = (index * 2) + 2;
        if(rightChildIndex >= size)
            throw new IllegalArgumentException();
        return rightChildIndex;
    }

    private boolean hasRightChild(int index) {
        return (index * 2) + 2 < size;
    }

    private int parentIndex(int index) {
        int parentIndex = (index - 1) / 2;
        if(parentIndex < 0)
            throw new IllegalArgumentException();
        return parentIndex;
    }

    private void swap(int a, int b) {
        Node temp = nodes[a];
        nodes[a] = nodes[b];
        nodes[b] = temp;
    }

    private Node leftChild(int index) {
        return nodes[leftChildIndex(index)];
    }

    private Node rightChild(int index) {
        return nodes[rightChildIndex(index)];
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[ ");
        for(int i = 0; i < size; i++)
            builder.append(nodes[i]).append(" ");
        return builder.append("]").toString();
    }
    
}
