package io.github.liambloom.softwareEngineering.chapter13.polygonComparable;

public class Ellipse extends Rounds {
    private double r1, r2;

    // Constructors
    public Ellipse() {
        this(0, 0);
    }

    public Ellipse(double r1, double r2) {
        super((r1 + r2) / 2); // This is the average of the radii, IDK what I should put
        this.r1 = r1;
        this.r2 = r2;
    }

    // Inherited
    @Override
    public void calculateArea() {
        setMyArea(r1 * r2 * Math.PI);
    }

    @Override
    public String toString() {
        return "Ellipse and I am also a " + super.toString() + 
            (Thread.currentThread().getStackTrace()[2].getClassName().startsWith(getClass().getPackageName()) 
                ? "" : " AND my area = " + getMyArea());
    }
}
