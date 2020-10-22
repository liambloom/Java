package io.github.liambloom.softwareEngineering.chapter11.polygonComparable;

public class Parallelogram extends Trapezium {
    // Constructors
    public Parallelogram() {
        super();
    }
    
    public Parallelogram(double base, double height) {
        super(base, base, height);
    }

    @Override
    public String toString() {
        return "Parallelogram and I am also a " + super.toString() + 
            (Thread.currentThread().getStackTrace()[2].getClassName().startsWith(getClass().getPackageName()) 
                ? "" : " AND my area = " + getMyArea());
    }
}
