package io.github.liambloom.softwareEngineering.chapter12;

import java.util.ArrayList;
import java.util.Arrays;

public class SelfChecks {
    public static final long startTime = System.nanoTime();
    public static void main(String[] args) {
        /*System.out.println(sc15(7, 1));
        System.out.println(sc15(4, 2));
        System.out.println(sc15(4, 3));
        System.out.println(sc15(5, 3));
        System.out.println(sc15(5, 4));*/
        //for (int i = 0; i < 10; i++) System.out.println((char)(0x2080 + i));
        fibonacci(46);
        /*for (int i = 0; i <= 47; i++) {
            fibonacci(i);
        }*/
        //System.out.println(fibonacci(50));
        //System.out.println(sc19Original(50));
    }
    public static int sc15(int n, int k) {
        if (k == 0 || k == n) return 1;
        else if (k > n) return 0;
        else return sc15(n - 1, k - 1) + sc15(n - 1, k);
    }
    public static int sc19Original(int n) {
        if (n <= 2) {
            return 1;
        }
        else return sc19Original(n - 1) + sc19Original(n - 2);
    }

    private static final ArrayList<Integer> fibonacci = new ArrayList<>(Arrays.asList(0, 1));
    public static int fibonacci(int n) { // The 47th fibonacci number exceeds the 32-bit integer limit
        if (n < fibonacci.size()) return fibonacci.get(n);
        else {
            final int r = Math.addExact(fibonacci(n - 1), fibonacci.get(n - 2));
            fibonacci.add(r);
            System.out.printf("F%-2s = %10d @ %d%n", new String(("" + n).codePoints().map(e -> (char)(0x2080 + Character.getNumericValue(e))).toArray(), 0, (int) Math.floor(Math.log10(n)) + 1), r, System.nanoTime() - startTime);
            return r;
        }
    }
}
