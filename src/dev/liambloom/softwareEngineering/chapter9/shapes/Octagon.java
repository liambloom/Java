package dev.liambloom.softwareEngineering.chapter9.shapes;

public class Octagon implements Shape {
    public final double side;
    private static final double sideMultiplier = 2 * (1 + Math.sqrt(2));

    public Octagon (double side) {
        this.side = side;
    }

    public double getArea () {
        return Octagon.sideMultiplier * Math.pow(side, 2);
    }
    public double getPerimeter() {
        return 8 * side;
    }
}
