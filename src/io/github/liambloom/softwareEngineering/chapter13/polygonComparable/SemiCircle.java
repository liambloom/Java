package io.github.liambloom.softwareEngineering.chapter13.polygonComparable;

public class SemiCircle extends Rounds {
    // Constructors
    public SemiCircle() {
        this(0);
    }

    public SemiCircle(double r) {
        super(r);
    }

    // Inherited
    @Override
    public void calculateArea() {
        setMyArea(Math.PI * Math.pow(getMyRadius(), 2) / 2);
    }

    @Override
    public String toString() {
        return "SemiCircle and I am also a " + super.toString() + " AND my area = " + getMyArea();
    }
}
