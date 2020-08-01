package liam.chapter12;

import liam.chapter4.Ask;
import java.util.Arrays;

public class Exercises {
    public static void main(String[] args) {
        /*while (true) */exercise20(new String[]{"Janet", "Robert", "Morgan", "Char"});
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
    public static RecursiveWMemory<Integer, Integer> factorial = new RecursiveWMemory<>((self, n) -> {
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
    public static <T> void exercise20(T[] list) {
        new Exercise20<T>().printer(list);
    }
    /*public static <T> T[][] exercise20_helper(T e, T[] list) {
        if (list.length == 0) System.out.println("[" + e + "]");

    }*/
}

class Exercise20<T> {
    public void printer(T[] a) {
        for (T[] e : main(a)) {
            System.out.println(Arrays.toString(e));
        }
    }
    @SuppressWarnings("unchecked")
    public T[][] main(T[] a) {
        //System.out.println("a = " + Arrays.toString(a));
        //System.out.println(a.length + " @170");
        //@SuppressWarnings("unchecked")
        //System.out.println("-------------------------------------------------------------------------");
        T[][] combinations = (T[][]) new Object[(int) Math.pow(2, a.length)][];
        for (int i = 0; i < a.length; i++) {
            //System.out.println("i = " + i);
            //System.out.println(Arrays.deepToString(combinations));
            T e = a[i];
            //System.out.println(Arrays.toString(a) + " @174");
            //System.out.println(Arrays.toString(Arrays.copyOfRange(a, i + 1, a.length)) + " @175");
            T[][] following = main(Arrays.copyOfRange(a, i + 1, a.length));
            //System.out.println(following.length + " @178");
            final int combinationIndex = (int) (-combinations.length * (Math.pow(0.5, i) - 1));
            //if (combinations.length == 4) System.out.println("combinationIndex = " + combinationIndex);
            for (int j = 0; j < following.length; j++) {
                //System.out.println("  j = " + j);
                //System.out.println(Arrays.toString(following[j]) + " @182");
                //@SuppressWarnings("unchecked")
                T[] c = (T[]) new Object[following[j].length + 1];
                c[0] = e;
                System.arraycopy(following[j], 0, c, 1, following[j].length);
                //if (c == null) System.out.println("C IS NULL!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                //if (combinations.length == 4) System.out.println("index = " + (combinationIndex + j));
                combinations[combinationIndex + j] = c;
            }
        }
        //@SuppressWarnings("unchecked")
        combinations[combinations.length - 1] = (T[]) new Object[0];
        //System.out.println(Arrays.deepToString(combinations) + " @192");
        assert !Arrays.asList(combinations).contains(null);
        return combinations;
    }
}