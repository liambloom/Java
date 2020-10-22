package io.github.liambloom.softwareEngineering.chapter7;

import java.util.Arrays;

public class MrM2DArrayProblems {
    public static void main(String[] args) {
        int a[][] = { 
            { 10, 30, 50, 200 }, 
            { 60, 40, 70, 55 }, 
            { 80, 5, 35, 100 } };

        p4(a);
    }

    public static void p1(final int[][] a) {
        int sum = 0;
        int len = 0;
        for (int[] row : a) {
            len += row.length;
            for (int e : row)
                sum += e;
        }
        System.out.printf("Sum = %d   Average = %s", sum, $.numberToString((double) sum / len, 16));
    }

    public static void p2(final int[][] a) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int[] row : a) {
            for (int e : row) {
                min = Math.min(min, e);
                max = Math.max(max, e);
            }
        }
        if (min > max)
            throw new IllegalArgumentException("No min/max of an empty array");
        System.out.printf("Largest = %d   Smallest = %d", max, min);
    }

    public static void p3(final int[][] a) {
        int even = 0;
        int odd = 0;
        for (int[] row : a) {
            for (int e : row) {
                if (e % 2 == 0)
                    even++;
                else
                    odd++;
            }
        }
        System.out.printf("OddCount = %d   EvenCount = %d", odd, even);
    }

    // This 4th project was hard
    // And this can't possibly be the best solution
    // And I'm glad I don't have to explain it, because I don't know that I could
    // Also, the # of decimal places in your example output was inconsistent and didn't make sense
    public static void p4(final int[][] a) {
        if (a.length == 0)
            throw new IllegalArgumentException("Cannot perform operation on empty array");
        // I think these default to being filled with 0s
        final int rowCount = a.length;
        final int colCount = a[0].length;
        int[] rowSum = new int[rowCount];
        int[] colSum = new int[colCount];
        for (int i = 0; i < a.length; i++) {
            final int[] row = a[i];
            if (row.length != colCount)
                throw new IllegalArgumentException("Cannot perform operation on jagged array");
            for (int j = 0; j < row.length; j++) {
                final int e = row[j];
                rowSum[i] += e;
                colSum[j] += e;
            }
        }
        ArrayAndMaxIndex rowData = sumToAvg(rowSum, colCount);
        rowSum = null;
        ArrayAndMaxIndex colData = sumToAvg(colSum, rowCount);
        rowSum = null;
        final int maxD = Math.max(rowCount, colCount);
        final String[] rowStrings = new String[maxD];
        Arrays.fill(rowStrings, "");
        final String rowStringsLast = String.format("MaxAvg row = %d with MaxAvg = %f", rowData.maxIndex, rowData.max());
        final int rowOffset = maxD - rowCount;
        int stringsMaxLengthI = 0;
        for (int i = maxD - 1; i >= rowOffset; i--) {
            rowStrings[i] = String.format("Average of row %d = %f", i - rowOffset, rowData.array[i - rowOffset]);
            if (rowStrings[i].length() > rowStrings[stringsMaxLengthI].length())
                stringsMaxLengthI = i;
        }
        final int stringsMaxLength = Math.max(rowStringsLast.length(), rowStrings[stringsMaxLengthI].length());
        final int colOffset = maxD - colCount;
        System.out.printf("%d == %d%n", maxD, rowStrings.length);
        System.out.printf("colOffset = %d%n", colOffset);
        for (int i = 0; i < maxD; i++) {
            System.out.println(i);
            System.out.printf("%-" + stringsMaxLength + "s\t", rowStrings[i]);
            if (i < colOffset)
                System.out.println();
            else {
                System.out.printf("Average of col %d = %f%n", i - colOffset, colData.array[i - colOffset]);
            }
        }
        System.out.printf("%-" + stringsMaxLength + "s\tMaxAvg col = %d with MaxAvg = %f", rowStringsLast, colData.maxIndex, colData.max());
    }
    private static ArrayAndMaxIndex sumToAvg(int[] sums, int len) {
        final ArrayAndMaxIndex r = new ArrayAndMaxIndex(sums.length);
        for (int i = 0; i < sums.length; i++) {
            final double avg = (double) sums[i] / len;
            r.array[i] = avg;
            if (avg > r.array[r.maxIndex])
                r.maxIndex = i;
        }
        return r;
    }
    private static class ArrayAndMaxIndex {
        public double[] array;
        public int maxIndex = 0;

        public ArrayAndMaxIndex(int len) {
            array = new double[len];
        }

        public double max() {
            return array[maxIndex];
        }
    }
}
