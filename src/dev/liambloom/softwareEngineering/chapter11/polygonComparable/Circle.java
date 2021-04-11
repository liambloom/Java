package dev.liambloom.softwareEngineering.chapter11.polygonComparable;

public class Circle extends Ellipse {
    // Constructors
    public Circle() {
        this(0);
    }

    public Circle(double r) {
        super(r, r);
    }

    // Inherited
    @Override
    public String toString() {
        return "Circle and I am also an " + super.toString();
    }
}
