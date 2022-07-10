package sort.quick;

import sort.Utils;

public class QuickUtils {

    public int randomizedSelection(final int[] array, final int index) {
        if(index < 0 || index >= array.length)
            throw new IllegalStateException();
        return randomizedSelection(array, 0, array.length - 1, index);
    }

    private int randomizedSelection(int[] array, int startIndex, int endIndex, final int index) {
        int[] indexes = partition(array, startIndex, endIndex);
        int pivotIndex = indexes[0];

        if (index >= pivotIndex && index < indexes[1])
            return array[pivotIndex];

        if (index < pivotIndex)
            return randomizedSelection(array, startIndex, indexes[0] - 1, index);

        return randomizedSelection(array, indexes[1], endIndex, index);
    }

    private int[] partition(int[] array, int startIndex, int endIndex) {
        final int pivot = array[startIndex];
        int i, j, k;
        i = j = startIndex;
        k = endIndex;
        while (j <= k) {
            final int item = array[j];
            if (item == pivot) {
                j++;
            } else if (item > pivot) {
                Utils.swap(array, j, k);
                k--;
            } else {
                Utils.swap(array, j, i);
                i++;
                j++;
            }
        }
        return new int[]{i, j};
    }

}
