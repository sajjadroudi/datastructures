package sort;

import java.util.Arrays;

public class InsertionSort {

    public static void main(String[] args) {
        int[] array = {8,22,7,9,31,5,13};
        sort(array);
    }

    public static void sort(int[] array) {
        int current, j;
        int count = 1;
        for (int i = 1; i < array.length; i++) {
            current = array[i];
            for (j = i - 1; j >= 0 && array[j] > current; j--) {
                array[j + 1] = array[j];
            }
            array[j + 1] = current;
            System.out.println(Arrays.toString(array) + "\t" + count++);
        }
    }

}
