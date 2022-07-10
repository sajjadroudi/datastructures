package avltree;

public class Test {

    public static void main(String[] args) {
        test2();
    }

    private static void test1() {
        var tree = new AVLTree();
//        tree.add(5, 10, 3, 12, 15, 14);
        tree.add(12, 3, 9, 4, 6, 2);
        System.out.println(tree);
    }

    private static void test2() {
        var tree = new AVLTree();
        tree.add(5, 10, 3, 12, 15, 14);
//        tree.add(12, 3, 9, 4, 6, 2);
//        tree.add(2, 1, 3, 5, 4);
        System.out.println(tree);
    }

}
