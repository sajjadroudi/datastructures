package hashtable;

import java.util.HashMap;

public class HashTableExercises {

    // O(n)
    public static int mostFrequent(int[] input) {
        var map = new HashMap<Integer, Integer>();
        int repeatCount = 0, mostRepeatedItem = 0;
        for(int item : input) {
            int value = map.getOrDefault(item, 0) + 1;
            map.put(item, value);
            if(value > repeatCount) {
                repeatCount = value;
                mostRepeatedItem = item;
            }
        }
        return mostRepeatedItem;
    }

    // O(n^2)
    public static int countPairsWithDiff(int[] array, int k) {
        int count = 0;
        for(int item : array) {
            if(contains(array, item + k))
                count++;
        }
        return count;
    }

    private static boolean contains(int[] array, int value) {
        return indexOf(array, value) >= 0;
    }

    private static int indexOf(int[] array, int value) {
        for(int index=0; index<array.length; index++) {
            if(array[index] == value)
                return index;
        }
        return -1;
    }

    public static String twoSum(int[] array, int target) {
        for (int i=0; i<array.length; i++) {
            int index = indexOf(array, target - array[i]);
            if(index >= 0)
                return "[" + i + ", " + index + "]";
        }
        return "Not Found!";
    }

}
