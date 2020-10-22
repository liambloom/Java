package io.github.liambloom.softwareEngineering.chapter13.polygonComparable;

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
        return "Rhombus and I am also a " + super.toString() + " AND my area = " + getMyArea();
    }
}
