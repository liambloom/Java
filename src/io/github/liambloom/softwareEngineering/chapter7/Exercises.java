package io.github.liambloom.softwareEngineering.chapter7;

import java.util.Arrays;
import java.lang.reflect.Array;

@SuppressWarnings("unchecked")
public class Exercises {
    public static <T> int exercise1 (T[] arr, T val) { // last index of
        int index = -1;
        for (int i = 0; i < arr.length; i++) if (arr[i].equals(val)) index = i;
        return index;
    }
    public static <T extends Number & Comparable<T>> T exercise2 (T[] arr) { // range
        return $.castNumber($.max(arr).doubleValue() - $.min(arr).doubleValue() + 1, arr);
    }
    public static <T extends Number> int exercise3 (T[] arr, T min, T max) { // # of elements between min and max (inclusive)
        int inRange = 0;
        for (T e : arr) if (e.doubleValue() >= min.doubleValue() && e.doubleValue() <= max.doubleValue()) inRange++;
        return inRange;
    }
    public static <T> boolean exercise4 (T[] arr) { // is sorted?
        return Arrays.equals(arr, $.sortedCopy(arr));
    }
    public static Integer exercise5 (Integer[] arr) { // mode (min if tie)
        Integer[] counter = new Integer[101];
        for (int i : arr) counter[i]++;
        return $.maxIndex(counter);
    }
    public static <T extends Number> double exercise6 (T[] ints) { // standard deviation (√(∑((a[i] - avg(a))^2)|i=0 to i=a.length-1)(a[i] - avg(a)^2) / (a.length - 1))
        final double avg = $.avg(ints);
        double numerator = 0;
        for (T i : ints) numerator += Math.pow(i.doubleValue() - avg, 2); // The formula in the book is wrong, the book says a[i] - avg^2, correct is (a[i] - avg)^2
        return Math.sqrt(numerator / (ints.length - 1));
    }
    public static <T extends Number> T exercise7 (int n, T[] arr) { // returns the nth highest value in the array
        return $.sortedCopy(arr)[arr.length - 1 - n];
    }
    public static <T extends Number> T exercise8 (T[] arr) { // median
        return $.sortedCopy(arr)[arr.length / 2];
    }
    public static <T extends Number> T exercise9 (T[] arr) { // smallest gap between consecutive elements
        if (arr.length < 2) return $.castNumber(0, arr);
        T minGap = $.castNumber(liam.chapter4.Exercises.Infinity, arr);
        for (int i = 0; i < arr.length - 1; i++) minGap = $.castNumber(Math.min(minGap.doubleValue(), arr[i + 1].doubleValue() - arr[i].doubleValue()), arr);
        return minGap;
    }
    public static <T extends Number> double exercise10 (T[] a) { // % of elements that are even
        int even = 0;
        for (T i : a) {
            if (i.doubleValue() % 2 == 0) even++;
        }
        return (double) even / a.length * 100;
    }
    public static <T extends Number> boolean exercise11 (final T[] arr) { // is every element unique
        final T[] sorted = $.sortedCopy(arr);
        for (int i = 0; i < sorted.length - 1; i++) {
            if (sorted[i].equals(sorted[i + 1])) return false;
        }
        return true;
    }
    public static <T extends Number & Comparable<T>> T exercise12 (T[] a, T p) { // largest element in a that isn't higher than p (rules like price is right or blackjack)
        T g = $.castNumber(-1, p);
        for (T i : a) {
            if (i.compareTo(p) <= 0) g = $.castNumber(Math.max(g.doubleValue(), i.doubleValue()), p);
        }
        return g;
    }
    public static <T extends Number & Comparable<T>> int exercise13 (T[] a) { // longest consecutive sequence of non-descending numbers
        int longest = 0;
        int current = 0;
        for (int i = 0; i < a.length - 1; i++) {
            if (a[i].compareTo(a[i + 1]) <= 0) current++;
            else current = 1;
            longest = Math.max(longest, current);
        }
        return longest;
    }
    public static <T> boolean exercise14 (T[] container, T[] containee) { // checks if the sequence containee is in container
        outer: for (int i = 0; i < container.length - containee.length; i++) { // This is the first time I have actually found a use for labels
            if (!container[i].equals(containee[i])) continue;
            for (int j = 0; j < containee.length; j++) {
                if (!container[i + j].equals(containee[j])) continue outer;
            }
            return true;
        }
        return false;
    }
    public static <T extends Number> T[] exercise15 (final T[] arr) { // returns array with sum of each consecutive pair
        final T[] collapsed = Arrays.copyOfRange(arr, 0, (int) Math.ceil(arr.length / 2.0));
        collapsed[collapsed.length - 1] = arr[arr.length - 1];
        for (int i = 0; i < arr.length / 2; i++) collapsed[i] = $.castNumber(arr[2 * i].doubleValue() + arr[2 * i + 1].doubleValue(), arr);
        return collapsed;
    }
    public static <T> T[] exercise16 (final T[] arr1, final T[] arr2) { // concatenates arr1 and arr2
        T[] merged = Arrays.copyOf(arr1, arr1.length + arr2.length);
        for (int i = 0; i < arr2.length; i++) merged[i + arr1.length] = arr2[i];
        return merged;
    }
    public static int[] exercise17 (String s) { // returns array [a, e, i, o, u] with count of each vowel
        int[] counts = new int[5];
        s = s.toLowerCase();
        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                case 'a':
                    counts[0]++;
                    break;
                case 'e':
                    counts[1]++;
                    break;
                case 'i':
                    counts[2]++;
                    break;
                case 'o':
                    counts[3]++;
                    break;
                case 'u':
                    counts[4]++;
                    break;
            }
        }
        return counts;
    }
    // I originally didn't do exercise 18 because I hadn't done chapter 6 yet
    // Now I'm not doing it because I have no clue what it's asking me to do
    public static <T extends Number> T[][] exercise19 (T[][] m1, T[][] m2) { // adds matrices
        if (!m1.getClass().equals(m2.getClass())) throw new IllegalArgumentException("Cannot add matrices of different types");
        if (m1.length == 0) return m1;
        else {
            for (T[] arr : m1) {
                if (arr.length == 0) return m1;
            }
        }
        T[][] ms = (T[][]) Array.newInstance(m1[0][0].getClass(), m1.length, m1[0].length); // Here lies the only warning in the Exercises
        for (int i = 0; i < ms.length; i++) {
            for (int j = 0; j < ms[0].length; j++) {
                T sum = $.castNumber(m1[i][j].doubleValue() + m2[i][j].doubleValue(), m1[i][j]);
                ms[i][j] = sum;
            }
        }
        return ms;
    }
    public static <T extends Number> boolean exercise20 (T[][] m) { // checks if something is a magic square
        // Check for valid square
        final int height = m.length;
        if (height == 0) throw new IllegalArgumentException("A magic square cannot have a height of 0");
        final int width = m[0].length;
        if (height != width) throw new IllegalArgumentException("A magic square must be a square (duh).");

        // Convert T[][] to Double[][] because generics are hard
        Double[][] dblMatrix = new Double[height][];
        for (int i = 0; i < dblMatrix.length; i++) {
            final int rowWidth = m[i].length;
            if (width != rowWidth) throw new IllegalArgumentException("A magic square cannot be jagged");
            dblMatrix[i] = new Double[rowWidth];
            for (int j = 0; j < dblMatrix[i].length; j++) dblMatrix[i][j] = m[i][j].doubleValue();
        }
        double sum = $.sum(dblMatrix[0]);

        // Check rows
        for (int i = 0; i < width; i++) {
            if ($.sum(dblMatrix[i]) != sum) return false;
        }

        // Check columns
        for (int i = 0; i < height; i++) {
            double colSum = 0;
            for (Double[] row : dblMatrix) colSum += row[i];
            if (colSum != sum) return false;
        }

        // Check top-left to bottom-right
        {
            double diagonalSum = 0;
            for (int i = 0; i < width; i++) diagonalSum += dblMatrix[i][i];
            if (diagonalSum != sum) return false;
        }

        // Check for top-right to bottom-left
        {
            double diagonalSum = 0;
            for (int i = 0; i < width; i++) diagonalSum += dblMatrix[i][width - 1 - i];
            return diagonalSum == sum;
        }
    }
}