package string_manipulation;

public class Test {

    public static void main(String[] args) {
        System.out.println(StringUtils.countVowels("Hi, I am sajjad roudi, your bOss"));
        System.out.println(StringUtils.reverse("Sajjad Roudi"));
        System.out.println(StringUtils.reverseWords("Sajjad Roudi"));
        System.out.println(StringUtils.areRotations("ABCD", "CDAB"));
        System.out.println(StringUtils.removeDuplicates("Hellooo!!"));
        System.out.println(StringUtils.getMaxOccuringChar("Hellooo!!"));
        System.out.println(StringUtils.capitalize("    "));
        System.out.println(StringUtils.areAnagrams("aFDS", "FDSa"));
        System.out.println(StringUtils.isPalindrome("asda"));
    }

}
