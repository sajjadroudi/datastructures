package hashtable;

import java.util.HashSet;
import java.util.LinkedHashMap;

public class CharFinder {

    public static Character findFirstNotRepeated(String input) {
        var map = buildHashMap(input);
        for(var item : map.entrySet()) {
            if(item.getValue() == 1) return item.getKey();
        }
        return null;
    }

    public static Character findFirstRepeated(String input) {
        var set = new HashSet<Character>();
        var chars = input.toCharArray();
        for(var ch : chars) {
            if(set.contains(ch))
                return ch;
            set.add(ch);
        }
        return null;
    }

    private static LinkedHashMap<Character, Integer> buildHashMap(String input) {
        var map = new LinkedHashMap<Character, Integer>();
        var chars = input.toCharArray();
        for(char ch : chars) {
            int value = map.getOrDefault(ch, 0);
            map.put(ch, value+1);
        }
        return map;
    }

}
