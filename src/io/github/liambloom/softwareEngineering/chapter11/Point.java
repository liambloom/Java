package io.github.liambloom.softwareEngineering.chapter11;

public class Point extends io.github.liambloom.softwareEngineering.chapter8.Point implements Cloneable {
    public Point(int x, int y) {
        super(x, y);
    }
    public Point() {
        super();
    }
    public Point(Point p) {
        super(p);
    }

    public Point getTransformed(int dx, int dy) {
        return new Point(x + dx, y + dy);
    }
    public Point getFlipped() {
        return new Point(-x, -y);
    }

    public Point clone() {
        return new Point(this);
    }
    public String toString() {
        return this.toPointNotation();
    }
}
