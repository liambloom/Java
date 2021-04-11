package dev.liambloom.softwareEngineering.chapter11.polygonComparable;

public class Rhombus extends Parallelogram {
    // Constructors
    public Rhombus() {
        super();
    }

    public Rhombus(double base, double height) {
        super(base, height);
    }

    // Inherited
    @Override
    public String toString() {
        return "Rhombus and I am also a " + super.toString();
    }
}
