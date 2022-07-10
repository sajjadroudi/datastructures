import java.util.NoSuchElementException;

public class LinkedList {

    private static class Node {
        private int value;
        private Node next;

        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public void addFirst(int value) {
        Node node = new Node(value, head);
        if (isEmpty()) {
            tail = head = node;
        } else {
            head = node;
        }

        size++;
    }

    public void sort() {
        sort(head);
    }

    public void sort(Node head) {
        for(Node node = head; node != null; node = node.next) {
            for(Node secondNode = head; secondNode.next != null; secondNode = secondNode.next) {
                if(secondNode.value > secondNode.next.value) {
                    int temp = secondNode.value;
                    secondNode.value = secondNode.next.value;
                    secondNode.next.value = temp;
                }
            }
        }
    }

    public void addLast(int value) {
        Node node = new Node(value, null);
        if (isEmpty()) {
            head = tail = node;
        } else {
            tail.next = node;
            tail = node;
        }

        size++;
    }

    public void removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException();

        if (head == tail) {
            head = tail = null;
        } else {
            Node oldFirst = head;
            head = oldFirst.next;
            oldFirst.next = null;
        }

        size--;
    }

    public void removeLast() {
        if (isEmpty())
            throw new NoSuchElementException();

        if (head == tail) {
            head = tail = null;
        } else {
            tail = getNode(size - 2);
            tail.next = null;
        }

        size--;
    }

    public int indexOf(int value) {
        Node currentNode = head;
        for (int index = 0; index < size; index++) {
            if (currentNode.value == value) {
                return index;
            }
            currentNode = currentNode.next;
        }
        return -1;
    }

    public int[] toArray() {
        int[] array = new int[size];
        int index = 0;
        Node currentNode = head;
        while (currentNode != null) {
            array[index] = currentNode.value;
            currentNode = currentNode.next;
            index++;
        }
        return array;
    }

    public void reverse() {
        if (isEmpty()) return;

        Node prev = head;
        Node curr = head.next;

        while (curr != null) {
            Node next = curr.next;
            curr.next = prev;

            prev = curr;
            curr = next;
        }

        tail = head;
        tail.next = null;
        head = prev;
    }

    public void reverseRecursive() {
        revRecursive(head, null);

        // Swap first and last
        Node temp = head;
        head = tail;
        tail = temp;
    }

    private void revRecursive(Node curr, Node prev) {
        if (curr == null) return;
        revRecursive(curr.next, curr);
        curr.next = prev;
    }

    public int getKthFromTheEnd(int k) {
        if(isEmpty())
            throw new IllegalStateException();

        int distance = k - 1;
        Node first = head;
        Node second = head;

        int index = 0;
        while (index != distance) {
            second = second.next;
            if(second == null)
                throw new IllegalArgumentException();
            index++;
        }

        while (second != tail) {
            first = first.next;
            second = second.next;
        }

        return first.value;
    }

    public int get(int index) {
        return getNode(index).value;
    }

    public boolean contains(int value) {
        return indexOf(value) >= 0;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");

        Node currentNode = head;
        for (int i = 0; i < size; i++) {
            builder.append(currentNode.value);
            if (i != size - 1) {
                builder.append(", ");
            }
            currentNode = currentNode.next;
        }
        builder.append("]");

        return builder.toString();
    }

    private Node getNode(int index) {
        if (index >= size || index < 0)
            throw new IllegalArgumentException();

        Node currentNode = head;
        for (int i = 0; i < size; i++) {
            if (index == i) {
                return currentNode;
            }
            currentNode = currentNode.next;
        }

        return null;
    }

    private boolean isEmpty() {
        return head == null;
    }

    public void printMiddle() {
        rec(head, new int[1], 0);
    }

    private void rec(Node node, int[] size, int index) {
        if(node == tail) {
            size[0] = index+1;
            return;
        }

        rec(node.next, size, index+1);

        if(size[0]/2 == index)
            System.out.println(node.value);

        if(size[0] % 2 == 0 && size[0]/2 == index+1)
                System.out.println(node.value);

    }

    public boolean hasLoop() {
        Node slow = head;
        Node fast = head;

        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if(fast == slow) return true;
        }

        return false;
    }

    public void createLoop() {
        getNode(1).next = getNode(3);
    }

    public boolean isPalindrome() {
        return palindrome(new Node[] {head}, head, new boolean[] {false});
    }

    private boolean palindrome(Node[] first, Node last, boolean[] isFinished) {
        if(last == null) return true;
        boolean result = palindrome(first, last.next, isFinished);
        if(isFinished[0]) return result;
        if(first[0] == last) {
            isFinished[0] = true;
            return result;
        }
        result = result && first[0].value == last.value;
        first[0] = first[0].next;
        return result;
    }

    /*public static void merge(Node first, Node second) {
        if(second == null || first == null)
            return;
        if(first.next == null) {
            first.next = second;
            return;
        }
        Node oldNextFirst = first.next;
        Node oldNextSecond = second.next;
        first.next = second;
        second.next = oldNextFirst;

        second.next = null;
        merge(oldNextFirst, oldNextSecond);
    }*/

    public static void merge(LinkedList one, LinkedList two) {

        Node first = one.head;
        Node second = two.head;

        while(first != null && second != null) {
            if(first.next == null) {
                first.next = second;
                return;
            }
            Node oldNextFirst = first.next;
            Node oldNextSecond = second.next;

            first.next = second;
            second.next = oldNextFirst;

//            second.next = null;

            first = oldNextFirst;
            second = oldNextSecond;
        }

        two.head = null;
    }

    public static LinkedList mergeSorted(LinkedList first, LinkedList second) {

        Node curFirst = first.head;
        Node curSecond = second.head;

        LinkedList result = new LinkedList();

        while(curFirst != null && curSecond != null) {
            int value;
            if(curFirst.value < curSecond.value) {
                value = curFirst.value;
                curFirst = curFirst.next;
            } else {
                value = curSecond.value;
                curSecond = curSecond.next;
            }
            result.addLast(value);
        }

        while(curFirst != null) {
            result.addLast(curFirst.value);
            curFirst = curFirst.next;
        }

        while(curSecond != null) {
            result.addLast(curSecond.value);
            curSecond = curSecond.next;
        }

        return result;
    }

    public static void main(String[] args) {
        LinkedList first = new LinkedList();
        first.addLast(1);
        first.addLast(3);
        first.addLast(5);
        first.addLast(7);

        LinkedList second = new LinkedList();
        second.addLast(2);
        second.addLast(4);
        second.addLast(6);
        second.addLast(8);

        System.out.println(mergeSorted(first, second));

    }


    /*
        public static void main(String[] args) {
        LinkedList list = new LinkedList();

        list.addLast(23);
//        list.addLast(46);
//        list.addLast(149);
//        list.addFirst(200);
//        list.addFirst(360);

        int[] array = list.toArray();
        System.out.println(Arrays.toString(array));

//        list.createLoop();

//        System.out.println(list.hasLoop());
        list.printMiddle();
//        System.out.println(list.getKthFromTheEnd(1));

        /*list.reverseRecursive();

        array = list.toArray();
        System.out.println(Arrays.toString(array));
}
     */

}
