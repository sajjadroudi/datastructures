package heap;

public class Heap {

    private static final int DEF_SIZE = 10;

    private final int[] array;
    private int size;

    public Heap(int size) {
        array = new int[size];
    }

    public Heap() {
        this(DEF_SIZE);
    }

    public Heap(int[] array) {
        this(array.length);
        insert(array);
    }

    public static void sort(int[] array) {
        Heap heap = new Heap(array);
        for(int i = array.length - 1; i >= 0; i--)
            array[i] = heap.remove();
    }

    public void insert(int... values) {
        for(int value : values)
            insert(value);
    }

    private void insert(int value) {
        if(isFull())
            throw new IllegalStateException();

        array[size++] = value;

        bubbleUp();
    }

    public int remove() {
        if(isEmpty())
            throw new IllegalStateException();

        int max = array[0];
        array[0] = array[--size];

        bubbleDown();

        return max;
    }

    public int size() {
        return size;
    }

    public boolean isFull() {
        return array.length == size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void bubbleUp() {
        int index = size - 1;
        int parentIndex = parentIndex(index);
        while(array[index] > array[parentIndex] && index > 0) {
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

        return (leftChild(index) > rightChild(index)) ?
                leftChildIndex(index) : rightChildIndex(index);
    }

    private boolean isValidParent(int index) {
        if(!hasLeftChild(index))
            return true;
        if(!hasRightChild(index))
            return array[index] >= leftChild(index);

        return array[index] >= leftChild(index) && array[index] >= rightChild(index);
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
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    private int leftChild(int index) {
        return array[leftChildIndex(index)];
    }

    private int rightChild(int index) {
        return array[rightChildIndex(index)];
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[ ");
        for(int i = 0; i < size; i++)
            builder.append(array[i]).append(" ");
        return builder.append("]").toString();
    }

}
