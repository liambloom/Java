package dev.liambloom.softwareEngineering.chapter11.polygonComparable;

/**
 * Rectangles are both Parallelograms *and* isosceles trapezoids, but java
 * doesn't support multiple inheritance, so I was forced to pick one, so I
 * picked parallelogram
 */
public class Rectangle extends Parallelogram {
    // Constructors
    public Rectangle() {
        super();
    }

    public Rectangle(double base, double height) {
        super(base, height);
    }

    // Inherited
    @Override
    public String toString() {
        return "Rectangle and I am also a " + super.toString();
    }
}
