package search;

public class JumpSearch {

    public int search(int[] array, int target) {
        final int blockSize = (int) Math.sqrt(array.length);

        int start = 0, next = blockSize;

        while(start <= array.length) {
            if(next > array.length)
                next = array.length;

            if(array[next - 1] >= target) break;

            start = next;
            next += blockSize;
        }

        for(int index = start; index < next; index++) {
            if(array[index] == target)
                return index;
        }
        return -1;
    }

}
