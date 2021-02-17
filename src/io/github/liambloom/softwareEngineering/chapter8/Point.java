package io.github.liambloom.softwareEngineering.chapter8;

// This is the Point class
// It does not have the encapsulation added in section 8.4, as it is unnecessary to the property functioning of the class

public class Point {
    // PUBLIC. There is no reason for these fields to be private, as there is no such thing as an illegal state for a point.
    public int x;
    public int y;

    public Point (int x, int y) {
        setLocation(x, y);
    }
    public Point () {
        this(0, 0);
    }
    public Point (Point p) { // Self Check 21
        setLocation(p);
    }

    private int getDx (Point p) { // This is private because it is a utility function, there is no reason for the client to use it
        return Math.abs(this.x - p.x);
    }
    private int getDy (Point p) {
        return Math.abs(this.y - p.y);
    }
    public double distance (Point p) { // Self Check 10
        return Math.sqrt(Math.pow(getDx(p), 2) + Math.pow(getDy(p), 2)); // For this, I feel like the use of this helps make it clear which point is being used.
    }
    public double manhattanDistance (Point p) { // Exercise 3
        return getDx(p) + getDy(p);
    }
    public double slope (Point p) { // Exercise 5
        if (equals(p)) throw new IllegalArgumentException("You cannot have a slope between two identical points");
        return (double) getDy(p) / getDx(p);
    }
    public boolean isVertical (Point p) { // Exercise 4
        return slope(p) == Double.POSITIVE_INFINITY;
    }
    public boolean isHorizontal (Point p) {
        return slope(p) == 0;
    }
    public boolean isCollinear (Point p1, Point p2) { // Exercise 6
        return Math.round(1e5 * slope(p1)) == Math.round(1e5 * p1.slope(p2));
    }
    public int quadrant () { // Exercise 1
        if (x == 0 || y == 0) return 0;
        if (x > 0) {
            if (y > 0) return 1;
            else return 4;
        }
        else {
            if (y > 0) return 2;
            else return 3;
        }
    }

    public void setLocation (int x, int y) { // This mutator provides convenience to the client code, so it is good. Encapsulation does the opposite, so it's bad
        this.x = x;
        this.y = y;
    }
    public void setLocation (Point p) {
        setLocation(p.x, p.y);
    }
    public void transform (int dx, int dy) {
        setLocation(x + dx, y + dy);
    }
    public void flip () { // Exercise 2
        setLocation(-x, -y);
    }

    public String toString () { // Self Check 14
        return this.getClass().getName() + "[x=" + x + ",y=" + y + "]";
    }
    public String toPointNotation () {
        return "(" + x + ", " + y + ")";
    }
    public boolean equals (Point p) {
        return this.x == p.x && this.y == p.y;
    }
}
