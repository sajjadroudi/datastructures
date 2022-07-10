package sort;


public class BubbleSort {

    public static void sort(int[] array) {
        int size = array.length - 1;
        boolean isSorted;
        do {
            isSorted = true;
            for (int i = 0; i < size; i++) {
                if (array[i] > array[i + 1]) {
                    swap(array, i, i + 1);
                    isSorted = false;
                }
            }
            size--;
        } while (!isSorted);
    }

    private static void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

}
