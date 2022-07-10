package redblacktree;

public class Test {

    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();
//        tree.insert(10, 18, 7, 15, 16, 30, 25, 40, 60, 2, 1, 70);
        tree.insert(10, 18, 7, 15, 13);
        tree.insert(16);
        System.out.println(tree);
    }

}
