package dev.liambloom.softwareEngineering.chapter12;

import dev.liambloom.tests.Tester;

import java.util.Arrays;

public class MrMRecursivePrograms {
    public static void main(String[] args) {
        final Tester tester = new Tester();
        tester
                .testOutput(() -> countDownRecursively(5), "5, 4, 3, 2, 1\n")
                .testOutput(() -> countUpRecursively(7), "1, 2, 3, 4, 5, 6, 7\n")
                .testOutput(() -> fractionCountUpRecursively(5), "1/1, 1/2, 1/3, 1/4, 1/5\n");
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

    public static void countUpRecursively(int n) {
        if (n > 0) {
            countUpRecursively(n - 1);
            System.out.print(n + (isRoot() ? System.lineSeparator() : ", "));
        }
    }
    public static void fractionCountUpRecursively(int n) {
        if (n > 0) {
            fractionCountUpRecursively(n - 1);
            System.out.print("1/" + n + (isRoot() ? System.lineSeparator() : ", "));
        }
    }


}
