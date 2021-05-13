package dev.liambloom.softwareEngineering.chapter11.polygonComparable;

public class Trapezoid extends Quadrilaterals {
    // Constructors
    public Trapezoid() {
        super();
    }

    public Trapezoid(double base1, double base2, double height) {
        super(base1, base2, height);
    }

    // Inherited
    @Override
    public void calculateArea() {
        setMyArea((getMyBase1() + getMyBase2()) / 2 * getMyHeight());
    }

    @Override
    public String toString() {
        return "Trapezoid and I am also a " + super.toString();
    }
}
