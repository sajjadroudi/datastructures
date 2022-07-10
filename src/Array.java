public class Array {

    private int[] array;
    private int size;

    public Array(int initialCapacity) {
        array = new int[initialCapacity];
        size = 0;
    }

    public void insert(int item) {
        resizeIfRequired();
        insertAt(item, size);
    }

    public void removeAt(int index) {
        if(index < 0 || index >= size)
            throw new IllegalArgumentException();
        for(int i=index+1; i<size; i++) {
            array[i-1] = array[i];
        }
        size--;
    }

    public int indexOf(int value) {
        for(int i=0; i<size; i++) {
            if(array[i] == value) return i;
        }
        return -1;
    }

    public boolean contains(int value) {
        return indexOf(value) >= 0;
    }

    // O(n)
    public int max() {
        int max = array[0];
        for(int i=1; i<size; i++) {
            if(array[i] > max) max = array[i];
        }
        return max;
    }

    // O(n^2)
    public Array intersect(Array anotherArray) {
        Array resultArray = new Array(0);
        for(int i=0; i<anotherArray.size; i++) {
            if( contains(anotherArray.get(i)) ) {
                resultArray.insert(anotherArray.get(i));
            }
        }
        return resultArray;
    }

    public void reverse() {
        int n = size/2;
        for(int i=0; i<=n; i++) {
            swap(i, size - (i+1));
        }
    }

    public void insertAt(int item, int index) {
        if(size == array.length) {
            int newLength = calculateNewLength();
            int[] newArray = new int[newLength];
            for(int i=0; i<index; i++) {
                newArray[i] = array[i];
            }
            newArray[index] = item;
            for(int i=index; i<size; i++) {
                newArray[i+1] = array[i];
            }
            array = newArray;
        } else {
            for(int i=size; i>index; i--) {
                array[i] = array[i-1];
            }
            array[index] = item;
        }
        size++;
    }

    public int size() {
        return size;
    }

    public int get(int index) {
        return array[index];
    }

    private void copy(int[] origin, int[] dest) {
        for(int i=0; i<origin.length; i++) {
            dest[i] = origin[i];
        }
    }

    private void swap(int firstIndex, int secondIndex) {
        int temp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temp;
    }

    private int calculateNewLength() {
        return (size < 2) ? size+1 : (size * 3) / 2;
    }

    private void resizeIfRequired() {
        if(array.length == size) {
            int newLength = calculateNewLength();
            int[] newArray = new int[newLength];
            copy(array, newArray);
            array = newArray;
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        for(int i=0; i<size; i++) {
            builder.append(array[i]);
            if(i != size-1) {
                builder.append(", ");
            }
        }
        builder.append("]");
        return builder.toString();
    }
}
