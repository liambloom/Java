package io.github.liambloom.softwareEngineering.chapter11.gridProblem;

import java.util.*;
import java.util.function.BiConsumer;

public class Grid implements Cloneable {
    public static final boolean[][] DEFAULT_GRID = {
        { false, false, false, false, false, false, false, false, false },
        { false, true,  true,  true,  true,  true,  true,  true,  false },
        { false, true,  false, false, false, false, false, false, false },
        { false, true,  false, true,  false, true,  false, true,  true  },
        { false, true,  false, true,  false, false, false, false, true  },
        { false, true,  false, true,  true,  true,  true,  false, false },
        { false, true,  false, false, false, false, true,  false, false },
        { false, true,  true,  true,  true,  false, true,  true,  true  },
        { false, false, false, false, false, false, true,  false, false },
    };

    public Grid() {
        this(clone2d(DEFAULT_GRID));
    }
    public Grid(boolean[][] grid) {
        if (!isSquare(grid))
            throw new IllegalArgumentException("Grid must be a square");
        this.grid = grid;
        height = grid.length;
        width = grid[0].length;
    }


    public final int width;
    public final int height;
    private final boolean[][] grid;
    BiConsumer<Integer, Integer> afterAlter = null;

    public boolean[][] grid() {
        return clone2d(grid);
    }

    public static Grid checkerBoard(int size) {
        boolean[][] grid = new boolean[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = (i + j) % 2 == 0;
            }
        }
        return new Grid(grid);
    }

    protected static boolean[][] clone2d(boolean[][] grid) {
        boolean[][] clone = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            clone[i] = Arrays.copyOf(grid[i], grid[i].length);
        }
        return clone;
    }

    protected static boolean isSquare(boolean[][] matrix) {
        if (matrix.length <= 1)
            return true;
        final int cols = matrix[0].length;
        for (boolean[] row : matrix) {
            if (row.length != cols)
                return false;
        }
        return true;
    }

    /*private boolean isArg(String arg, String argName) {
        return arg.equals("-" + argName) || arg.equals("--" + argName);
    }*/

    // The method header that was given for this was not static
    // This doesn't make much sense since the thing it's operating on is the grid, which is an argument
    // My guess is that it is from a C/C++ textbook, since those allow functions at the top level
    public boolean alter(int row, int col) {
        if (!grid[row][col])
            return false;
        grid[row][col] = false;
        if (afterAlter != null) 
            afterAlter.accept(row, col);
        if (row > 0)
            alter(row - 1, col);
        if (col > 0)
            alter(row, col - 1);
        if (row + 1 < grid.length)
            alter(row + 1, col);
        if (col + 1 < grid[0].length)
            alter(row, col + 1);
        return true;
    }

    public boolean containsObjects() {
        for (boolean[] row : grid) {
            for (boolean cell : row) {
                if (cell)
                    return true;
            }
        }
        return false;
    }

    public Grid clone() {
        return new Grid(grid());
    }
}
