public class Test2 {

    public static void main(String[] args) throws Exception {
        LinkedList linkedList = new LinkedList();
        linkedList.addLast(2);
        linkedList.addLast(5);
        linkedList.addLast(9);
        linkedList.addLast(1);
        linkedList.addLast(0);
        linkedList.addLast(-2);
        linkedList.sort();
        System.out.println(linkedList);
    }

}
