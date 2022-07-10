package hashtable;

public class Test {

    public static void main(String[] args) {
        var map = new HashMap(10);
        map.put(1, "One");
        map.put(3, "Three");
        map.put(12, "Twelve");
        map.put(11, "Eleven");
        System.out.println(map);

//        System.out.println(HashTableExercises.countPairsWithDiff(new int[]{1, 7, 5, 9, 2, 12, 3}, 3));
    }

}
