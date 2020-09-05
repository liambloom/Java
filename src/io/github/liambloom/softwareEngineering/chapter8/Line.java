package io.github.liambloom.softwareEngineering.chapter8;

public class Line { // Exercise 14
    public final Point p1;
    public final Point p2;

    public Line (Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
    }
    public Line (int x1, int y1, int x2, int y2) { // Exercise 16
        p1 = new Point(x1, y1);
        p2 = new Point(x2, y2);
    }

    public double getSlope () { // Exercise 15
        return p1.slope(p2);
    }
    public boolean isCollinear (Point p) {
        return p.isCollinear(p1, p2);
    }

    public String toString () {
        return this.getClass().getName() + "[" + p1.toPointNotation() + ", " + p2.toPointNotation() + "]";
    }
    public boolean equals (Line line) {
        return this.p1.equals(line.p1) && this.p2.equals(line.p2);
    }
}
