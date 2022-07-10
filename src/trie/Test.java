package trie;

import java.util.Arrays;

public class Test {

    public static void main(String[] args) {
        TrieWithMap trie = new TrieWithMap();
        trie.insert("car");
        trie.insert("card");
        trie.insert("care");
        trie.insert("careful");
        trie.insert("egg");

//        System.out.println(Arrays.toString(trie.findWords("e")));
//        System.out.println(trie.containsRecursive("c"));
//        System.out.println(trie.countWords());
        System.out.println(TrieWithMap.longestCommonPrefix(new String[]{"car"}));
//        trie.remove(null);

//        System.out.println(trie.contains("car"));
//        System.out.println(trie.contains("care"));
    }
}
