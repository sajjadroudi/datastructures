package binarytree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public abstract class Tree {

    protected static class Node {
        int value;
        Node rightChild;
        Node leftChild;

        Node(int value) {
            this.value = value;
        }

        public boolean hasRightChild() {
            return rightChild != null;
        }

        public boolean hasLeftChild() {
            return leftChild != null;
        }

        @Override
        public String toString() {
            return "Node=" + value;
        }

        public boolean isLeaf() {
            return rightChild == null && leftChild == null;
        }
    }

    protected Node root;
    protected int size;

    public abstract void insert(int value);

    public abstract boolean contains(int value);

    public abstract int min();

    public abstract int max();

    public abstract boolean areSibling(int firstValue, int secondValue);

    public void add(int... values) {
        for (int value : values) {
            insert(value);
        }
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int height() {
        return height(root);
    }

    private int height(Node root) {
        if (root == null) return -1;
        return Math.max(height(root.leftChild), height(root.rightChild)) + 1;
    }

    public boolean equals(BinaryTree other) {
        if (other == null)
            return false;
        return checkEquality(root, other.root);
    }

    // Pre-order
    private boolean checkEquality(Node first, Node second) {
        if (first == null && second == null) return true;
        if (first == null || second == null) return false;
        return first.value == second.value
                && checkEquality(first.leftChild, second.leftChild)
                && checkEquality(first.rightChild, second.rightChild);
    }

    public ArrayList<Integer> getNodesAtDistance(int k) {
        var list = new ArrayList<Integer>();
        getNodesAtDistance(root, k, list);
        return list;
    }

    // Pre-order traverse
    private void getNodesAtDistance(Node root, int dis, ArrayList<Integer> list) {
        if (root == null) return;
        if (dis == 0) {
            list.add(root.value);
            return;
        }
        getNodesAtDistance(root.leftChild, dis - 1, list);
        getNodesAtDistance(root.rightChild, dis - 1, list);
    }

    public int size() {
        return size;
    }

    // Breadth first search (Level order traversal)
    public ArrayList<Integer> traverseLevelOrder() {
        var list = new ArrayList<Integer>();
        int height = height();
        for (int i = 0; i <= height; i++) {
            list.addAll(getNodesAtDistance(i));
        }
        return list;
    }

    public void traverseLevelOrderUsingQueue() {
        traverseLevelOrderUsingQueue(root, new ArrayDeque<>());
        System.out.println();
    }

    private void traverseLevelOrderUsingQueue(Node root, Queue<Node> queue) {
        if (root == null) return;

        // visit root
        System.out.print(root.value + " ");

        if (root.leftChild != null)
            queue.add(root.leftChild);
        if (root.rightChild != null)
            queue.add(root.rightChild);

        if (queue.isEmpty()) return;

        traverseLevelOrderUsingQueue(queue.remove(), queue);
    }

    public int countLeaves() {
        return countLeaves(root);
    }

    private int countLeaves(Node root) {
        if (root == null) return 0;
        if (root.isLeaf()) return 1;
        return countLeaves(root.leftChild) + countLeaves(root.rightChild);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[ ");
        inOrder(root, builder);
        builder.append("]");
        return builder.toString();
    }

    private void inOrder(Node root, StringBuilder builder) {
        if (root == null)
            return;
        inOrder(root.leftChild, builder);
        builder.append(root.value).append(" ");
        inOrder(root.rightChild, builder);
    }

    /*
    Pre-order:  root - left - right
    In-order:   left - root - right
    Post-order: left - right - root
     */
    protected void traverse(Node current, StringBuilder builder) {
        if (current == null) return;
        traverse(current.leftChild, builder);
        traverse(current.rightChild, builder);
        builder.append(current.value).append(" ");
    }

    public boolean isBinarySearchTree() {
        return isBinarySearchTree(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    // Pre-order
    private boolean isBinarySearchTree(Node root, int min, int max) {
        if (root == null) return true;
        return root.value < max && root.value > min
                && isBinarySearchTree(root.leftChild, min, root.value)
                && isBinarySearchTree(root.rightChild, root.value, max);
    }

    public ArrayList<Integer> getAncestors(int value) {
        var list = new ArrayList<Integer>();
        fillListWithAncestors(root, value, list);
        return list;
    }

    private boolean fillListWithAncestors(Node root, int value, ArrayList<Integer> list) {
        if (root == null) return false;
        if (root.value == value) return true;
        boolean result = fillListWithAncestors(root.leftChild, value, list)
                || fillListWithAncestors(root.rightChild, value, list);
        if (result)
            list.add(root.value);
        return result;
    }

    public boolean isPerfect() {
        return size == Math.pow(2, height(root) + 1) - 1;
    }

    public boolean isBalanced() {
        // 2 ^ (height) <= size < 2 ^ (height + 1)
        int height = height();
        return Math.pow(2, height) <= size
                && size < Math.pow(2, height + 1);
    }

}
