package trie;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class TrieWithMap {

    public final static int ALPHABET_COUNT = 26;

    private static class Node {

        private final char value;
        private final HashMap<Character, Node> children;
        private boolean isEndOfWord;

        public Node(char value) {
            this.value = value;
            children = new HashMap<>();
        }

        public boolean hasChild(char value) {
            return children.containsKey(value);
        }

        public void addChild(char value) {
            children.put(value, new Node(value));
        }

        public Node getChild(char value) {
            return children.get(value);
        }

        public Node[] getChildren() {
            return children.values().toArray(new Node[0]);
        }

        public boolean hasAnyChildren() {
            return !children.isEmpty();
        }

        public void removeChild(char value) {
            children.remove(value);
        }

        public int countChildren() {
            return children.size();
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }

    private Node root;

    public TrieWithMap() {
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
    }

    public boolean contains(String word) {
        if (word == null)
            return false;

        char[] chars = word.toCharArray();

        Node current = root;
        for (char ch : chars) {
            if (!current.hasChild(ch))
                return false;
            current = current.getChild(ch);
        }

        return current.isEndOfWord;
    }

    public boolean containsRecursive(String word) {
        if(word == null)
            return false;

        return containsRecursive(root, word.toCharArray(), 0);
    }

    private boolean containsRecursive(Node node, final char[] chars, final int index) {
        if(index == chars.length)
            return node.isEndOfWord;
        char ch = chars[index];
        if(!node.hasChild(ch))
            return false;

        Node child = node.getChild(ch);
        return containsRecursive(child, chars, index + 1);
    }

    public void remove(String word) {
        if(word == null) return;
        remove(root, 0, word);
    }

    private Node findLastNodeOf(String prefix) {
        if(prefix == null) return null;

        char[] chars = prefix.toCharArray();

        Node current = root;
        for (char ch : chars) {
            if (!current.hasChild(ch)) return null;
            current = current.getChild(ch);
        }

        return current;
    }

    public String[] findWords(String prefix) {
        Node startNode = findLastNodeOf(prefix);

        if(startNode == null) return null;

        List<String> words = new LinkedList<>();
        findWords(startNode, prefix, words);

        return words.toArray(new String[0]);
    }

    private void findWords(Node root, String prefix, List<String> words) {
        if(root.isEndOfWord)
            words.add(prefix);

        Node[] children = root.getChildren();
        for(Node child : children) {
            findWords(child, prefix + child.value, words);
        }
    }

    private void remove(Node root, final int index, final String word) {
        if(index == word.length()) {
            root.isEndOfWord = false;
            return;
        }

        char ch = word.charAt(index);
        Node child = root.getChild(ch);
        if(child == null) return;

        remove(child, index + 1, word);

        if(!child.hasAnyChildren() && !child.isEndOfWord) {
            root.removeChild(ch);
        }
    }

    public int countWords() {
        return countWords(root);
    }

    public static String longestCommonPrefix(String[] words) {
        if(words == null)
            return "";

        int minChars = words[0].length();
        var trie = new TrieWithMap();
        for(String word : words) {
            trie.insert(word);

            if(minChars > word.length())
                minChars = word.length();
        }

        Node current = trie.root;
        StringBuilder builder = new StringBuilder();
        while(current.countChildren() == 1 && minChars > 0) {
            current = current.getChildren()[0];
            builder.append(current.value);
            minChars--;
        }
        return builder.toString();
    }

    private int countWords(Node node) {
        int count = 0;

        if(node.isEndOfWord)
            count++;

        Node[] children = node.getChildren();
        for(Node child : children)
            count += countWords(child);

        return count;
    }

    private void preOrder(Node node, StringBuilder builder) {
        builder.append(node.value);

        var children = node.getChildren();
        for (Node child : children) {
            preOrder(child, builder);
        }
    }

    private void postOrder(Node node, StringBuilder builder) {
        var children = node.getChildren();
        for (Node child : children) {
            postOrder(child, builder);
        }

        builder.append(node.value);
    }

    @Override
    public String toString() {
        var builder = new StringBuilder();
        preOrder(root, builder);
        return builder.toString();
    }
}
