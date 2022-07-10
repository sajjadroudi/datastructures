package sort;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Utils {

    public static void shuffleArray(int[] array) {
        Random rnd = ThreadLocalRandom.current();
        for (int i = array.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            int a = array[index];
            array[index] = array[i];
            array[i] = a;
        }
    }

    public static void swap(int[] array, int firstIndex, int secondIndex) {
        int temp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temp;
    }

    public static int getRandomIndex(int floor, int roof) {
        return (int) (Math.random() * (roof - floor + 1) + floor);
    }

    public static String test(int[] array, int start, int end) {
        StringBuilder builder = new StringBuilder();
        for (int i = start; i <= end; i++) {
            builder.append(array[i]).append(" ");
        }
        return builder.toString();
    }

    public static void copy(int[] origin, int[] dest) {
        for (int i = 0; i < origin.length && i < dest.length; i++) {
            dest[i] = origin[i];
        }
    }

    public static void printArray(int[] array) {
        System.out.println(Arrays.toString(array));
    }

    public static int getMax(int[] array) {
        int max = -1;
        for (int x : array) {
            if (x > max)
                max = x;
        }
        return max;
    }

}
