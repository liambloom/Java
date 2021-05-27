package dev.liambloom.softwareEngineering.chapter12;

import dev.liambloom.tests.Tester;

public class MrMRecursivePrograms {
    public static void main(String[] args) {
        final Tester tester = new Tester();
        tester
            .testOutput(() -> countDownRecursively(5), "5, 4, 3, 2, 1\n")
            .testOutput(() -> countUpRecursively(7), "1, 2, 3, 4, 5, 6, 7\n")
            .testOutput(() -> fractionCountUpRecursively(5), "1/1, 1/2, 1/3, 1/4, 1/5\n")
            .testOutput(() -> printEvenNums(5), "2, 4, 6, 8, 10\n")
            .testOutput(() -> printOddNums(5), "1, 3, 5, 7, 9\n")
            .testOutput(() -> print10sRecursively(6), "1, 10, 100, 1000, 10000, 100000\n")
            .testOutput(() -> printLettersRecursively(7), "a, b, c, d, e, f, g\n")
            .testOutput(() -> printLettersRecursivelyStartEnd(5, 10), "e, f, g, h, i, j\n")
            .testOutput(() -> printParenthesesRecursively(5), "((((( )))))\n")
            .testOutput(() -> printRepetitiveNumber(3, 6), "333333\n")
            .testOutput(() -> printTriangleRecurisvely(4), "*\n* *\n* * *\n* * * *\n")
            .test(() -> numberOfDigitsRecursively(1234567L), 7)
            .testAssert(() -> areDigitsAllEvenRecursively(24680) && !areDigitsAllEvenRecursively(245680))
            .test(() -> countNumberOf_Ks_Recursively(323345321L, 3), 4)
            .testOutput(() -> printNumberBackwards(12345), "54321\n")
            .test(() -> reverseNumRecursively(12345), 54321)
            .test(() -> powerRecursively(3, 5), 243)
            .testAssert(() -> isPalindrome(1234321) && !isPalindrome(1234521))
            .testAssert(() -> hasDigit(12345, 3) && !hasDigit(12345, 6))
            .testAssert(() -> areDigitsDecreasing(54321) && !areDigitsDecreasing(54521))
            .testAssert("Test 19-alt", () -> countCinS("abcadaef", 'a') == 3 && countCinS("abcadaef", 'z') == 0)
            .testAssert("Test 20-alt", () -> isArraySortedDecreasingly(new int[]{5, 4, 3, 2, 1}, 0) && !isArraySortedDecreasingly(new int[]{5, 4, 1, 2, 1}, 0));
        tester.close();
    }

    private static boolean isRoot() {
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        return stackTrace.length >= 3
                && !(stackTrace[1].getClassName().equals(stackTrace[2].getClassName())
                    && stackTrace[1].getMethodName().equals(stackTrace[2].getMethodName()));
    }

    // Program 1 (I don't have annotations for this :(
    public static void countDownRecursively(int n) {
        if (n == 1)
            System.out.println(n);
        else {
            System.out.print(n + ", ");
            countDownRecursively(n - 1);
        }
    }

    // Program 2
    public static void countUpRecursively(int n) {
        if (n > 0) {
            countUpRecursively(n - 1);
            System.out.print(n + (isRoot() ? System.lineSeparator() : ", "));
        }
    }

    // Program 3
    public static void fractionCountUpRecursively(int n) {
        if (n > 0) {
            fractionCountUpRecursively(n - 1);
            System.out.print("1/" + n + (isRoot() ? System.lineSeparator() : ", "));
        }
    }

    // Program 4
    public static void printEvenNums(int n) {
        if (n > 0) {
            printEvenNums(n - 1);
            System.out.print((n * 2) + (isRoot() ? System.lineSeparator() : ", "));
        }
    }

    // Program 5
    public static void printOddNums(int n) {
        if (n > 0) {
            printOddNums(n - 1);
            System.out.print((n * 2) - 1 + (isRoot() ? System.lineSeparator() : ", "));
        }
    }

    // Program 6
    public static void print10sRecursively(int n) {
        if (n > 0) {
            print10sRecursively(n - 1);
            System.out.print((int) Math.pow(10, n - 1) + (isRoot() ? System.lineSeparator() : ", "));
        }
    }

    // Program 7
    public static void printLettersRecursively(int n) {
        if (n > 0) {
            printLettersRecursively(n - 1);
            System.out.print((char) (n + 96) + (isRoot() ? System.lineSeparator() : ", "));
        }
    }

    // Program 8
    public static void printLettersRecursivelyStartEnd(int start, int end) {
        if (end >= start) {
            printLettersRecursivelyStartEnd(start, end - 1);
            System.out.print((char) (end + 96) + (isRoot() ? System.lineSeparator() : ", "));
        }
    }

    // Program 9
    public static void printParenthesesRecursively(int n) {
        if (n > 0) {
            System.out.print('(');
            printParenthesesRecursively(n - 1);
            System.out.print((n == 1 ? " " : "") + ')' + (isRoot() ? System.lineSeparator() : ""));
        }
    }

    // Program 10
    public static void printRepetitiveNumber(int t, int n) {
        if (n > 0) {
            System.out.print(t);
            printRepetitiveNumber(t, n - 1);
            if (isRoot())
                System.out.println();
        }
    }

    // Program 11
    public static void printTriangleRecurisvely(int n) {
        if (n > 0) {
            printTriangleRecurisvely(n - 1);
            printTriangleRow(n);
        }
    }

    private static void printTriangleRow(int n) {
        if (n == 0)
            System.out.println();
        else {
            System.out.print((isRoot() ? "" : " ") + "*");
            printTriangleRow(n - 1);
        }
    }

    // Program 12
    public static int numberOfDigitsRecursively(long n) {
        if (n == 0)
            return 0;
        else
            return 1 + numberOfDigitsRecursively(n / 10);
    }

    // Program 13
    public static boolean areDigitsAllEvenRecursively(int n) {
        if (n == 0)
            return true;
        else
            return n % 10 % 2 == 0 && areDigitsAllEvenRecursively(n / 10);
    }

    // Program 14
    public static int countNumberOf_Ks_Recursively(long n, int k) {
        if (n == 0)
            return 0;
        else
            return (n % 10 == k ? 1 : 0) + countNumberOf_Ks_Recursively(n / 10, k);
    }

    // Program 15
    public static void printNumberBackwards(int n) {
        System.out.println(reverseNumRecursively(n));
    }

    // Program 16
    public static int reverseNumRecursively(int n) {
        if (n < 10)
            return n;
        else
            return n % 10 * powerRecursively(10, numberOfDigitsRecursively(n / 10)) + reverseNumRecursively(n / 10);
    }

    // Program 17
    public static int powerRecursively(int a, int b) {
        if (b == 0)
            return 1;
        else
            return a * powerRecursively(a, b - 1);
    }

    // Program 18
    public static boolean isPalindrome(long n) {
        int digits = (int) Math.ceil(Math.log10(n + 1));
        return n / (long) Math.pow(10, (digits + 1) / 2) == reverseNumRecursively((int) (n % (long) Math.pow(10, digits / 2)));
    }

    // Program 19
    public static boolean hasDigit(int n, int k) {
        if (k > 10)
            throw new IllegalArgumentException("k must be one digit");
        return n != 0 && (n % 10 == k || hasDigit(n / 10, k));
    }

    // Program 20
    public static boolean areDigitsDecreasing(int n) {
        return n < 10 || n / 10 % 10 > n % 10 && areDigitsDecreasing(n / 10);
    }

    // Other Program 19
    public static int countCinS(String s, char c) {
        int i = s.indexOf(c);
        return i == -1 ? 0 : 1 + countCinS(s.substring(i + 1), c);
    }

    // Other Program 20
    public static boolean isArraySortedDecreasingly(int[] a, int currentPosition) {
        return currentPosition == a.length - 1 || a[currentPosition] > a[currentPosition + 1] && isArraySortedDecreasingly(a, currentPosition + 1);
    }
}
