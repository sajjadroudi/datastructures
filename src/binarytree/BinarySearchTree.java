package binarytree;

public class BinarySearchTree extends Tree {

    @Override
    public void insert(int value) {
        var node = new Node(value);
        if (isEmpty()) {
            root = node;
        } else {
            var parent = getParent(value);
            if (value > parent.value)
                parent.rightChild = node;
            else
                parent.leftChild = node;
        }
        size++;
    }

    public void remove(int value) {
        if (size == 0)
            throw new IllegalStateException();

        Node node = getNode(value);

        if (node == null)
            throw new IllegalArgumentException();

        Node parent = getParent(root, value);
        if (node.isLeaf()) {
            if (parent.leftChild == node)
                parent.leftChild = null;
            else
                parent.rightChild = null;
        } else if (!node.hasRightChild()) {
            // Node only has left child
            if (parent.leftChild == node)
                parent.leftChild = node.leftChild;
            else
                parent.rightChild = node.leftChild;
            node.leftChild = null; // To be collected by the garbage collector
        } else if (!node.hasLeftChild()) {
            // Node only has right child
            if (parent.leftChild == node)
                parent.leftChild = node.rightChild;
            else
                parent.rightChild = node.rightChild;
            node.rightChild = null; // To be collected by the garbage collector
        } else {
            Node current = node.leftChild;
            Node currParent = node;
            while (current.hasRightChild()) {
                currParent = current;
                current = currParent.rightChild;
            }
            remove(current.value);
            size++;
            node.value = current.value;
        }

        size--;
    }

    @Override
    public boolean contains(int value) {
        return getNode(value) != null;
    }

    private Node getNode(int value) {
        if (isEmpty())
            return null;

        Node current = root;
        while (current != null) {
            if (current.value == value)
                return current;
            else if (current.value < value)
                current = current.rightChild;
            else
                current = current.leftChild;
        }
        return null;
    }

    private Node getParent(int value) {
        Node current = root;
        while (true) {
            if (current.value <= value) {
                if (current.rightChild == null)
                    return current;
                else
                    current = current.rightChild;
            } else {
                if (current.leftChild == null)
                    return current;
                else
                    current = current.leftChild;
            }
        }
    }

    private Node getParent(Node root, int value) {
        if (root.hasLeftChild() && root.leftChild.value == value
                || root.hasRightChild() && root.rightChild.value == value) {
            return root;
        }
        if(value > root.value)
            return getParent(root.rightChild, value);
        return getParent(root.leftChild, value);
    }

    // Time complexity : O(log2(n))
    @Override
    public int min() {
        if (isEmpty())
            throw new IllegalStateException();

        Node last = null;
        var current = root;
        while (current != null) {
            last = current;
            current = current.leftChild;
        }
        return last.value;
    }

    @Override
    public int max() {
        if (isEmpty())
            throw new IllegalStateException();
        Node last = null;
        var current = root;
        while (current != null) {
            last = current;
            current = current.rightChild;
        }
        return last.value;
    }

    @Override
    public boolean areSibling(int firstValue, int secondValue) {
        return getParent(firstValue) == getParent(secondValue);
    }

}
