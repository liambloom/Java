package io.github.liambloom.softwareEngineering.chapter11.polygonComparable;

/**
 * This is specifically an Isosceles trapezoid, as not all trapezoids can be
 * represented with just two bases and a height
 */

public class Trapezoid extends Trapezium {
    // Constructors
    public Trapezoid() {
        super();
    }

    public Trapezoid(double base1, double base2, double height) {
        super(base1, base2, height);
    }

    // Inherited
    @Override
    public String toString() {
        return "Trapezoid and I am also a " + super.toString();
    }
}
