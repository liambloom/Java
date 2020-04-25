package liam.chapter7;

import java.util.Arrays;

public class Exercises {
    public static void main (String[] args) {
        //int[] x = (int[]) Ask.forArray(int.class);
        //while (true) System.out.println(exercise9());
    }
    public static int exercise1 (int[] arr, int val) { // last index of
        int index = -1;
        for (int i = 0; i < arr.length; i++) if (arr[i] == val) index = i;
        return index;
    }
    public static int exercise2 (int[] arr) { // range
        return $.max(arr) - $.min(arr) + 1;
    }
    public static int exercise3 (int[] arr, int min, int max) { // # of elements between min and max (inclusive)
        int inRange = 0;
        for (int e : arr) if (e >= min && e <= max) inRange++;
        return inRange;
    }
    public static boolean exercise4 (double[] arr) { // is sorted?
        return Arrays.equals(arr, $.sortedCopy(arr));
    }
    public static int exercise5 (int[] arr) { // mode (min if tie)
        int[] counter = new int[101];
        for (int i : arr) counter[i]++;
        return $.maxIndex(counter);
    }
    public static double exercise6 (int[] ints) { // standard deviation (√(∑((a[i] - avg(a))^2)|i=0 to i=a.length-1)(a[i] - avg(a)^2) / (a.length - 1))
        final double avg = $.avg(ints);
        double numerator = 0;
        for (int i : ints) numerator += Math.pow(i - avg, 2); // The formula in the book is wrong, the book says a[i] - avg^2, correct is (a[i] - avg)^2
        return Math.sqrt(numerator / (ints.length - 1));
    }
    public static int exercise7 (int n, int[] arr) { // returns the nth highest value in the array
        return $.sortedCopy(arr)[arr.length - 1 - n];
    }
    public static int exercise8 (int[] arr) {
        return $.sortedCopy(arr)[arr.length / 2];
    }
    public static int exercise9 (int[] arr) {
        if (arr.length < 2) return 0;
        int minGap = liam.chapter4.Exercises.Infinity;
        for (int i = 0; i < arr.length - 1; i++) minGap = Math.min(minGap, arr[i + 1] - arr[i]);
        return minGap;
    }
}