package sort.counting;

import sort.Utils;

public class StableCountingSort {

    // O(k + 1) + O(n) + O(k) + O(n) = O(2n + 2k) = O(2(n + k)) = O(n + k)
    public static void sort(final int[] array, int[] result, final int k) {
        int[] c = new int[k + 1];
        for (int item : array) {
            c[item]++;
        }
        // c[i] now contains the number of elements equal to i
        for(int i = 1; i <= k; i++) {
            c[i] += c[i - 1];
        }
        Utils.printArray(c);
        // c[i] now contains the number of elements less than or equal to i
        // c[i] = count of numbers <= i
       for(int i = array.length - 1; i >= 0; i--) {
            final int value = array[i];
            final int lastIndex = c[value] - 1;
            result[lastIndex] = value;
            c[value]--;
        }
    }

    public static void sort2(int[] a, int[] b, int k) {
        int[] c = new int[k + 1];
        for (int item : a) {
            c[item]++;
        }

        int sum = 1;
        for(int i = 0; i < c.length; i++) {
            int temp = c[i];
            c[i] = sum;
            sum += temp;
        }

        for(int value : a) {
            final int startIndex = c[value] - 1;
            b[startIndex] = value;
            c[value]++;
        }
    }

}
