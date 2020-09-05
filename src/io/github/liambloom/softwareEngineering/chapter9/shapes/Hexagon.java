package io.github.liambloom.softwareEngineering.chapter9.shapes;

public class Hexagon implements Shape {
    public final double side;
    private static final double sideMultiplier = 3 * Math.sqrt(3) / 2;

    public Hexagon (double side) {
        this.side = side;
    }

    public double getArea() {
        return sideMultiplier * Math.pow(side, 2);
    }
    public double getPerimeter() {
        return 6 * side;
    }
}
