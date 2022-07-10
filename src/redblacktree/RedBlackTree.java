package redblacktree;

// TODO
public class RedBlackTree {

    private static class Node {
        private int value;
        private Node leftChild;
        private Node rightChild;
        private Node parent;
        private boolean isBlack;

        public Node(int value, Node parent) {
            this(value, false, parent);
        }

        public Node(int value, boolean isBlack) {
            this(value, isBlack, null);
        }

        public Node(int value, boolean isBlack, Node parent) {
            this.value = value;
            this.isBlack = isBlack;
            this.parent = parent;
        }

        public boolean isBlack() {
            return isBlack;
        }

        public boolean isRed() {
            return !isBlack;
        }

        public void recolor() {
            isBlack = !isBlack;
        }

        public Node getUncle() {
            Node grandParent = parent.parent;
            return (grandParent.leftChild == parent) ?
                    grandParent.rightChild : grandParent.leftChild;
        }

        public Node getGrandParent() {
            return parent.parent;
        }

        public State getState() {
            Node grandParent = getGrandParent();
            if(grandParent.leftChild != null && this == grandParent.leftChild.leftChild)
                return State.LL;
            if(grandParent.rightChild != null && this == grandParent.rightChild.rightChild)
                return State.RR;
            if(grandParent.leftChild != null && this == grandParent.leftChild.rightChild)
                return State.LR;
            return State.RL;
        }

        public boolean isRightChildOfParent() {
            return parent.rightChild == this;
        }

        @Override
        public String toString() {
            return value + "(" + (isBlack ? "B" : "R" ) + ")";
        }
    }

    private enum State {
        RL, LR, LL, RR
    }

    private Node root;
    private int size;

    public void insert(int... values) {
        for(int value : values)
            insert(value);
    }

    public void insert(int value) {
        if(isEmpty()) {
            root = new Node(value, true);
        } else {
            Node curr = root;
            Node prev = root;
            while(curr != null) {
                prev = curr;
                if(value > curr.value) {
                    curr = curr.rightChild;
                } else {
                    curr = curr.leftChild;
                }
            }

            Node node = new Node(value, prev);

            if(value > prev.value) {
                prev.rightChild = node;
            } else {
                prev.leftChild = node;
            }

            balance(node);
        }
        size++;
    }

    private void balance(Node node) {
        if(node == root) {
            if(node.isRed())
                node.recolor();
            return;
        }

        Node parent = node.parent;

        if(parent.isBlack())
            return;

        Node uncle = node.getUncle();
        Node grandParent = node.getGrandParent();

        if(uncle != null && uncle.isRed()) {
            uncle.recolor();
            parent.recolor();
            grandParent.recolor();

            balance(grandParent);
            return;
        }

        grandParent.recolor();
        parent.recolor();

        switch (node.getState()) {
            case LL:
                rotateRight(grandParent);
                break;
            case RR:
                rotateLeft(grandParent);
                break;
            case LR:
                rotateLeft(parent);
                rotateRight(grandParent);
                break;
            default:
                rotateRight(parent);
                rotateLeft(grandParent);
        }

    }

    private void rotateRight(Node node) {
        Node parent = node.parent;
        Node child = node.leftChild;

        node.leftChild = child.rightChild;
        child.rightChild = node;

        if(node.isRightChildOfParent()) {
            parent.rightChild = child;
        } else {
            parent.leftChild = child;
        }

        node.parent = child;
    }

    private void rotateLeft(Node node) {
        Node parent = node.parent;
        Node child = node.rightChild;

        node.rightChild = child.leftChild;
        child.leftChild = node;

        if(node.isRightChildOfParent()) {
            parent.rightChild = child;
        } else {
            parent.leftChild = child;
        }

        node.parent = child;
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
        builder.append(root).append(" ");
        inOrder(root.rightChild, builder);
    }

}
