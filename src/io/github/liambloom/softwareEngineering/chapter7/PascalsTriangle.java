package io.github.liambloom.softwareEngineering.chapter7;

public class PascalsTriangle {
    public static void method1(final int rows) {
        final int[][] matrix = new int[rows][rows * 2 - 1];

        // fill 1s
        for (int i = 0; i < rows; i++) {
            matrix[i][rows - 1 + i] = 1;
            if (i != rows)
                matrix[i][rows - 1 - i] = 1;
        }

        // fill rest
        for (int i = 0; i < rows; i++) {
            for (int j = rows - i + 1; j < rows + i - 1; j += 2) {
                matrix[i][j] = matrix[i - 1][j - 1] + matrix[i - 1][j + 1];
            }
        }

        // print
        final int length = (int) Math.ceil(Math.log10(matrix[rows - 1][rows - (rows % 2 == 0 ? 0 : 1)] + 1));
        for (int[] row : matrix) {
            for (int cell : row) {
                if (cell == 0)
                    System.out.print(" ".repeat(length + 1));
                else
                    System.out.printf("%" + (length + 1) + "d", cell);
            }
            System.out.println();
        }
    }
}
