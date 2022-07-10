package search;

public class TernarySearch {

    public int search(int[] array, int target) {
        return search(array, target, 0, array.length - 1);
    }

    private int search(int[] array, int target, int start, int end) {
        if(start > end) return -1;

        int partitionSize = (end - start) / 3;
        int mid1 = start + partitionSize;
        int mid2 = end - partitionSize;
        
        if(array[mid1] == target) return mid1;
        if(array[mid2] == target) return mid2;
        if(target < array[mid1]) return search(array, target, start, mid1 - 1);
        if(target > array[mid2]) return search(array, target, mid2 + 1, end);
        return search(array, target, mid1 + 1, mid2 - 1);
    }

}
