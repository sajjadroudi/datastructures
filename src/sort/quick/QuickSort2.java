package sort.quick;

import static sort.Utils.getRandomIndex;
import static sort.Utils.swap;

public class QuickSort2 {

    public static void sort(int[] array) {
        sort(array, 0, array.length - 1);
    }

    private static void sort(int[] array, int start, int end) {
        if (start >= end) return;
        choosePivot(array, start, end);
        /*int middle = partitionCLRS(array, start, end);
        sort(array, start, middle - 1);
        sort(array, middle + 1, end);*/
        int[] arr = partition3Way2(array, start, end);
        sort(array, start, arr[0]);
        sort(array, arr[1], end);
    }

    private static void choosePivot(int[] array, int start, int end) {
        int randomIndex = getRandomIndex(start, end);
        swap(array, randomIndex, start);
    }

    private static int[] partition3Way2(int[] array, final int start, final int end) {
        int i = start;
        int j = start + 1;
        int k = end;
        /*while(j <= k) {
            if(array[j] < array[i]) {
                swap(array, i, j);
                i++;
                j++;
            } else if(array[j] > array[i]) {
                swap(array, j, k);
                k--;
            } else {
                j++;
            }
        }*/
        while(k >= j) {
            if(array[k] > array[i]) {
                k--;
            } else if(array[k] < array[i]) {
                swap(array, i, j);
                j++;
                swap(array, i, k);
                i++;
            } else {
                swap(array, k, j);
                j++;
            }
        }
        return new int[] {i - 1, k + 1};
    }

    // This algorithm is taken from Prof. Sedgwick - Princeton University
    private static int[] partition3Way(int[] array, final int start, final int end) {
        int i = start + 1;
        int k = end;
        int j = end;
        while (j >= i) {
            if (array[j] > array[start]) {
                swap(array, k--, j--);
            } else if (array[j] < array[start]) {
                j--;
            } else {
                swap(array, i++, j);
            }
        }
        for(int index = start, temp = k; index < i && temp >= i; index++, temp--) {
            swap(array, index, temp);
        }

        /*int m = Math.min(i - start, k - j);

        for(int index = start, temp = k - m + 1; index < start + m && temp <= k; index++, temp++) {
            swap(array, index, temp);
        }*/

        return new int[] {k - i + start, k + 1};
    }

    private static int partitionClassic(int[] array, int start, int end) {
        final int pivotIndex = start++;
        while (true) {
            while (start <= end && array[start] <= array[pivotIndex]) start++;
            while (start <= end && array[end] > array[pivotIndex]) end--;
            if (start >= end) break;
            swap(array, start, end);
        }
        swap(array, pivotIndex, end);
        return end;
    }

    private static int partitionCLRS(int[] array, final int start, final int end) {
        int i = start + 1;
        for (int j = start + 1; j <= end; j++) {
            if (array[j] < array[start]) {
                swap(array, j, i++);
            }
        }
        swap(array, i - 1, start);
        return i - 1;
    }

}