package io.github.liambloom.softwareEngineering.chapter11.gridProblem;

import io.github.liambloom.softwareEngineering.Globals;
import java.util.*;
import java.util.regex.*;

public class CLI {
    public static final Pattern pointRegex = Pattern.compile("\\(\\s*(\\d+)\\s*,\\s*(\\d+)\\s*\\)");

    public static final int COLUMN_WIDTH = 2;
    public static final char VERTICAL = '\u2502';
    public static final char HORIZONTAL = '\u2500';
    public static final char TOP_LEFT = '\u250c';
    public static final char TOP_RIGHT = '\u2510';
    public static final char BOTTOM_LEFT = '\u2514';
    public static final char BOTTOM_RIGHT = '\u2518';
    public static final char SOMETHING = ' ';
    public static final char NOTHING = '\u2588';

    public final Grid grid;
    private final String H_BORDER;
    private final int NUM_WIDTH;
    private final String LEFT_BORDER;

    public void main() {
        final Scanner s = new Scanner(System.in);
        /*final Grid grid = new Grid();
        final CLI cli = new CLI(grid);*/
        System.out.printf("This grid has %d objects on it%n", grid.objectCount());
        draw();
        while (grid.containsObjects()) {
            System.out.print("Enter a point to remove an object (or 'quit' to quit): ");
            final String point = s.nextLine().trim();
            if (point.toLowerCase().equals("quit"))
                System.exit(0);
            final Matcher matcher = pointRegex.matcher(point);
            if (matcher.matches()) {
                if (grid.alter(Integer.parseInt(matcher.group(2)) - 1, Integer.parseInt(matcher.group(1)) - 1))
                    draw();
                else
                    System.out.println("No object found at point: " + point);
            } else {
                System.out.println(point + " is not a valid point. Please enter a point in the form (x, y)");
            }
        }
        System.out.println("No points left");
        s.close();
    }

    public CLI() {
        this(new Grid());
    }

    public CLI(Grid grid) {
        this.grid = grid;
        if (Math.floor(Math.log10(grid.grid()[0].length) + 1) > COLUMN_WIDTH)
            Main.error("The grid is too wide");
        NUM_WIDTH = (int) Math.floor(Math.log10(grid.grid().length) + 1);
        LEFT_BORDER = Globals.repeat(' ', NUM_WIDTH) + ' ';
        H_BORDER = Globals.repeat(HORIZONTAL, COLUMN_WIDTH * grid.grid()[0].length);
    }


    public void draw() {
        System.out.print(LEFT_BORDER + ' ');
        for (int i = 0; i < grid.grid()[0].length; i++)
            System.out.printf("%" + COLUMN_WIDTH + "d", i + 1);
        System.out.println();
        System.out.println(LEFT_BORDER + TOP_LEFT + H_BORDER + TOP_RIGHT);
        for (int i = 0; i < grid.grid().length; i++) {
            System.out.printf("%" + NUM_WIDTH + "d %c", i + 1, VERTICAL);
            for (boolean cell : grid.grid()[i])
                System.out.print(Globals.repeat(cell ? SOMETHING : NOTHING, COLUMN_WIDTH));
            System.out.println(VERTICAL);
        }
        System.out.println(LEFT_BORDER + BOTTOM_LEFT + H_BORDER + BOTTOM_RIGHT);
    }
}
