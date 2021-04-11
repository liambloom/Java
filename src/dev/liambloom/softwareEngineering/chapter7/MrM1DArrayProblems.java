package dev.liambloom.softwareEngineering.chapter7;

import java.util.Arrays;

public class MrM1DArrayProblems {
    public static void p1(final int[] a) {
        if (a.length == 0)
            throw new IllegalArgumentException("There is not min and max for an empty array");
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (final int e : a) {
            min = Math.min(min, e);
            max = Math.max(max, e);
        }
        System.out.printf("largest = %d smallest = %d%n", max, min);
    }

    public static void p2(final int[] a, final int k) {
        int eq = 0;
        int lt = 0;
        int gt = 0;
        for (final int e : a) {
            if (e == k)
                eq++;
            else if (e < k)
                lt++;
            else
                gt++;
        }
        System.out.printf("k count = %d    \tLess that k count = %d\tGreater tha k count = %d", eq, lt, gt);
    }

    public static void p3(final String[] a) {
        if (a.length == 0)
            throw new IllegalArgumentException("There is not longest and shortest for an empty array");
        int min = Integer.MAX_VALUE;
        int iMin = -1;
        int max = 0;
        int iMax = -1;
        for (int i = 0; i < a.length; i++) {
            final int length = a[i].length();
            if (min > length) {
                iMin = i;
                min = length;
            }
            if (max < length) {
                iMax = i;
                max = length;
            }
        }
        System.out.printf("longest = \u201c%s\u201d   position = %d       \tshortest = \u201c%s\u201d  position = %d%n",
                a[iMax], iMax, a[iMin], iMin);
    }

    public static void p4(final int[] a) {
        int positive = 0;
        int negative = 0;
        for (final int e : a) {
            if (e > 0)
                positive++;
            else if (e < 0)
                negative++;
        }
        System.out.printf("positive count = %d\tnegative count = %d%n", positive, negative);
    }
    
    public static void p5(final double[] a) {
        double sum = 0;
        for (double e : a)
            sum += e;
        System.out.printf("sum = %d  average = %d%n", sum, sum / a.length);
    }

    public static void p6(final int[] a) {
        for (int i = 0; i < a.length / 2; i++) {
            final int temp = a[i];
            a[i] = a[a.length - i - 1];
            a[a.length - i - 1] = temp;
        }
        System.out.println(Arrays.toString(a));
    }

    public static void p7(final int[] a) {
        if (a.length < 3)
            throw new IllegalArgumentException("The array is too short");
        final int[] largest = new int[3];
        Arrays.fill(largest, Integer.MIN_VALUE);
        for (int e : a) {
            int insert = e;
            for (int i = 0; i < largest.length; i++) {
                if (insert > largest[i]) {
                    final int temp = insert;
                    insert = largest[i];
                    largest[i] = temp;
                }
            }
        }
        final int sum = largest[1] + largest[2];
        final boolean bigger = largest[0] > sum;
        System.out.printf("largest1 = %d   largest2 = %d  largest3 = %d   \t%s  %d %c %d%n",
            largest[0], largest[1], largest[2], bigger ? "YES" : "NO", largest[0], bigger ? '>' : '<', sum);
    }
    public static void p8(int[] a) {
        if (a.length == 0) {
            System.out.println("mean = none   median = none   mode = none");
            return;
        }
        Arrays.sort(a);
        int sum = 0;
        int modeCount = 0;
        Integer[] modes = new Integer[a.length / 2];
        int currentCount = 0;
        Integer previous = null;
        for (int e : a) {
            sum += e;
            if (previous != null && previous == e)
                currentCount++;
            else {
                previous = e;
                currentCount = 1;
            }
            if (currentCount == modeCount) {
                for (int i = 0; i < modes.length; i++) {
                    if (modes[i] == null) {
                        modes[i] = e;
                        break;
                    }
                }
            }
            else if (currentCount > modeCount) {
                modeCount = currentCount;
                modes[0] = e;
                for (int i = 1; i < modes.length; i++) {
                    if (modes[i] == null)
                        break;
                    else
                        modes[i] = null;
                }
            }
            
        }
        System.out.printf("mean = " + $.numberToString((double) sum / a.length, 2) + "   median = %d   mode = ", a.length % 2 == 0 ? (a[a.length / 2] + a[a.length / 2 - 1]) / 2 : a[a.length / 2]);
        System.out.print(modes[0]);
        for (int i = 1; i < modes.length && modes[i] != null; i++)
            System.out.print(", " + modes[i]);
        System.out.println();
    }

    public static int[] p9(int n) { // to binary
        if (n > 255 || n < 0)
            throw new IllegalArgumentException("Only converts numbers between 0 and 255 to binary");
        final int[] bin = new int[8];
        for (int i = 7; i >= 0; i--) {
            final short pow = (short) Math.pow(2, i);
            System.out.printf("%d %s %d%n", n, n >= pow ? ">=" : "<" , pow);

            bin[7 - i] = n >= pow ? 1 : 0;
            n %= pow;
        }
        return bin;
    }

    public static final int FIRST_CLASS_SEATS = 3;
    public static void p10(boolean[] seats) {
        SeatGroup.main(
            new SeatGroup1d("first", seats, 0, Math.min(FIRST_CLASS_SEATS, seats.length)),
            new SeatGroup1d("second", seats, FIRST_CLASS_SEATS, seats.length)
        );
    }
}

class SeatGroup1d implements SeatGroup {
    public final int start;
    public final int end;
    public final String name;
    private final boolean[] allSeats;
    private int lastFull;

    public SeatGroup1d(String name, boolean[] allSeats, int start, int end) {
        this.name = name;
        this.allSeats = allSeats;
        this.start = start;
        this.end = end;
        lastFull = start - 1;
        updateLastFull();
    }

    public void add() {
        if (!isFull()) {
            allSeats[++lastFull] = true;
            updateLastFull();
        }
    }

    private void updateLastFull() {
        for (; lastFull + 1 < end && allSeats[lastFull + 1]; lastFull++);
    }

    public boolean isFull() {
        assert lastFull + 1 == end || !allSeats[lastFull + 1];
        return lastFull + 1 == end;
    }

    public String getName() {
        return name;
    }
}