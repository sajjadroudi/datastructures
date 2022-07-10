package sort.counting;

public class UnstableCountingSort {

    public static void sort(int[] array) {
        int length = getMaxValue(array) + 1;
        int[] counts = new int[length];
        fillCounts(array, counts);
        sort(array, counts);
    }

    private static void sort(int[] array, int[] counts) {
        int count;
        for (int i = 0, j = 0; i < array.length; j++) {
            for(count = counts[j]; count > 0; count--)
                array[i++] = j;
        }
    }

    private static int getMaxValue(int[] array) {
        int max = -1;
        for(int item : array) {
            if(item > max) max = item;
        }
        return max;
    }

    private static void fillCounts(int[] array, int[] counts) {
        for (int value : array) {
            counts[value]++;
        }
    }

}
