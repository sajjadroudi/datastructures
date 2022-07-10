package sort;

public class RadixSort implements Sorter {

    private static final int DEFAULT_RADIX = 10;

    private int radix;

    public RadixSort(int radix) {
        this.radix = radix;
    }

    public RadixSort() {
        this(DEFAULT_RADIX);
    }

    private void countingSort(int[] array, int index) {
        int[] counts = new int[radix];

        fillCounts(array, counts, index);

        for(int i = 1; i < counts.length; i++) {
            counts[i] += counts[i - 1];
        }

        int[] output = new int[array.length];
        for(int j = array.length - 1; j >= 0; j--) {
            final int totalNumber = array[j];
            final int digit = getDigitAt(totalNumber, index);
            final int lastIndex = counts[digit] - 1;
            output[lastIndex] = totalNumber;
            counts[digit]--;
        }

        Utils.copy(output, array);

    }

    private void fillCounts(int[] array, int[] counts, int index) {
        for (int value : array) {
            int digit = getDigitAt(value, index);
            counts[digit]++;
        }
    }

    private int getDigitAt(int number, int index) {
        if(index >= getLength(number)) return 0;
        return ((int) (number / Math.pow(10, index))) % 10;
    }

    private int getMax(int[] array) {
        int max = -1;
        for(int item : array) {
            if(item > max) max = item;
        }
        return max;
    }

    private int getLength(int number) {
        return (int) Math.log10(number) + 1;
    }

    @Override
    public void sort(int[] array) {
        int max = getMax(array);
        int d = getLength(max);

        for(int index = 0; index < d; index++) {
            countingSort(array, index);
        }

    }

}
