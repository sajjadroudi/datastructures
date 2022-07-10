package binarytree;

public class Test {

    public static void main(String[] args) {
        test3();
    }

    private static void test3() {
        BinarySearchTree tree = new BinarySearchTree();
        tree.add(20, 10, 40, 6, 15, 30, 2, 8, 18, 25, 35, 7);
        System.out.println(tree.size);

        tree.remove(10);
        System.out.println(tree);
        System.out.println(tree.size);
    }

    private static void test1() {
        IndexedBinaryTree binaryTree = new IndexedBinaryTree();
        int index = 0;
        for(char ch = 'a'; ch <= 'l'; ch++) {
            if(ch == 'f' || ch == 'g')
                continue;
            binaryTree.insert(index++, ch);
        }
        binaryTree.insert(5, 'm');
        System.out.println(binaryTree);
        binaryTree.delete(5);
        System.out.println(binaryTree);
    }

    private static void test2() {
        Tree tree = new BinarySearchTree();
//        tree.add(10, 70, 40, 30, 50, 20, 60);
//        tree.add(7, 4, 9, 1, 6, 8, 10);
//        tree.add(20, 10, 6, 3, 8, 21, 30, 4);
//        tree.add(20, 10, 30, 6, 14, 24, 3, 8, 26);
        tree.add(10, 15, 5, 1, 12, 16, 6, 7);

        /*
                10
             5      15
           1  6   12  16
               7

        height = 3
        size = 6

        In a perfect tree :
       size = 2^(height+1) - 1
        2 ^ (height) <= size < 2 ^ (height + 1)
        8 <= size <= 15
         */

        tree.traverseLevelOrderUsingQueue();

        System.out.println(tree.isBalanced());

        System.out.println(tree.getAncestors(26));
//        tree.addUnordered(4, 1);
//        System.out.println(tree.isBinarySearchTree());
        /*var list = tree.getNodesAtDistance(1);
        System.out.println(list);*/

//        System.out.println(tree.traverseLevelOrder());

//        System.out.println(tree.size());

//        System.out.println(tree.countLeaves());

//        System.out.println(tree.max());

//        System.out.println(tree.min());

//        System.out.println(tree.height());
//        System.out.println(tree.minAsBST());

        /*BinaryTree another = new BinaryTree();
        another.add(20, 10, 6, 3, 8, 21, 30, 4);
        System.out.println(tree.equals(another));*/
    }

}
