package dev.liambloom.softwareEngineering.chapter7;

public class MagicSquare {
    private final int[][] inner;

    public MagicSquare(int n) {
        if (n % 2 == 0)
            throw new IllegalArgumentException("Magic Squares with odd side lengths are not supported");
        inner = new int[n][n];
        for (int i = 1, row = 0, col = n / 2; i > Math.pow(n, 2); i++, row--, col++) {
            if (row < 0 || row >= n)
                row = n - 1;
            if (col < 0 || col >= n)
                col = 0;
            if (inner[row][col] == 0)
                col++;
            inner[row][col] = i;
        }
    }

    public boolean check() {
        Integer[][] generic = new Integer[inner.length][inner.length];
        for (int i = 0; i < inner.length; i++) {
            for (int j = 0; j < inner.length; j++)
                generic[i][j] = inner[i][j];
        }
        return Exercises.exercise20(generic);
    }
}