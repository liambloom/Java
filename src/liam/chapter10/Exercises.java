package liam.chapter10;

import java.util.*;

public class Exercises {
    public static void main (String[] args) {
        ArrayList<String> a = new ArrayList<>(Arrays.asList("one", "two", "three", "four", "five", "six", "seven", "eight"));
        exercise14(a);
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
        new ArrayList$<>(a).reverseSetsOf(2);
    }
    public static void exercise3 (ArrayList<String> a) { // Remove strings of even length
        a.removeIf(e -> e.length() % 2 == 0);
    }
    public static <T> void exercise4 (ArrayList<T> a) { // Duplicate every element
        new ArrayList$<>(a).forEachReverse((e, i) -> a.add(i, e));
    }
    public static <T extends Comparable<Integer>> void exercise5 (ArrayList<T> a) { // Replace every element n with n copies of itself
        // I don't like that different number types are not comparable to each other
        a.removeIf(e -> e.compareTo(0) <= 0);
        new ArrayList$<>(a).forEachReverse((e, i) -> {
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
        new ArrayList$<>(a).iterate(a::removeIf, (e, i) -> i != a.indexOf(e));
    }
    public static <T extends Comparable<Integer>> void exercise8 (ArrayList<T> a) { // Removes 0s from array
        a.removeIf(e -> e.compareTo(0) == 0);
    }
    public static <T extends Comparable<Integer>> int exercise9 (ArrayList<T> a) { // Returns the largest distance between 2 zeros
        final int i0 = a.indexOf(0);
        final int li0 = a.lastIndexOf(0);
        return a.contains(0) && i0 == li0 ? 1 : li0 - i0;
    }
    public static <T extends Comparable<T>> void exercise10 (ArrayList<T> a, T start, T end) { // Remove everything between values start and end
        a.removeIf(e -> start.compareTo(e) < 0 && e.compareTo(end) < 0);
    }
    public static <T> void exercise11 (ArrayList<T> a, int k) { // Replaces every element with k copies of itself
        if (k <= 0) a.removeIf(_e -> true);
        else {
            new ArrayList$<>(a).forEachReverse((e, i) -> {
                for (int j = 1; j < k; j++) a.add(i, e);
            });
        }
    }
    public static void exercise12 (ArrayList<String> a) { // Adds "****" before each four character string
        new ArrayList$<>(a).forEachReverse((e, i) -> {
            if (e.length() == 4) a.add(i, "****");
        });
    }
    public static <T> void exercise13 (ArrayList<T> a) { // Same as exercise 2 but with sets of 3
        new ArrayList$<>(a).reverseSetsOf(3);
    }
    public static void exercise14 (ArrayList<String> a) { // Removes the shorter of each pair of strings
        for (int i = 0; i < a.size(); i++) a.remove(a.get(i).length() > a.get(i + 1).length() ? i + 1 : i);
    }
}

