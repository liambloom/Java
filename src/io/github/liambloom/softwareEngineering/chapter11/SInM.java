package io.github.liambloom.softwareEngineering.chapter11;

import java.util.*;

public class SInM {
    public static void main(String[] args) {
        recursive();
        /*System.out.println(java.util.Arrays.asList(new char[][]{
     

            {'B','C','D','A'},
            {'A','B','C','D'},
            {'E','E','A','B'}
        }).get(0).getClass());*/
    }
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
        if (!recursive_helper_top(M, s, new Point(0, 0))) {
            System.out.println("answer = NO");
        }
    }
    public static boolean recursive_helper_top(char[][] M, String s, Point startAt) {
        //System.out.println(startAt);
        if (M[startAt.y][startAt.x] == s.charAt(0)) {
            LinkedList<Point> coordinates = new LinkedList<>();
            coordinates.add(startAt);
            if (recursive_helper(M, s.substring(1), coordinates))
                return true;
        }
        startAt.transform(1, 0);
        if (startAt.x == M[0].length)
            startAt.setLocation(0, startAt.y + 1);
        if (startAt.y == M.length)
            return false;
        return recursive_helper_top(M, s, startAt);
    }
    public static boolean recursive_helper(char[][] M, String s, LinkedList<Point> coordinates) {
        if (s.length() == 0) {
            done(coordinates);
            return true;
        }
        if (coordinates.size() == 1)
            System.out.println(coordinates.element());
        java.util.function.Function<Point, Boolean> tryPoint = point -> tryPoint(M, s, coordinates, point);
        //@SuppressWarnings("unchecked")
        //final LinkedList<Point> clone = (LinkedList<Point>) coordinates.clone();
        //final String substr = s.substring(1);
        //final char c0 = s.charAt(0);
        final Point here = coordinates.getLast();
        //Point next;
        return
            tryPoint.apply(here.getTransformed(0, -1)) ||
            tryPoint.apply(here.getTransformed(-1, 0)) ||
            tryPoint.apply(here.getTransformed(1, 0)) || 
            tryPoint.apply(here.getTransformed(0, 1));
            /*here.y > 0 && 
                !coordinates.contains(next = here.getTransformed(0, -1)) && M[next.y][next.x] == c0 && clone.add(next) && recursive_helper(M, substr, clone) ||
            here.x > 0 && 
                !coordinates.contains(next = here.getTransformed(-1, 0)) && M[next.y][next.x] == c0 && clone.add(next) && recursive_helper(M, substr, clone) ||
            here.x + 1 < M[0].length && 
                !coordinates.contains(next = here.getTransformed(1, 0)) && M[next.y][next.x] == c0 && clone.add(next) && recursive_helper(M, substr, clone) ||
            here.y + 1 < M.length &&
                !coordinates.contains(next = here.getTransformed(0, 1)) && M[next.y][next.x] == c0 && clone.add(next) && recursive_helper(M, substr, clone);*/
        
    }
    public static boolean tryPoint(char[][] M, String s, LinkedList<Point> coordinates, Point point) {
        @SuppressWarnings("unchecked")
        final LinkedList<Point> clone = (LinkedList<Point>) coordinates.clone();
        final boolean r =  0 < point.x && point.x < M[0].length && 0 < point.y && point.y < M.length &&
            !coordinates.contains(point) && M[point.y][point.x] == s.charAt(0) && clone.add(point) && recursive_helper(M, s.substring(1), clone);
        System.out.println(coordinates + " + " + point + " => " + r);
        return r;
    }
    public static void done(LinkedList<Point> coordinates) {
        System.out.println("answer = YES");
        System.out.println("origin = " + coordinates.getFirst().toPointNotation());
        System.out.print("coordinates: " + coordinates);
        /*final Iterator<Point> iter = coordinates.iterator();
        System.out.print(iter.next().toPointNotation());
        while (iter.hasNext())
            System.out.print(", " + iter.next().toPointNotation());
        System.out.println("]");*/
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
// Up Left Right Down