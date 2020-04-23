package liam.chapter7;

import java.util.Arrays;

public class Main {
    public static void main (String[] args) {
        System.out.println("bar");
    }
}

class $ { // random utility functions that don't fit in anywhere
    public static int min (int[] arr) {
        return arr[minIndex(arr)];
    }
    public static int max (int[] arr) {
        return arr[maxIndex(arr)];
    }
    public static int minIndex (int[] arr) {
        int min = arr[0];
        int minIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            if (min > arr[i]) {
                min = arr[i];
                minIndex = i;
            }
        }
        return minIndex;
    }
    public static int maxIndex (int[] arr) {
        int max = arr[0];
        int maxIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            if (max < arr[i]) {
                max = arr[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    public static int sum (int[] arr) {
        int sum = 0;
        for (int i : arr) sum += i;
        return sum;
    }
    public static double avg (int[] arr) {
        return sum(arr) / arr.length;
    }
    public static int[] sortedCopy (int[] arr) {
        final int[] copy = Arrays.copyOf(arr, arr.length);
        Arrays.sort(copy);
        return copy;
    }
    public static double[] sortedCopy (double[] arr) {
        final double[] copy = Arrays.copyOf(arr, arr.length);
        Arrays.sort(copy);
        return copy;
    }
}