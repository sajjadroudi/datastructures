package sort.merge;

public class MergeSort {

    public static void sort(int[] array) {
        if (array.length < 2)
            return;

        int middle = array.length / 2;

        int[] left = new int[middle];
        copy(array, left, 0);

        int[] right = new int[array.length - middle];
        copy(array, right, middle);

        // left and right arrays will be deleted from the memory
        // after ending the execution of the whole current sort function
        sort(left);
        sort(right);

        merge(array, left, right);
    }

    private static void merge(int[] original, int[] left, int[] right) {
        int i = 0, leftIndex = 0, rightIndex = 0;
        while(leftIndex < left.length && rightIndex < right.length) {
            if (left[leftIndex] <= right[rightIndex])
                original[i] = left[leftIndex++];
            else
                original[i] = right[rightIndex++];
            i++;
        }

        while (leftIndex < left.length)
            original[i++] = left[leftIndex++];

        while (rightIndex < right.length)
            original[i++] = right[rightIndex++];
    }

    private static void copy(int[] orig, int[] dest, int startIndex) {
        for (int i = 0; i < dest.length; i++) {
            dest[i] = orig[startIndex + i];
        }
    }
}
