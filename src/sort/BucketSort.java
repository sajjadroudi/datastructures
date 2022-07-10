package sort;

import java.util.LinkedList;

public class BucketSort {

    private static final int bucketCount = 10;

    public static void sort(int[] array) {
        LinkedList<Integer>[] buckets = new LinkedList[bucketCount];
        fillBuckets(array, buckets);
        sortBuckets(buckets);
        sort(array, buckets);
    }

    private static void fillBuckets(int[] array, LinkedList<Integer>[] buckets) {
        int index;
        for(int value : array) {
            index = value / bucketCount;
            if(buckets[index] == null)
                buckets[index] = new LinkedList<>();
            buckets[index].add(value);
        }
    }

    private static void sortBuckets(LinkedList<Integer>[] buckets) {
        for(LinkedList<Integer> bucket : buckets) {
            if(bucket == null) continue;
            bucket.sort(Integer::compareTo);
        }
    }

    private static void sort(int[] array, LinkedList<Integer>[] buckets) {
        int i = 0;
        for(LinkedList<Integer> bucket : buckets) {
            if(bucket == null) continue;
            for (Integer value : bucket) {
                array[i++] = value;
            }
        }
    }

}
