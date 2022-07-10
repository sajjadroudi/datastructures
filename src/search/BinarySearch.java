package search;

public class BinarySearch {

    public int search(int[] array, int target) {
        int start = 0;
        int end = array.length - 1;
        int middle;

        while(start <= end) {
            middle = (start + end) / 2;

            if(array[middle] == target) return middle;

            if(array[middle] > target)
                end = middle - 1;
            else
                start = middle + 1;
        }

        return -1;
    }

    public int searchRec(int[] array, int target) {
        return searchRec(array, target, 0, array.length - 1);
    }

    public int searchRec(int[] array, int target, int start, int end) {
        int middle = (start + end) / 2;
        if(start > end) return -1;
        if(array[middle] == target) return middle;
        if(array[middle] > target) return searchRec(array, target, start, middle - 1);
        return searchRec(array, target, middle + 1, end);
    }

}
