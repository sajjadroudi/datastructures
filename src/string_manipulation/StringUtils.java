package string_manipulation;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class StringUtils {

    public static int countVowels(String input) {
        if(input == null) return 0;
        int count = 0;
        var chars = input.toLowerCase().toCharArray();
        String vowels = "aeoui";

        for (char ch : chars)
            if (vowels.contains(Character.toString(ch)))
                count++;

        return count;
    }

    public static String reverse(String input) {
        if(input == null) return "";
        var output = new StringBuilder();
        for(int i = input.length() - 1; i >= 0; i--)
            output.append(input.charAt(i));
        return output.toString();
    }

    public static String reverseWords(String input) {
        if(input == null) return "";
        var words = input.trim().split(" ");
        Collections.reverse(Arrays.asList(words));
        return String.join(" ", words);
    }

    public static boolean areRotations(String str1, String str2) {
        return str1.length() == str2.length()
                && str1.concat(str1).contains(str2);
        /*if(str1.length() != str2.length()) return false;
        final int len = str1.length();
        for(int index = 0; index < len; index++) {
            if(str1.charAt(index) == str2.charAt(0)) {
                int j = 0, i = index;
                do {
                    i = (i + 1) % len;
                    j = (j + 1) % len;
                } while(j != 0 && str1.charAt(i) == str2.charAt(j));
                if(j == 0) return true;
            }
        }
        return false;*/
    }

    public static String removeDuplicates(String str) {
        if(str == null) return "";
        var seen = new HashSet<Character>();
        var builder = new StringBuilder();
        var chars = str.toCharArray();
        for(char ch : chars) {
            if(!seen.contains(ch)) {
                builder.append(ch);
                seen.add(ch);
            }
        }
        return builder.toString();
    }

    public static char getMaxOccuringChar(String str) {
        var frequencies = new HashMap<Character, Integer>();

        var chars = str.toCharArray();
        for(char ch : chars) {
            int count = frequencies.getOrDefault(ch, 0) + 1;
            frequencies.put(ch, count);
        }

        int maxCount = 0;
        char maxChar = 0;

        var entries = frequencies.entrySet();
        for(var entry : entries) {
            if(entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                maxChar = entry.getKey();
            }
        }

        return maxChar;
    }

    public static char getMaxOccuringChar2(String str) {
        final int ASCII_SIZE = 256;
        var frequencies = new int[ASCII_SIZE];

        var chars = str.toCharArray();
        for(char ch : chars)
            frequencies[ch]++;

        char maxChar = 0;
        for (char ch = 0; ch < frequencies.length; ch++) {
            if(frequencies[ch] > frequencies[maxChar]) {
                maxChar = ch;
            }
        }

        return maxChar;
    }
    
    public static String capitalize(String sentence) {
        if(sentence == null || sentence.isBlank()) return "";

        String[] words = sentence.trim()
                .replaceAll(" +", " ")
                .split(" ");

        var builder = new StringBuilder();
        for(String word : words) {
            builder
                    .append(Character.toUpperCase(word.charAt(0)))
                    .append(word.substring(1).toLowerCase())
                    .append(" ");
        }
        return builder.toString().trim();
    }

    public static boolean areAnagrams(String first, String second) {
        if(first.length() != second.length()) return false;

        final int ENGLISH_ALPHABET = 26;
        int[] frequencies = new int[ENGLISH_ALPHABET];

        int index;
        for(var i = 0; i < first.length(); i++) {
            index = Character.toLowerCase(first.charAt(i)) - 'a';
            frequencies[index]++;

            index = Character.toLowerCase(second.charAt(i)) - 'a';
            frequencies[index]--;
        }

        for(int count : frequencies) {
            if(count != 0) return false;
        }
        return true;
    }

    public static boolean isPalindrome(String input) {
        for(int i = 0; i <= input.length() / 2; i++) {
            if(input.charAt(i) != input.charAt(input.length() - i - 1))
                return false;
        }
        return true;
    }

}
