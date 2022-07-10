package search;

public class LinearSearch {

    public int search(int[] array, int target) {
        for (int index = 0; index < array.length; index++)
            if (array[index] == target)
                return index;
        return -1;
    }

}
