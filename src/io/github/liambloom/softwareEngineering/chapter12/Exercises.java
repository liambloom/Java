package io.github.liambloom.softwareEngineering.chapter12;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Exercises {
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
        exercise5_helper(n, digitsBinary(n));
    }
    private static void exercise5_helper(int n, int digit) {
        //System.out.printf("n = %d, digit = %d%n", n, digit);
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
    private static int digitsBinary(int n) {
        return (int) Math.pow(2, Math.floor(Math.log(n) / Math.log(2)));
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
    public static double exercise9(int n) { // Returns 1 + 1/2 + 1/3 + 1/4 ... + 1/n
        if (n < 1) throw new IllegalArgumentException("n cannot be <0");
        else if (n == 1) return 1;
        else return 1 / n + exercise9(n - 1);
    }
    public static int exercise10(int n1, int n2) { // Finds digits that are the same in each #
        if (n1 < 0 || n2 < 0) throw new IllegalArgumentException("Cannot find corresponding digits of negative numbers");
        else if (n1 < 10 || n2 < 10) return n1 % 10 == n2 % 10 ? 1 : 0;
        else return (n1 % 10 == n2 % 10 ? 1 : 0) + exercise10(n1 / 10, n2 / 10);
    }
    public static String exercise11(String s, int n) { // Repeats s n times
        if (n < 0) throw new IllegalArgumentException("Cannot repeat string negative amount of times");
        else if (n == 0) return "";
        else if (n == 1) return s;
        else return s + exercise11(s, n - 1);
    }
    public static boolean exercise12(String s1, String s2) { // Determines if s1 is reverse of s2
        return s1.length() == s2.length() && (s1.length() == 0 ? true : Character.toLowerCase(s1.charAt(0)) == Character.toLowerCase(s2.charAt(s2.length() - 1)) && exercise12(s1.substring(1), s2.substring(0, s1.length() - 1)));
    }
    public static int exercise13(String s1, String s2) { // Finds index of s2 in s1 without calling String.indexOf
        if (s1.length() == 0 || s1.length() < s2.length()) return -1;
        else if (s1.startsWith(s2)) return 0;
        else {
            final int i = exercise13(s1.substring(1), s2);
            return i == -1 ? i : i + 1;
        }
    }
    public static int exercise14(int n) { // Removes odd digits from a number
        if (n == 0) return 0;
        else if (n % 10 % 2 == 0) return n % 10 + 10 * exercise14(n / 10);
        else return exercise14(n / 10);
    }
    public static MemorizingRecursive<Integer, Integer> factorial = new MemorizingRecursive<>((self, n) -> {
        if (n < 0) throw new IllegalArgumentException("Cannot find factorial of negative number");
        else if (n == 1) return 1;
        else return n * self.apply(n - 1);
    });
    public static int exercise15(int n, int r) { // permut
        return factorial.apply(n) / factorial.apply(n - r);
    }
    // exercises 16 and 17 require graphics, and I didn't do Supplement 3G
    public static void exercise18(int steps) { // Prints every combination of 1s and twos 
        exercise18_helper(new int[0], steps);
    }
    public static void exercise18_helper(int[] taken, int remaining) {
        if (remaining == 0) System.out.println(Arrays.toString(taken));
        else {
            int[] arr = Arrays.copyOf(taken, taken.length + 1);
            arr[taken.length] = 1;
            exercise18_helper(arr, remaining - 1);
            if (remaining >= 2) {
                arr[taken.length] = 2;
                exercise18_helper(arr, remaining - 2);
            }
        }
    }
    public static void exercise19(int n) { // Writes all numbers 0..n in binary
        if (n == 0) System.out.println();
        else exercise19_helper((int) Math.pow(2, n) - 1, (int) Math.pow(2, n - 1));
    }
    public static void exercise19_helper(int n, int digits) {
        if (n >= 0) {
            exercise19_helper(n - 1, digits);
            exercise5_helper(n, digits);
            System.out.println();
        }
    }
    public static <T> void exercise20(T[] list) { // Prints every combination of elements in the list (order doesn't matter)
        new Exercise20<T>().printer(list);
    }

    // Not the most efficient solution, since it generates every possible solution, then finds the sums, and then filters the max value
    // The most efficient solution would stop add as it went down a path and then stop when it exceeded the max value
    // But then I would need to write code to go through the possible combinations, and exercise 20 already does that
    public static Integer exercise21(Integer[] arr, Integer sum) { // finds the maximum sum of elements of arr that is not more than sum
        return exercise21_maxUnder(exercise21_findSums(new Exercise20<Integer>().main(arr)), sum);
    }
    public static Integer exercise21_findSum(Integer[] arr) {
        // This is the same as liam.chapter7.$.sum, so if I'm allowed to use external functions with loops, I could maybe use that instead
        if (arr.length == 0) return 0;
        else {
            return arr[0] + exercise21_findSum(Arrays.copyOfRange(arr, 1, arr.length));
        }
    }
    public static Integer[] exercise21_findSums(Integer[][] arr) {
        if (arr.length == 0) return new Integer[0];
        else {
            Integer[] sums = Arrays.copyOf(exercise21_findSums(Arrays.copyOfRange(arr, 0, arr.length - 1)), arr.length);
            sums[sums.length - 1] = exercise21_findSum(arr[arr.length - 1]);
            return sums;
        }
    }
    public static Integer exercise21_maxUnder(Integer[] arr, Integer max) {
        if (arr.length == 0) return 0;
        else return Math.max(arr[0] > max ? 0 : arr[0], exercise21_maxUnder(Arrays.copyOfRange(arr, 1, arr.length), max));
    }
    public static void exercise22(int n) { // Prints every combination of unique perfect squares that add up to n
        for (String s : exercise22_helper(n, 1)) {
            System.out.println(s);
        }
    }
    public static String[] exercise22_helper(int n, int start) {
        if (n < Math.pow(start, 2)) return new String[0];
        String[] r = Math.sqrt(n) % 1 == 0 ? new String[]{(int) Math.sqrt(n) + "^2"} : new String[0];
        for (int i = start; i <= Math.sqrt(n); i++) {
            if (i == 2) System.out.println(Math.sqrt(n));
            final String[] subSquares = exercise22_helper(n - (int) Math.pow(i, 2), i + 1);
            if (n == 200 && i == 1) System.out.println(subSquares.length);
            for (int j = 0; j < subSquares.length; j++) {
                subSquares[j] = i + "^2 " + subSquares[j];
            }
            String[] newR = new String[r.length + subSquares.length];
            System.arraycopy(r, 0, newR, 0, r.length);
            System.arraycopy(subSquares, 0, newR, r.length, subSquares.length);
            r = newR;
        }
        return r;
    }
}

// Arrays.toString contains a for loop
// MemorizingRecursive.apply contains a for loop
// MemorizingRecursive.apply calls Arrays.equals, which contains a for loop
// MemorizingRecursive.apply calls many methods of HashMap that contains for, while, and do/while loops
class Exercise20<T> {
    public void printer(T[] a) {
        printer(main(a));
    }

    protected void printer(T[][] a) {
        if (a.length > 0) {
            System.out.println(Arrays.toString(a[0]));
            printer(Arrays.copyOfRange(a, 1, a.length));
        }
    }

    public T[][] main(T[] a) {
        return this.main.apply(a);
    }

    @SuppressWarnings("unchecked")
    protected MemorizingRecursive<T[], T[][]> main = new MemorizingRecursive<>((self, a) -> {
        T[][] combinations = (T[][]) Array.newInstance(a.getClass(), (int) Math.pow(2, a.length));
        iLoop(0, self, a, combinations);
        combinations[combinations.length - 1] = (T[]) Array.newInstance(a.getClass().getComponentType(), 0);
        assert !Arrays.asList(combinations).contains(null);
        return combinations;
    });

    // Is using incrementing methods kind of cheating? Yes. But seriously, how were we supposed to do this w/out loops? Even the book was using loops this late in the chapter.
    public void iLoop(int i, MemorizingRecursive<T[], T[][]> self, T[] a, T[][] combinations) {
        if (i >= a.length) return;
        jLoop(0, combinations, a[i], self.apply(Arrays.copyOfRange(a, i + 1, a.length)), (int) (-combinations.length * (Math.pow(0.5, i) - 1)));
        iLoop(i + 1, self, a, combinations);
    }

    @SuppressWarnings("unchecked")
    public void jLoop(int j, T[][] combinations, T e, T[][] following, int combinationIndex) {
        if (j >= following.length) return;
        T[] c = (T[]) Array.newInstance(e.getClass(), following[j].length + 1);
        c[0] = e;
        System.arraycopy(following[j], 0, c, 1, following[j].length);
        combinations[combinationIndex + j] = c;
        jLoop(j + 1, combinations, e, following, combinationIndex);
    }
}