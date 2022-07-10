package search;

import java.util.Arrays;

import sort.BubbleSort;

public class Test {

    public static void main(String[] args) {
        var searcher = new ExponentialSearch();
        int[] array = {1, 3, 6, 2, 4, 7, 8, 32};
        sort(array);
        System.out.println(Arrays.toString(array));
        System.out.println(searcher.search(array, array[3]));
    }

    private static void sort(int[] array) {
        BubbleSort.sort(array);
    }

}
