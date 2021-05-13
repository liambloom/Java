package dev.liambloom.softwareEngineering.chapter11.polygonComparable;

public class IsoscelesTrapezoid extends Trapezoid {
    // Constructors
    public IsoscelesTrapezoid() {
        super();
    }

    public IsoscelesTrapezoid(double base1, double base2, double height) {
        super(base1, base2, height);
    }

    // Inherited
    @Override
    public String toString() {
        return "Isosceles Trapezoid and I am also a " + super.toString();
    }
}
