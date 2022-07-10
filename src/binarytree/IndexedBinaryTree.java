package binarytree;

public class IndexedBinaryTree {

    private static class Node {
        private int value;
        private Node rightChild;
        private Node leftChild;
        private int leftSize;

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
    }

    private Node root;
    private int size;

    public IndexedBinaryTree(int[] array) {
        for (int i = 0; i < array.length; i++) {
            insert(i, array[i]);
        }
    }

    public IndexedBinaryTree() {}

    public int get(int index) {
        if (index >= size)
            throw new IllegalStateException();
        return getNode(index).value;
    }

    public void insert(int index, int value) {
        if (index > size)
            throw new IllegalStateException();

        Node newNode = new Node(value);

        if (index == size)
            if (size == 0)
                root = newNode;
            else
                getNode(size - 1).rightChild = newNode;
        else {
            Node node = getNode(index);
            if (node.hasLeftChild()) {
                Node current = node.leftChild;
                while (current.hasRightChild())
                    current = current.rightChild;
                current.rightChild = newNode;
            } else {
                node.leftChild = newNode;
            }
            node.leftSize++;
        }

        size++;

    }

    public void insert2(int index, int value) {
        if (index > size)
            throw new IllegalStateException();

        Node newNode = new Node(value);

        if (index == 0) {
            if (size == 0)
                root = newNode;
            else {
                Node current = root;
                while (current.hasLeftChild()) {
                    current.leftSize++;
                    current = current.leftChild;
                }
                current.leftChild = newNode;
                current.leftSize++;
            }
        } else {
            Node node = getNode(index - 1);
            if (node.hasRightChild()) {
                Node current = node.rightChild;
                while (current.hasLeftChild()) {
                    current.leftSize++;
                    current = current.leftChild;
                }
                current.leftChild = newNode;
                current.leftSize++;
            } else {
                node.rightChild = newNode;
            }
        }

        size++;

    }

    public void delete(int index) {
        if(index >= size)
            throw new IllegalStateException();
        boolean degree2 = false;
        Node node = root;
        Node parent = root;
        while(node.leftSize != index) {
            if(index > root.leftSize) {
                node = root.rightChild;
                index = index - root.leftSize - 1;
            } else {
                node.leftSize--;
                parent = node;
                node = root.leftChild;
            }
        }

        if(node.isLeaf()) {
            if(parent.leftChild == node)
                parent.leftChild = null;
            else
                parent.rightChild = null;
        } else if(node.hasLeftChild() && node.hasRightChild()) {
            // Node has two children
            // TODO : complete this code block
            Node current = node.leftChild;
            while(current.hasRightChild())
                current = current.rightChild;
            node.value = current.value;
            degree2 = true;
        } else {
            // Node has only left child
            if(parent.leftChild == node) {
                parent.leftChild = node.leftChild;
            } else {
                parent.rightChild = node.leftChild;
            }
            node.leftChild = null;
        }

        size--;
        if(degree2) delete(index - 1);
    }

    private Node getNode(int index) {
        return getNode(root, index);
    }

    private Node getNode(Node root, int index) {
        if (index == root.leftSize)
            return root;
        if (index > root.leftSize)
            return getNode(root.rightChild, index - root.leftSize - 1);
        return getNode(root.leftChild, index);
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
        builder.append((char) root.value).append(" ");
        inOrder(root.rightChild, builder);
    }

}
