package dev.liambloom.softwareEngineering.chapter7;

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
        final int length = (int) Math.ceil(Math.log10(matrix[rows - 1][rows - (rows % 2 == 0 ? 0 : 1)] + 1)) + 1;
        final String space = " ".repeat(length);
        for (int[] row : matrix) {
            for (int cell : row) {
                if (cell == 0)
                    System.out.print(space);
                else
                    System.out.printf("%" + length + "d", cell);
            }
            System.out.println();
        }
    }

    public static void method2(final int rows) {
        final int[][] matrix = new int[rows][];

        // fill 1s
        for (int i = 0; i < rows; i++) {
            matrix[i] = new int[i + 1];
            matrix[i][0] = 1;
            matrix[i][i] = 1;
        }

        // fill rest
        for (int i = 2; i < rows; i++) {
            for (int j = 1; j < i; j++) {
                matrix[i][j] = matrix[i - 1][j] + matrix[i - 1][j - 1];
            }
        }

        // used for both printouts
        final int length = (int) Math.ceil(Math.log10(matrix[rows - 1][rows / 2] + 1)) + 1;
        final String space = " ".repeat(length);

        // print as stored
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j <= i; j++)
                System.out.printf("%-" + length + "d", matrix[i][j]);
            System.out.println();
        }

        // print pretty
        for (int i = 0; i < rows; i++) {
            System.out.print(space.repeat(rows - i - 1));
            for (int j = 0; j <= i; j++)
                System.out.printf("%" + length + "d" + space, matrix[i][j]);
            System.out.println();
        }
    }

    public static void method3(final int rows) {
        final int[][] matrix = new int[rows][];

        // fill 1s
        for (int i = 0; i < rows; i++) {
            matrix[i] = new int[i / 2 + 1];
            matrix[i][0] = 1;
        }

        // fill rest
        for (int i = 2; i < rows; i++) {
            for (int j = 1; j < i / 2 + 1; j++) {
                if (j == (i + 1) / 2)
                    matrix[i][j] = 2 * matrix[i - 1][j - 1];
                else
                    matrix[i][j] = matrix[i - 1][j] + matrix[i - 1][j - 1];
            }
        }

        // used for both printouts
        final int length = (int) Math.ceil(Math.log10(matrix[rows - 1][(rows + 1) / 2 - 1] + 1)) + 1;
        final String space = " ".repeat(length);

        // print as stored
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < i / 2 + 1; j++)
                System.out.printf("%-" + length + "d", matrix[i][j]);
            System.out.println();
        }

        // print pretty
        for (int i = 0; i < rows; i++) {
            System.out.print(space.repeat(rows - i - 1));
            for (int j = 0; j < (i + 1) / 2; j++)
                System.out.printf("%" + length + "d" + space, matrix[i][j]);
            if (i % 2 == 0)
                System.out.printf("%" + length + "d" + space, matrix[i][i / 2]);
            for (int j = (i + 1) / 2 - 1; j >= 0; j--)
                System.out.printf("%" + length + "d" + space, matrix[i][j]);
            System.out.println();
        }
    }

    public static void method4(final int rows) {
        final int[][] matrix = new int[rows - 2][];

        // fill 1st column
        for (int i = 0; i < matrix.length; i++) {
            matrix[i] = new int[i / 2 + 1];
            matrix[i][0] = i + 2;
        }

        // fill rest
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 1; j < i / 2 + 1; j++) {
                if (j == (i + 1) / 2)
                    matrix[i][j] = 2 * matrix[i - 1][j - 1];
                else
                    matrix[i][j] = matrix[i - 1][j] + matrix[i - 1][j - 1];
            }
        }
        
        // used for both printouts
        final int length = (int) Math.ceil(Math.log10(matrix[matrix.length - 1][(matrix.length - 1) / 2] + 1)) + 1;
        final String space = " ".repeat(length);

        // print as stored
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < i / 2 + 1; j++)
                System.out.printf("%-" + length + "d", matrix[i][j]);
            System.out.println();
        }

        // print pretty
        for (int i = -2; i < matrix.length; i++) {
            System.out.print(space.repeat(matrix.length - i - 1));
            System.out.printf("%" + length + "d" + space, 1);
            if (i >= 0) {
                for (int j = 0; j < (i + 1) / 2; j++)
                    System.out.printf("%" + length + "d" + space, matrix[i][j]);
                if (i % 2 == 0)
                    System.out.printf("%" + length + "d" + space, matrix[i][i / 2]);
                for (int j = (i + 1) / 2 - 1; j >= 0; j--)
                    System.out.printf("%" + length + "d" + space, matrix[i][j]);
            }
            if (i != -2)
                System.out.printf("%" + length + "d" + space, 1);
            System.out.println();
        }
    }
}
