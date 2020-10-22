package io.github.liambloom.softwareEngineering.chapter7;

import java.util.Arrays;

@SuppressWarnings("unchecked")
public class $ { // random utility functions that don't fit in anywhere
    public static <T extends Number & Comparable<T>> T min (T[] arr) {
        return arr[minIndex(arr)];
    }
    public static <T extends Number & Comparable<T>> T max (T[] arr) {
        return arr[maxIndex(arr)];
    }
    public static <T extends Number & Comparable<T>> int minIndex (T[] arr) {
        T min = arr[0];
        int minIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            if (min.compareTo(arr[i]) > 0) {
                min = arr[i];
                minIndex = i;
            }
        }
        return minIndex;
    }
    public static <T extends Number & Comparable<T>> int maxIndex (T[] arr) {
        T max = arr[0];
        int maxIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            if (max.compareTo(arr[i]) < 0) {
                max = arr[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    public static <T extends Number> T sum (T[] arr) {
        double[] prim = new double[arr.length];
        for (int i = 0; i < arr.length; i++) prim[i] += arr[i].doubleValue();
        return castNumber(sum(prim), arr);
    }
    public static double sum (double[] arr) {
        double sum = 0;
        for (double i : arr) sum += i;
        return sum;
    }
    public static <T extends Number> double avg (T[] arr) {
        return sum(arr).doubleValue() / arr.length;
    }
    public static <T> T[] sortedCopy (T[] arr) {
        final T[] copy = Arrays.copyOf(arr, arr.length);
        Arrays.sort(copy);
        return copy;
    }
    public static <T extends Number> T castNumber (double d, Class<T> t) {
        if (t.equals(Byte.class)) return (T) (Byte) (byte) Math.round(d);
        else if (t.equals(Short.class)) return (T) (Short) (short) Math.round(d);
        else if (t.equals(Integer.class)) return (T) (Integer) (int) Math.round(d);
        else if (t.equals(Long.class)) return (T) (Long) Math.round(d);
        else if (t.equals(Float.class)) return (T) (Float) (float) d;
        else if (t.equals(Double.class)) return (T) (Double) d;
        else throw new ClassCastException("T must be one of Byte, Short, Integer, Long, Float, Double");
    }
    public static <T extends Number> T castNumber (double d, T[] a) {
        return castNumber(d, (Class<T>) a.getClass().getComponentType());
    }
    public static <T extends Number> T castNumber (double d, T v) {
        return castNumber(d, (Class<T>) v.getClass());
    }
    
    /*
     * This code is based on code by JasonD and ToolmakerSteve on stackoverflow (the
     * original code can be found here https://stackoverflow.com/a/14126736). This
     * method (and this method only) are licensed under CC-BY-SA 3.0
     * (https://creativecommons.org/licenses/by-sa/3.0/). It has been modified to
     * store the long value in a variable instead of casting it twice, and to be
     * able to round.
     */
    public static String numberToString(Number num, int maxDecimalPlaces) {
        final double n = num.doubleValue();
        final long nLong = (long) n;
        if (n == nLong)
            return String.format("%d", nLong);
        else {
            final double pow = Math.pow(10, maxDecimalPlaces);
            return String.format("%s", (double) (long) (n * pow) / pow);
        }
    }
}
