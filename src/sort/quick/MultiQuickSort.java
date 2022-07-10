package sort.quick;

import sort.Sorter;
import sort.Utils;

import static sort.Utils.swap;

public class MultiQuickSort implements Sorter {

    @Override
    public void sort(int[] array) {
        sort(array, 0, array.length - 1);
    }

    private void sort(int[] array, int start, int end) {
        if (start >= end) return;
        choosePivot(array, start, end);
        int[] boundary = partition(array, start, end);
        sort(array, start, boundary[0]);
        sort(array, boundary[1], boundary[2]);
        sort(array, boundary[3], end);
    }

    private void choosePivot(int[] array, int start, int end) {
        int leftPivot = Utils.getRandomIndex(start, end);
        int rightPivot = Utils.getRandomIndex(start, end);
        swap(array, start, leftPivot);
        swap(array, end, rightPivot);
        if(array[start] > array[end])
            swap(array, start, end);
    }

    private int[] partition(int[] array, final int start, final int end) {
        final int leftPivot = array[start];
        final int rightPivot = array[end];

        int i = start;
        int j = start + 1;
        int k = end - 1;
        int m = end;
        int l = end - 1;

        while (j <= k) {
            int item = array[j];
            if (item < leftPivot) {
                swap(array, j, i);
                i++;
                j++;
            } else if (item == leftPivot) {
                j++;
            } else if (item > rightPivot) {
                swap(array, j, k);
                swap(array, k, l);
                k--;
                swap(array, l, m);
                l--;
                m--;
            } else if (item == rightPivot) {
                swap(array, j, k);
                swap(array, k, l);
                l--;
                k--;
            } else {
                swap(array, j, k);
                k--;
            }
        }

        return new int[]{i - 1, k + 1, l, m + 1};
    }

    public int[] partition2(int[] array, final int start, final int end) {
        final int leftPivot = array[start];
        final int rightPivot = array[end];

        int i = start;
        int j = start + 1;
        int k = start + 1;
        int l = end - 1;
        int m = end;

        int item;
        while(k <= l) {
            item = array[k];
            if(item < leftPivot) {
                swap(array, k, j);
                k++;
                swap(array, j, i);
                j++;
                i++;
            } else if(item == leftPivot) {
                swap(array, k, j);
                k++;
                j++;
            } else if(item == rightPivot) {
                swap(array, k, l);
                l--;
            } else if(item > rightPivot) {
                swap(array, k, l);
                swap(array, l, m);
                l--;
                m--;
            } else {
                k++;
            }
        }

        return new int[] {i - 1, j, k - 1, m + 1};
    }


}
