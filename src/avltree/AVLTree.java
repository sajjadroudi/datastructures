package avltree;

public class AVLTree {

    private static class Node {
        private int value;
        private Node rightChild;
        private Node leftChild;
        private int height;

        public Node(int value) {
            this.value = value;
        }

        public boolean hasLeftChild() {
            return leftChild != null;
        }

        public boolean hasRightChild() {
            return rightChild != null;
        }

        public boolean isLeaf() {
            return !hasLeftChild() && !hasRightChild();
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }

    private Node root;
    private int size;

    public void add(int... values) {
        for (int value : values) {
            insert(value);
        }
    }

    public void insert(int value) {
        root = insert(root, value);
        size++;
    }

    // TODO
    public void remove(int value) {
        if (isEmpty())
            throw new IllegalStateException();

        root = helper(root, value);
    }

    private Node helper(Node root, int value) {
        if (root == null)
            return null;

        Node parent = root;

        if (value > root.value) {
            root.rightChild = helper(root.rightChild, value);
        } else if (value < root.value) {
            root.leftChild = helper(root.leftChild, value);
        } else {
            root = remove(root);
            return root;
        }

//        updateHeight(parent);

        root = balance(parent, root);

        return root;
    }

    private Node balance(Node parent, Node node) {
        if (isLeftHeavy(parent)) {
            Node left = parent.leftChild;
            int balanceFactor = balanceFactor(left);
            if (balanceFactor == 0) {
                rotateR0();
            } else if (balanceFactor == 1) {
                rotateR1();
            } else {
                rotateR2();
            }
        } else if (isRightHeavy(parent)) {

        }
        return null;
    }

    private void rotateR0() {

    }

    private void rotateR1() {

    }

    private void rotateR2() {

    }

    private Node remove(Node node) {
        size--;
        Node parent = getParent(node.value);
        if (node.isLeaf()) {
            if (parent.leftChild == node)
                parent.leftChild = null;
            else
                parent.rightChild = null;
            return null;
        }

        if (!node.hasRightChild()) {
            // Node only has left child
            if (parent.leftChild == node)
                parent.leftChild = node.leftChild;
            else
                parent.rightChild = node.leftChild;
            Node temp = node.leftChild;
            node.leftChild = null; // because of garbage collector
            return temp;
        }

        if (!node.hasLeftChild()) {
            // Node only has right child
            if (parent.leftChild == node)
                parent.leftChild = node.rightChild;
            else
                parent.rightChild = node.rightChild;
            Node temp = node.rightChild;
            node.rightChild = null; // because of garbage collector
            return temp;
        }

        Node current = node.leftChild;
        Node currParent = node;
        while (current.hasRightChild()) {
            currParent = current;
            current = currParent.rightChild;
        }
        remove(current.value);
        size++;
        node.value = current.value;

        return node;
    }

    private Node getParent(int value) {
        Node parent = root;
        Node current = root;
        while (true) {
            if (current.value == value)
                return parent;
            parent = current;
            if (value > current.value)
                current = current.rightChild;
            else
                current = current.leftChild;
        }
    }

    private Node getNode(int value) {
        Node current = root;
        while (true) {
            if (current.value == value)
                return current;
            if (value > current.value)
                current = current.rightChild;
            else
                current = current.leftChild;
        }
    }

    private Node insert(Node root, final int value) {
        if (root == null)
            return new Node(value);

        if (value < root.value)
            root.leftChild = insert(root.leftChild, value);
        else
            root.rightChild = insert(root.rightChild, value);

        updateHeight(root);

        return balance(root);
    }

    // Returns new root
    private Node balance(Node root) {
        if (isLeftHeavy(root)) {
            if (balanceFactor(root.leftChild) < 0) {
                root.leftChild = rotateLeft(root.leftChild);
            }
            root = rotateRight(root);
        } else if (isRightHeavy(root)) {
            if (balanceFactor(root.rightChild) > 0) {
                root.rightChild = rotateRight(root.rightChild);
            }
            root = rotateLeft(root);
        }
        return root;
    }

    private Node rotateLeft(Node root) {
        Node child = root.rightChild;
        root.rightChild = child.leftChild;
        child.leftChild = root;

        updateHeight(root);
        updateHeight(child);

        return child;
    }

    private Node rotateRight(Node root) {
        Node child = root.leftChild;
        root.leftChild = child.rightChild;
        child.rightChild = root;

        updateHeight(root);
        updateHeight(child);

        return child;
    }

    private int height(Node node) {
        return node == null ? -1 : node.height;
    }

    private void updateHeight(Node node) {
        node.height = Math.max(
                height(node.leftChild),
                height(node.rightChild)) + 1;
    }

    private boolean isRightHeavy(Node node) {
        return balanceFactor(node) < -1;
    }

    private boolean isLeftHeavy(Node node) {
        return balanceFactor(node) > 1;
    }

    private int balanceFactor(Node node) {
        return node == null ? 0 : height(node.leftChild) - height(node.rightChild);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
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

}
