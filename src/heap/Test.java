package heap;

import sort.Utils;

public class Test {

    public static void main(String[] args) {
        testMinHeap();
    }

    public static void test() {
        Heap heap = new Heap();
        heap.insert(2, 6, 5, 22, 64, 9, 3, 15, 100);
        System.out.println(heap);
        heap.remove();
        System.out.println(heap);
        heap.remove();
        System.out.println(heap);

        sort(new int[] {2, 6, 5, 22, 64, 9, 3, 15, 100});

        int[] numbers = {5, 3, 8, 4, 1, 2};

        System.out.println(HeapUtils.getKthMaxValue(numbers, 3));

        System.out.println(HeapUtils.isMaxHeap(numbers));
        HeapUtils.heapify(numbers);
        System.out.println(HeapUtils.isMaxHeap(numbers));
        Utils.printArray(numbers);

    }

    public static void testMinHeap() {
        MinHeap heap = new MinHeap();
        heap.insert(2, "Mohammad");
        heap.insert(6, "Jack");
        heap.insert(5, "James");
        heap.insert(4, "Michel");
        heap.insert(3, "Ali");
        heap.insert(1, "Sajjad");
        System.out.println(heap);
    }

    public static void sort(int[] array) {
        Heap.sort(array);
        Utils.printArray(array);
    }

}
