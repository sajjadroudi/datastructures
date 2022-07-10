package sort;

import java.util.Arrays;
import java.util.Random;

public class SortTester {

    public int[] getArray(final int size) {
        int[] array = new int[size];
        for (int i = 0; i < array.length; i++) {
            array[i] = new Random().nextInt(100);
        }
        return array;
    }

    public boolean isSorted(int[] array) {
        for(int i = 0; i < array.length - 1; i++) {
            if(array[i] > array[i+1]) return false;
        }
        return true;
    }

    public boolean test(Sorter sorter, final int arrayCount, final int arrayLength, final int min, final int max) {
        int[] array;
        for(int i = 0; i < arrayCount; i++) {
            array = getArray(new Random().nextInt(arrayLength));
            sorter.sort(array);
            if(!isSorted(array)) {
                System.out.println("testcase: " + Arrays.toString(array));
                return false;
            }
        }
        return true;
    }

    public boolean test(Sorter sorter, final int arrayCount, final int arrayLength) {
        return test(sorter, arrayCount, arrayLength, 0, 100);
    }

    public boolean test(Sorter sorter) {
        return test(sorter, 100, 100, 0, 100);
    }

    public boolean test(Sorter sorter, int[] array) {
        sorter.sort(array);
        if(!isSorted(array)) {
            System.out.println("failed: " + Arrays.toString(array));
            return false;
        }
        return true;
    }

}
