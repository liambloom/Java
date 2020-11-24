package io.github.liambloom.softwareEngineering.chapter10;

import java.util.*;
import io.github.liambloom.softwareEngineering.chapter8.Point;
import io.github.liambloom.softwareEngineering.chapter8.TimeSpan;

public class Exercises {
    public static void main (String[] args) {
        ArrayList<String> a = new ArrayList<>(Arrays.asList("one", "two", "three", "four", "five", "six", "seven", "eight"));
        exercise17(a, new ArrayList<>(Arrays.asList("hi", "bye", "no", "yes", "foo", "bar", "buz", "loerum", "ipsum", "dolor")));
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
        return io.github.liambloom.softwareEngineering.chapter7.$.avg(vowels.toArray(new Integer[0]));
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
        a.add(0, a.remove(minIndex));
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
    public static <T extends Comparable<T>> void exercise10and15 (ArrayList<T> a, T start, T end) { // Remove everything between values start and end
        a.removeIf(e -> start.compareTo(e) <= 0 && e.compareTo(end) <= 0);
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
    // Because I used generics, Exercises 10 and 15 are the same
    public static void exercise16 (ArrayList<String> a) { // Merges pairs of elements
        for (int i = 0; i < a.size() - 1; i++) {
            a.set(i, a.get(i) + a.get(i + 1));
            a.remove(i + 1);
        }
    }
    public static <T> void exercise17 (ArrayList<T> a1, ArrayList<T> a2) { // Puts elements from a2 into a1 at alternating indexes
        for (int i = 0; i < a2.size(); i++) a1.add(Math.min(2 * i + 1, a1.size()), a2.get(i));
    }
    public static class Exercise18 extends Point implements Comparable<Exercise18> { // Make chapter 8 Point comparable
        public Exercise18 (int x, int y) {
            super(x, y);
        }
        public Exercise18 () {
            super();
        }
        public Exercise18 (Point p) {
            super(p);
        }
        public int compareTo(Exercise18 o) {
            final int yComp = Integer.compare(this.y, o.y);
            return yComp == 0 ? Integer.compare(this.x, o.x) : yComp;
        }
    }
    public static class Exercise19 extends TimeSpan implements Comparable<Exercise19> { // Make chapter 8 TimeSpan comparable
        public Exercise19 (int hours, int minutes) {
            super(hours, minutes);
        }

        public int compareTo(Exercise19 o) {
            return Integer.compare(this.getTotalMinutes(), o.getTotalMinutes());
        }
    }
    public static class Exercise20 extends CalendarDate { // Add a year to CalendarDate
        private int year;

        public Exercise20 (int year, int month, int day) {
            super(month, day);
            this.year = year;
        }

        public int getYear () {
            return year;
        }
        public int compareTo (Exercise20 o) {
            final int compYear = Integer.compare(this.year, o.getYear());
            return compYear == 0 ? compareTo((CalendarDate) o) : compYear;
        }
    }
}

