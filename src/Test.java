public class Test {

    public static void main(String[] args) {
        int[] array = new int[] {5, 6, 7, 2, 1, 9, 4, 5, 3, 13, 78};
        int index = DS(array, 0, array.length - 1);
        System.out.println(array[index]);

        simple(array);
        // 0 1 2 4 5 6 7 10
        // 0 1 2 5 6 7 10 12
        // 0 1 2 6 7 10 12 15 : 10
        // 0 1 2 4 6 7 12 15
        // 1 2 3 4 5 5 6 7 9 13 78
    }

    public static int DS(int[] array, int start, int end) {
        if(start == end) {
            return start;
        }
        int middle = (start + end) / 2;
        int a = DS(array, start, middle);
        int b = DS(array, middle + 1, end);
        int mid = (array[a] + array[b]) / 2;
        int diffA = Math.abs(array[a] - mid);
        int diffB = Math.abs(array[b] - mid);
        return (diffA < diffB) ? a : b;
    }

    private static void simple(int[] array) {
        int sum = 0;
        for(int item : array) {
            sum += item;
        }
        sum /= array.length;

        int minDiff = -1;
        int index = 0;
        for(int i = 0; i < array.length; i++) {
            int diff = Math.abs(array[i] - sum);
            if(diff < minDiff) {
                minDiff = diff;
                index = i;
            }
        }

        System.out.println(array[index]);
    }

}
