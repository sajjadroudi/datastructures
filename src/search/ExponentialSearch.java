package search;

public class ExponentialSearch {

    public int search(int[] array, int target) {
        int bound = 1;
        while(bound < array.length && array[bound] < target) bound *= 2;
        int end = Math.min(bound, array.length - 1);
        return new BinarySearch().searchRec(array, target, bound/2, end);
    }

}
