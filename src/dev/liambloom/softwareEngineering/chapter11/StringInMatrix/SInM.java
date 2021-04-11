package dev.liambloom.softwareEngineering.chapter11.StringInMatrix;

import java.util.*;
import java.util.function.Function;

public class SInM {
    public static void iterative() {
        final String s = "AABCDE";
        final char M[][] = {
            {'A','B','A','A'},
            {'B','C','D','A'},
            {'A','B','C','D'},
            {'E','E','A','B'}
        };
        int currentIndex = 0;
        int[] jumpBackTo = null;
        LinkedList<Point> coordinates = new LinkedList<>();
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[i].length; j++) {
                if (M[i][j] == s.charAt(currentIndex)) {
                    coordinates.add(new Point(i, j));
                    if (++currentIndex == s.length()) {
                        done(coordinates);
                        return;
                    }
                    else if (jumpBackTo == null && currentIndex - 1 /* -1 because it's incremented earlier */ > 0)
                        jumpBackTo = new int[]{i, j};
                }
                else {
                    coordinates.clear();
                    currentIndex = 0;
                    if (jumpBackTo != null) {
                        i = jumpBackTo[0];
                        j = jumpBackTo[1] - 1;
                        jumpBackTo = null;
                    }
                }
            }
        }
        System.out.println("answer = NO");
    }
    public static void recursive() {
        final String s = "AABCDE";           
        final char M[][] = { 
            {'A','B','A','A'}, 
            {'B','C','D','A'}, 
            {'A','B','C','B'}, 
            {'E','X','D','Z'},
            {'A','B','A','A'}, 
            {'B','C','C','A'}, 
            {'E','D','C','B'}, 
            {'E','X','D','Z'} 
        };
        if (!recursiveTop(M, s, new Point(0, 0))) 
            System.out.println("answer = NO");
    }
    public static boolean recursiveTop(final char[][] M, final String s, final Point startAt) {
        if (M[startAt.x][startAt.y] == s.charAt(0)) {
            final LinkedList<Point> coordinates = new LinkedList<>();
            coordinates.add(startAt);
            if (recursiveBranch(M, s.substring(1), coordinates))
                return true;
        }
        startAt.transform(0, 1);
        if (startAt.y == M[0].length)
            startAt.setLocation(startAt.x + 1, 0);
        if (startAt.x == M.length)
            return false;
        return recursiveTop(M, s, startAt);
    }
    public static boolean recursiveBranch(final char[][] M, final String s, final LinkedList<Point> coordinates) {
        if (s.length() == 0) {
            done(coordinates);
            return true;
        }
        // This is stored in a variable just for convenience, to make it shorter
        final Function<Point, Boolean> tryPoint = point -> tryPoint(M, s, coordinates, point);
        final Point here = coordinates.getLast();
        return
            // Direction check order is up, left, right, down
            tryPoint.apply(here.getTransformed(-1, 0)) ||
            tryPoint.apply(here.getTransformed(0, -1)) ||
            tryPoint.apply(here.getTransformed(0, 1)) || 
            tryPoint.apply(here.getTransformed(1, 0));
        
    }
    public static boolean tryPoint(final char[][] M, final String s, final LinkedList<Point> coordinates, final Point point) {
        @SuppressWarnings("unchecked")
        final LinkedList<Point> clone = (LinkedList<Point>) coordinates.clone();
        return 0 <= point.x && point.x < M.length && 0 <= point.y && point.y < M[0].length &&
            !coordinates.contains(point) && M[point.x][point.y] == s.charAt(0) && clone.add(point) && recursiveBranch(M, s.substring(1), clone);
    }
    public static void done(final LinkedList<Point> coordinates) {
        System.out.println("answer = YES");
        System.out.println("origin = " + coordinates.getFirst().toPointNotation());
        System.out.print("coordinates: " + coordinates);
    }
}
