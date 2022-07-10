package heap;

import sort.Utils;

public class HeapUtils {

    private static int[] array;

    public static boolean isMaxHeap(int[] array) {
        int lastParentIndex = array.length / 2 - 1;
        return isMaxHeap(array, 0, lastParentIndex);
    }

    private static boolean isMaxHeap(int[] array, int index, final int lastParentIndex) {
        if(index > lastParentIndex)
            return true;

        int leftIndex = 2*index + 1;
        int leftChild = (leftIndex < array.length) ? array[leftIndex] : Integer.MIN_VALUE;
        int rightIndex = 2*index + 2;
        int rightChild = (rightIndex < array.length) ? array[rightIndex] : Integer.MIN_VALUE;

        if(leftIndex >= array.length)
            return true;

        if(rightIndex >= array.length)
            return array[index] > leftChild
                    && isMaxHeap(array, leftIndex, lastParentIndex);

        return array[index] > rightChild
                && array[index] > leftChild
                && isMaxHeap(array, leftIndex, lastParentIndex)
                && isMaxHeap(array, rightIndex, lastParentIndex);
    }

    public static int getKthMaxValue(int[] array, int k) {
        Heap heap = new Heap(array);
        for(;k > 1; k--) {
            heap.remove();
        }
        return heap.remove();
    }

    public static void heapify(int[] array) {
        HeapUtils.array = array;
        int lastParentIndex = array.length / 2 - 1;
        for(int index = lastParentIndex; index >= 0; index--)
            heapify(array, index);
    }

    private static void heapify(int[] array, int index) {
        while(index < array.length && !isValidParent(index)) {
            int largerChildIndex = largerChildIndex(index);
            Utils.swap(array, index, largerChildIndex);
            index = largerChildIndex;
        }
    }

    private static int largerChildIndex(int index) {
        if(!hasLeftChild(index))
            return index;
        if(!hasRightChild(index))
            return leftChildIndex(index);

        return (leftChild(index) > rightChild(index)) ?
                leftChildIndex(index) : rightChildIndex(index);
    }

    private static boolean isValidParent(int index) {
        if(!hasLeftChild(index))
            return true;
        if(!hasRightChild(index))
            return array[index] >= leftChild(index);

        return array[index] >= leftChild(index) && array[index] >= rightChild(index);
    }

    private static int leftChildIndex(int index) {
        int leftChildIndex = (index * 2) + 1;
        if(leftChildIndex >= array.length)
            throw new IllegalArgumentException();
        return leftChildIndex;
    }

    private static boolean hasLeftChild(int index) {
        return (index * 2) + 1 < array.length;
    }

    private static int rightChildIndex(int index) {
        int rightChildIndex = (index * 2) + 2;
        if(rightChildIndex >= array.length)
            throw new IllegalArgumentException();
        return rightChildIndex;
    }

    private static boolean hasRightChild(int index) {
        return (index * 2) + 2 < array.length;
    }

    private static int leftChild(int index) {
        return array[leftChildIndex(index)];
    }

    private static int rightChild(int index) {
        return array[rightChildIndex(index)];
    }

}
