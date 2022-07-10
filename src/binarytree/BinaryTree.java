package binarytree;

public class BinaryTree extends Tree {

    @Override
    public void insert(int value) {
        // TODO
        var newNode = new Node(value);
        if(isEmpty()) {
            root = newNode;
            return;
        }
        System.out.println(height());
        insert(root, height(), newNode);
    }

    // Pre-order traverse
    private void insert(Node root, int distance, Node node) {
        if (root == null) return;
        if (distance == 0) {
            if(root.leftChild == null) {
                root.leftChild = node;
                return;
            } else if(root.rightChild == null) {
                root.rightChild = node;
                return;
            }
        }
        insert(root.leftChild, distance - 1, node);
        insert(root.rightChild, distance - 1, node);
    }

    private Node getNode(int value) {
        return getNode(root, value);
    }

    private Node getNode(Node root, int value) {
        if(root == null)
            return null;
        if(root.value == value)
            return root;
        Node node = getNode(root.leftChild, value);
        if(node != null) return node;
        node = getNode(root.rightChild, value);
        return node;
    }

    private Node getParent(int value) {
        return getParent(root, value);
    }

    private Node getParent(Node root, int value) {
        if(root == null)
            return null;
        if(root.leftChild != null && root.leftChild.value == value)
            return root;
        if(root.rightChild != null && root.rightChild.value == value)
            return root;
        Node node = getParent(root.leftChild, value);
        if(node != null) return node;
        node = getParent(root.rightChild, value);
        return node;
    }

    // O(n)
    @Override
    public int min() {
        if (isEmpty())
            throw new IllegalStateException();

        return min(root);
    }

    // Post-order
    private int min(Node root) {
        if (root == null) return Integer.MAX_VALUE;
        if (root.isLeaf()) return root.value;
        int min = Math.min(min(root.leftChild), min(root.rightChild));
        return Math.min(min, root.value);
    }

    @Override
    public int max() {
        if(isEmpty())
            throw new IllegalStateException();
        return max(root);
    }

    private int max(Node root) {
        if(root == null) return Integer.MIN_VALUE;
        int max = Math.max(max(root.leftChild), max(root.rightChild));
        return Math.max(max, root.value);
    }

    @Override
    public boolean contains(int value) {
        return contains(root, value);
    }

    // Pre-order
    // suitable for binary tree
    private boolean contains(Node root, int value) {
        if(root == null) return false;
        return root.value == value
                || contains(root.leftChild, value)
                || contains(root.rightChild, value);
    }

    @Override
    public boolean areSibling(int firstValue, int secondValue) {
        return getParent(firstValue) == getParent(secondValue);
    }

}
