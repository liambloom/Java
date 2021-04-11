package dev.liambloom.softwareEngineering.chapter7;

import java.util.Arrays;
import java.util.Random;

public class MrM2DArrayProblems {
    protected static final Random r = new Random();

    public static void main(String[] args) {
        boolean seats[][] = {  
            {false, false, false, false, false, false, false, false},
            { false, false, false, false, false, false, false, false}
        };



        p10(seats);
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
        for (int i = 0; i < maxD; i++) {
            System.out.printf("%-" + stringsMaxLength + "s\t", rowStrings[i]);
            if (i < colOffset)
                System.out.println();
            else {
                System.out.printf("Average of col %d = %f%n", i - colOffset, colData.array[i - colOffset]);
            }
        }
        System.out.printf("%-" + stringsMaxLength + "s\tMaxAvg col = %d with MaxAvg = %f", rowStringsLast, colData.maxIndex, colData.max());
    }
    private static ArrayAndMaxIndex sumToAvg(final int[] sums, final int len) {
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

    public static void p5(int[][] a) {
        int sum = 0;
        if (a.length >= 1) {
            sum += sumOfArray(a[0]);
            for (int i = 1; i < a.length - 1; i++) {
                if (a[i].length >= 1) {
                    sum += a[i][0];
                    if (a[i].length >= 2)
                        sum += a[i][a[i].length - 1];
                }
            }
            if (a.length >= 2)
                sum += sumOfArray(a[a.length - 1]);
        }
        System.out.printf("Sum of the edges = %d%n", sum);
    }
    private static int sumOfArray(final int[] a) {
        int sum = 0;
        for (int e : a)
            sum += e;
        return sum;
    }

    public static void p6(int[][] a) {
        int sum = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i].length != a.length)
                throw new IllegalArgumentException("Cannot find sum of diagonals of a non-square array");
            sum += a[i][i];
            if (a.length % 2 == 0 || a.length / 2 != i)
                sum += a[i][a.length - 1 - i];
        }
        System.out.printf("BOTH diagonal sum = %d%n", sum);
    }

    public static void p7(final String[][] words) {
        int longestRow = 0;
        int longestColumn = 0;
        int longestVowels = 0;
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words[i].length; j++) {
                int vowels = 0;
                final String word = words[i][j];
                for (int k = 0; k < word.length(); k++) {
                    switch (Character.toLowerCase(word.charAt(k))) {
                        case 'a':
                        case 'e':
                        case 'i':
                        case 'o':
                        case 'u':
                            vowels++;
                    }
                }
                if (vowels >= longestVowels) {
                    longestRow = i;
                    longestColumn = j;
                    longestVowels = vowels;
                }
            }
        }
        System.out.printf("%s     row = %d   col = %d    vowel count = %d", 
            words[longestRow][longestColumn], longestRow, longestColumn, longestVowels);
    }

    public static void p8(final int[][] a) {
        int points = 0;
        for (int i = 0; i < 3; i++) {
            int row, col, hp;
            System.out.printf("Hit row: %d\tcol: %d     Point: %d%n", 
                row = r.nextInt(a.length), col = r.nextInt(a[row].length), hp = a[row][col]);
            points += hp;
        }
        System.out.println(" Sum = " + points);
    }

    // I know you said transpose it then print it out, but why do
    // that when you can do both at the same time, which is probably
    // more efficient
    public static void p9(final int[][] a) {
        for (int i = 0; i < a.length; i++) {
            if (a[i].length != a.length)
                throw new IllegalArgumentException("Cannot transpose a non-square array");
            for (int j = 0; j < i; j++)
                System.out.print(a[i][j] + ", ");
            for (int j = i; j < a.length; j++) {
                final int temp = a[j][i];
                a[j][i] = a[i][j];
                a[i][j] = temp;
                System.out.print(temp + ", ");
            }
            System.out.println();
        }
    }

    public static final int FIRST_CLASS_SEATS = 3;
    public static void p10(boolean[][] seats) {
        SeatGroup.main(
            new SeatGroup2d("first", seats, 0, FIRST_CLASS_SEATS), 
            new SeatGroup2d("second", seats, FIRST_CLASS_SEATS, Integer.MAX_VALUE)
        );
    }
    private static class SeatGroup2d implements SeatGroup {
        public final String name;
        public final boolean[][] seats;
        protected final int start;
        protected final int end;
        protected int[] firstEmpty = null;

        public SeatGroup2d(final String name, final boolean[][] seats, final int start, final int end) {
            this.name = name;
            this.seats = seats;
            this.start = start;
            this.end = end;
            for (int i = 0; i < seats.length && firstEmpty == null; i++) {
                if (seats[i].length > start)
                    firstEmpty = new int[] { i, start };
            }
        }

        public void add() {
            if (!isFull()) {
                seats[firstEmpty[0]][firstEmpty[1]] = true;
                updateFirstEmpty();
            }
        }

        protected void updateFirstEmpty() {
            if (isFull())
                return;
            if (updateFirstEmptyRow())
                return;
            for (firstEmpty[0]++; firstEmpty[0] < seats.length; firstEmpty[0]++) {
                firstEmpty[1] = start;
                if (updateFirstEmptyRow())
                    return;
            }
            firstEmpty = null;
        }

        protected boolean updateFirstEmptyRow() {
            boolean[] row = seats[firstEmpty[0]];
            final int end = Math.min(row.length, this.end);
            for (; firstEmpty[1] < end; firstEmpty[1]++) {
                if (!row[firstEmpty[1]])
                    return true;
            }
            return false;
        }

        public boolean isFull() {
            return firstEmpty == null;
        }

        public String getName() {
            return name;
        }
    }
}