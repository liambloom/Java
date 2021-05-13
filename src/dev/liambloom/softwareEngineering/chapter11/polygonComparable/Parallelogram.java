package dev.liambloom.softwareEngineering.chapter11.polygonComparable;

public class Parallelogram extends Trapezoid {
    // Constructors
    public Parallelogram() {
        super();
    }
    
    public Parallelogram(double base, double height) {
        super(base, base, height);
    }

    @Override
    public String toString() {
        return "Parallelogram and I am also a " + super.toString();
    }
}
