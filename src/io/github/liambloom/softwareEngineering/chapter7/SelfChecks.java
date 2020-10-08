package io.github.liambloom.softwareEngineering.chapter7;

//import liam.chapter4.Ask;
import java.util.*;

public class SelfChecks { // These were created to check my answers, but I did the problem before writing this.
    public static void main(String[] args) {
        //System.out.println(Arrays.toString(sc4(Ask.forInt("min"), Ask.forInt("max"))));
        System.out.println(sc30(new String[]{"alpha", "beta", "gamma", "delta", "gamma", "beta", "alpha"}));
        int[][] x = {{2, 3}, {4}};
        System.out.println(Arrays.deepToString(x));
    }

    public static void sc3() {
        @SuppressWarnings("unused")
        int[] data = /*new int[]*/{2};
    }

    public static int[] sc4(int min, int max) {
        int[] r = new int[max - min];
        for (int i = 0; i < r.length; i++) r[i] = i + min;
        return r;
    }

    public static void sc5() {
        int[] data = new int[8];
        data[0] = 3;
        data[7] = -18;
        data[4] = 5;
        data[1] = data[0];

        int x = data[4];
        data[4] = 6;
        data[x] = data[0] * data[1];

        System.out.println(Arrays.toString(data));
    }

    public static void sc14() {
        int[] list = {2, 18, 6, -4, 5, 1};
        for (int i = 0; i < list.length; i++) {
            list[i] = list[i] + (list[i] / list[0]);
        }
        System.out.println(Arrays.toString(list));
    }

    public static void sc21(final int[] a) {
        for (int i = 0; i < a.length / 2; i++) {
            final int temp = a[2 * i];
            a[2 * i] = a[2 * i + 1];
            a[2 * i + 1] = temp;
        }
    }
    public static void sc24 (int[] a, int[] b) {
        for (int i = 0; i < a.length; i++) {
            a[i] += b[b.length - 1 - i];
        }
    }
    public static void sc25 (final int[] a, final int[] b) {
        for (int i = 0; i < a.length; i++) {
            a[i] = a[2 * i % a.length] - b[3 * i % b.length];
        }
    }
    public static boolean sc30 (String[] arr) {
        String[] reverse = new String[arr.length];
        for (int i = 0; i < arr.length; i++) reverse[reverse.length - 1 - i] = arr[i];
        return Arrays.equals(arr, reverse);
    }
}
