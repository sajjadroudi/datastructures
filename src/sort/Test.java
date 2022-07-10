package sort;

import sort.quick.QuickUtils;

public class Test {

    public static void main(String[] args) {

        SortTester tester = new SortTester();
        RadixSort sorter = new RadixSort();
        boolean successful = tester.test(sorter, 100, 100);
        System.out.println(successful);

        int[] array = {53, 89, 150, 36, 633, 233};
        Utils.printArray(array);
        sorter.sort(array);
        Utils.printArray(array);

        /*int[] array = {8, 2, 33, 36, 59, 77};
        SortTester tester = new SortTester();
        tester.test(new MultiQuickSort(), 10000, 10000);*/

        /*int[] array = {3, 5, 9, 9, 0, 0, 0, 6, 2, 5};
        int[] b = new int[array.length];
        CountingSort2.sort(array, b, Utils.getMax(array));
        System.out.println(Arrays.toString(b));*/

        /*var quick = new QuickUtils();
        var array = new int[] {1, 10, 6, 7, 3};
        System.out.println(quick.randomizedSelection(array,4));*/
    }

}
