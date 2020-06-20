package liam.chapter10;


import java.util.Arrays;
import java.util.ArrayList;

public class Exercises {
    public static void main (String[] args) {
        ArrayList<Integer> a = new ArrayList<>(Arrays.asList(1, 1, 3, 0, -3, -3));
        exercise7(a);
        System.out.println(a);
    }
    public static double exercise1 (final ArrayList<String> strs) { // Avg # of vowels
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
    public static <T> void exercise2 (ArrayList<T> a) { // Switch each pair of elements
        for (int i = 0; i < a.size() - 1; i += 2) {
            a.add(i + 2, a.get(i));
            a.remove(i);
        }
    }
    public static void exercise3 (ArrayList<String> a) { // Remove strings of even length
        a.removeIf(e -> e.length() % 2 == 0);
    }
    public static <T> void exercise4 (ArrayList<T> a) { // Duplicate every element
        $.forEachReverse(a, (e, i) -> a.add(i, e));
    }
    public static <T extends Comparable<Integer>> void exercise5 (ArrayList<T> a) { // Replace every element n with n copies of itself
        // I don't like that different number types are not comparable to each other
        a.removeIf(e -> e.compareTo(0) <= 0);
        $.forEachReverse(a, (e, i) -> {
            for (int j = 1; e.compareTo(j) > 0; j++) {
                a.add(i, e);
            }
        });
    }
    public static <T extends Comparable<T>> void exercise6 (ArrayList<T> a) { // Move the minimum value to the front
        int minIndex = 0;
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i).compareTo(a.get(minIndex)) < 0) minIndex = i;
        }
        T min = a.get(minIndex);
        a.remove(minIndex);
        a.add(0, min);
    }
    public static <T> void exercise7 (ArrayList<T> a) { // Remove duplicates (assumes that a is sorted)
        $.iteratePredicate(a.listIterator(), a::removeIf, (e, i) -> i != a.indexOf(e));
    }

}

