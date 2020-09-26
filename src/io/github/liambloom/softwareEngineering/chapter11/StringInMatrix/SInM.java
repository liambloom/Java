package io.github.liambloom.softwareEngineering.chapter11.StringInMatrix;

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
        String s = "AABCDE";           
        char M[][] = { 
            {'A','B','A','A'}, 
            {'B','C','D','A'}, 
            {'A','B','C','B'}, 
            {'E','X','D','Z'},
            {'A','B','A','A'}, 
            {'B','C','C','A'}, 
            {'E','D','C','B'}, 
            {'E','X','D','Z'} 
        };
        if (!recursive_helper_top(M, s, new Point(0, 0))) 
            System.out.println("answer = NO");
    }
    public static boolean recursive_helper_top(char[][] M, String s, Point startAt) {
        if (M[startAt.x][startAt.y] == s.charAt(0)) {
            LinkedList<Point> coordinates = new LinkedList<>();
            coordinates.add(startAt);
            if (recursive_helper(M, s.substring(1), coordinates))
                return true;
        }
        startAt.transform(0, 1);
        if (startAt.y == M[0].length)
            startAt.setLocation(startAt.x + 1, 0);
        if (startAt.x == M.length)
            return false;
        return recursive_helper_top(M, s, startAt);
    }
    public static boolean recursive_helper(char[][] M, String s, LinkedList<Point> coordinates) {
        if (s.length() == 0) {
            done(coordinates);
            return true;
        }
        // This is stored in a variable just for convenience, to make it shorter
        Function<Point, Boolean> tryPoint = point -> tryPoint(M, s, coordinates, point);
        final Point here = coordinates.getLast();
        return
            tryPoint.apply(here.getTransformed(-1, 0)) ||
            tryPoint.apply(here.getTransformed(0, -1)) ||
            tryPoint.apply(here.getTransformed(0, 1)) || 
            tryPoint.apply(here.getTransformed(1, 0));
        
    }
    public static boolean tryPoint(char[][] M, String s, LinkedList<Point> coordinates, Point point) {
        @SuppressWarnings("unchecked")
        final LinkedList<Point> clone = (LinkedList<Point>) coordinates.clone();
        return 0 <= point.x && point.x < M.length && 0 <= point.y && point.y < M[0].length &&
            !coordinates.contains(point) && M[point.x][point.y] == s.charAt(0) && clone.add(point) && recursive_helper(M, s.substring(1), clone);
    }
    public static void done(LinkedList<Point> coordinates) {
        System.out.println("answer = YES");
        System.out.println("origin = " + coordinates.getFirst().toPointNotation());
        System.out.print("coordinates: " + coordinates);
    }
    public static String deepToString(Object o) {
        if (Iterable.class.isAssignableFrom(o.getClass())) {
            String s = "";
            @SuppressWarnings("unchecked")
            Iterator<Object> iter = ((Iterable<Object>) o).iterator();
            if (iter.hasNext()) 
                s += deepToString(iter.next());
            while (iter.hasNext()) 
                s += ", " + deepToString(iter.next());
            return "[" + s + "]";
        }
        else if (o instanceof Point) 
            return ((Point) o).toPointNotation();
        else
            return o.toString();
    }
}
