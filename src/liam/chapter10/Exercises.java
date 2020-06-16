package liam.chapter10;

import java.util.ArrayList;
import java.util.Arrays;

public class Exercises {
    public static void main (String[] args) {
        ArrayList<String> a = new ArrayList<>(Arrays.asList("foo", "bar"));
        exercise2(a);
        System.out.println(a);
    }
    public static double exercise1 (final ArrayList<String> strs) {
        if (strs.size() == 0) return 0;
        final ArrayList<Integer> vowels = new ArrayList<>();
        for (String s : strs) {
            int v = 0;
            for (int i = 0; i < s.length(); i++) {
                if ("aeiou".contains("" + Character.toLowerCase(s.charAt(i)))) v++;
            }
            vowels.add(v);
        }
        return liam.chapter7.$.avg(vowels.toArray(new Integer[0]));
    }
    public static <T> void exercise2 (ArrayList<T> a) {
        for (int i = 0; i < a.size() - 1; i += 2) {
            a.add(i + 2, a.get(i));
            a.remove(i);
        }
    }
}
