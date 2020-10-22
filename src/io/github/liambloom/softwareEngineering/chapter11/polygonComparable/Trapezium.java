package io.github.liambloom.softwareEngineering.chapter11.polygonComparable;

/**
 * This is just called a trapezoid in American and Canadian English.
 */

public class Trapezium extends Quadrilaterals {
    // Constructors
    public Trapezium() {
        super();
    }

    public Trapezium(double base1, double base2, double height) {
        super(base1, base2, height);
    }

    // Inherited
    @Override
    public void calculateArea() {
        setMyArea((getMyBase1() + getMyBase2()) / 2 * getMyHeight());
    }

    @Override
    public String toString() {
        return "Trapezium and I am also a " + super.toString() + 
            (Thread.currentThread().getStackTrace()[2].getClassName().startsWith(getClass().getPackageName()) 
                ? "" : " AND my area = " + getMyArea());
    }
}
