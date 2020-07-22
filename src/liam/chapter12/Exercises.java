package liam.chapter12;

import liam.chapter4.Ask;

public class Exercises {
    public static void main(String[] args) {
        while (true) System.out.println(exercise8(Ask.forInt()));
    }
    public static void exercise1(int n) { // Print n^2
        if (n < 0) throw new IllegalArgumentException("Cannot print <1 stars");
        else if (n == 0) System.out.print('*');
        else {
            exercise1(n - 1);
            exercise1(n - 1);
        }
    }
    public static void exercise2(int n) { // Prints numbers upt to 1..n
        if (n < 1) throw new IllegalArgumentException("Cannot print numbers starting from a number <1");
        else if (n == 1) System.out.print("1");
        else {
            exercise2(n - 1);
            System.out.print(", " + n);
        }
    }
    public static void exercise3(int n) { // Writes (n/2)..1..(n/2). It prints two 1s if n is even
        if (n < 1) throw new IllegalArgumentException("Cannot print sequence of <1 numbers");
        else if (n == 1) System.out.print("1 ");
        else if (n == 2) System.out.print("1 1 ");
        else {
            System.out.print((int) Math.ceil(n / 2.0) + " ");
            exercise3(n - 2);
            System.out.print((int) Math.ceil(n / 2.0) + " ");
        }
    }
    public static int exercise4(int n) { // Returns an int with each digit repeated (eg. 348 => 334488)
        if (n == 0) return 0;
        else return n % 10 * 11 + 100 * exercise4(n / 10);
    }
    public static void exercise5(int n) { // Writes n in binary
        exercise5_helper(n, (int) Math.pow(2, Math.floor(Math.log(n) / Math.log(2))));
    }
    private static void exercise5_helper(int n, int digit) {
        if (digit == 0) return;
        else if (n < digit) {
            System.out.print('0');
            exercise5_helper(n, digit / 2);
        }
        else {
            System.out.print('1');
            exercise5_helper(n - digit, digit / 2);
        }
    }
    public static void exercise6(int n) { // Writes squares for 1..n. Evens descending, then 1, then odds ascending
        if (n == 1) System.out.print("1");
        else if (n < 1) throw new IllegalArgumentException("n cannot be <1");
        else {
            int odd = n % 2 == 0 ? n - 1 : n;
            int even = n / 2 * 2;
            System.out.print((int) Math.pow(odd, 2));
            if (n != 2) {
                System.out.print(", ");
                exercise6(n - 2);
            }
            System.out.print(", " + (int) Math.pow(even, 2));
        }
    }
    public static void exercise7(int n) { // writes n chars. The 1 or 2 in the center are '*', the ones to the left are '<', the ones to the right are '>'
        if (n < 1) throw new IllegalArgumentException("Cannot write <1 chars");
        else if (n == 1) System.out.print("*");
        else if (n == 2) System.out.print("**");
        else {
            System.out.print('<');
            exercise7(n - 2);
            System.out.print('>');
        }
    }
    public static int exercise8(int n) { // Prints the product of the first n even numbers
        if (n < 1) throw new IllegalArgumentException("Cannot multiply a <1 numbers");
        else if (n == 1) return 2;
        else return 2 * n * exercise8(n - 1);
    }
    public static double exercise9(int n) {
        if (n < 1) throw new IllegalArgumentException("n cannot be <0");
        else if (n == 1) return 1;
        else return 1 / n + exercise9(n - 1);
    }
}
