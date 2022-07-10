package stack;

public class DoubleStack {

    private static final int TOTAL_SIZE = 10;

    private int[] array;

    private int size1;
    private int size2;

    public DoubleStack() {
        array = new int[TOTAL_SIZE];
    }

    public void push1(int value) {
        if(isFull1())
            throw new StackOverflowError();

        array[size1++] = value;
    }

    public void push2(int value) {
        if(isFull2())
            throw new StackOverflowError();

        array[TOTAL_SIZE - (++size2)] = value;
    }

    public int pop1() {
        if(isEmpty1())
            throw new IllegalStateException();

        return array[--size1];
    }

    public int pop2() {
        if(isEmpty2())
            throw new IllegalStateException();

        return array[TOTAL_SIZE - (size2--)];
    }

    public boolean isEmpty1() {
        return size1 == 0;
    }

    public boolean isEmpty2() {
        return size2 == 0;
    }

    public boolean isFull1() {
        return size1 + size2 == TOTAL_SIZE;
    }

    public boolean isFull2() {
        return size1 + size2 == TOTAL_SIZE;
    }

    @Override
    public String toString() {
        var builder = new StringBuilder("[");
        for(int i=0; i<size1; i++) {
            builder.append(array[i]);
            if(i != size1-1) {
                builder.append(", ");
            }
        }
        builder.append("]\n[");
        for(int i=TOTAL_SIZE-1; i>=TOTAL_SIZE-size2; i--) {
            builder.append(array[i]);
            if(i != TOTAL_SIZE-size2) {
                builder.append(", ");
            }
        }
        builder.append("]");
        return builder.toString();
    }

}
