package sort.merge;

public class MergeSort2 {

    public static void sort(int[] array) {
        sortRecursive(array, 0, array.length - 1);
    }

    public static void sortRecursive(int[] array, int start, int end) {
        if (start >= end) return;
        int middle = (start + end) / 2;
        sortRecursive(array, start, middle);
        sortRecursive(array, middle + 1, end);

        // merge() creates two new sorted arrays and merges them into the original array
        // so then those two arrays will be deleted from the memory
        // after ending execution of the merge function
        merge(array, start, middle, end);
    }

    private static void merge(int[] array, final int left, int middle, final int right) {
        // Create new arrays and copy the original array elements into them
        // left : [left, middle]
        // right : [middle + 1, right]
        int[] leftArray = new int[middle - left + 1];
        for (int i = left; i <= middle; i++)
            leftArray[i - left] = array[i];

        int[] rightArray = new int[right - middle];
        for (int i = middle + 1; i <= right; i++)
            rightArray[i - (middle + 1)] = array[i];

        // Merge two sorted arrays into the original one
        int leftIndex = 0, rightIndex = 0, index = left;
        while (leftIndex < leftArray.length && rightIndex < rightArray.length) {
            if (leftArray[leftIndex] < rightArray[rightIndex])
                array[index] = leftArray[leftIndex++];
            else
                array[index] = rightArray[rightIndex++];
            index++;
        }

        while (leftIndex < leftArray.length)
            array[index++] = leftArray[leftIndex++];

        while (rightIndex < rightArray.length)
            array[index++] = rightArray[rightIndex++];

    }

    public static void sortIterative(int[] array) {
        for (int size = 1; size < array.length; size *= 2) {
            for (int index = 0; index < array.length; index += 2*size) {
                int middle = Math.min(array.length, index + size) - 1;
                int end = Math.min(array.length, index + 2 * size) - 1;
                mergeInPlace(array, index, middle, end);
            }
        }
    }

    private static void mergeInPlace(int[] array, final int left, int middle, final int right) {
        int leftIndex = left, rightIndex = middle + 1;
        while (leftIndex <= middle && rightIndex <= right) {
            if (array[rightIndex] <= array[leftIndex]) {
                int temp = array[rightIndex];
                for (int i = rightIndex - 1; i >= leftIndex; i--)
                    array[i + 1] = array[i];
                array[leftIndex] = temp;

                rightIndex++;
                middle++;
            }
            leftIndex++;
        }
    }

}
