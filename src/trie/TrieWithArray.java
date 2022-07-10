package trie;

public class TrieWithArray {

    public final static int ALPHABET_COUNT = 26;

    private static class Node {

        private char value;
        private Node[] children;
        private boolean isEndOfWord;

        public Node(char value) {
            this.value = value;
            children = new Node[ALPHABET_COUNT];
        }

        public boolean hasChild(char value) {
            int index = toIndex(value);
            return hasIndex(index);
        }

        public void addChild(char value) {
            int index = toIndex(value);
            children[index] = new Node(value);
        }

        public Node getChild(char value) {
            int index = toIndex(value);
            return children[index];
        }

        private static int toIndex(char value) {
            return Character.toLowerCase(value) - 'a';
        }

        private boolean hasIndex(int index) {
            return children[index] != null;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }

    private Node root;
    private int size;

    public TrieWithArray() {
        root = new Node('\0');
    }

    public void insert(String word) {
        char[] chars = word.toCharArray();

        Node current = root;
        for (char ch : chars) {
            if (!current.hasChild(ch))
                current.addChild(ch);
            current = current.getChild(ch);
        }
        current.isEndOfWord = true;

        size++;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return size;
    }

    /*@Override
    public String toString() {

    }*/
}
