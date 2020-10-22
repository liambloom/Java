package io.github.liambloom.softwareEngineering.chapter11.polygonComparable;

public class Square extends Rectangle {
    // Constructors
    public Square() {
        super();
    }

    public Square(double s) {
        super(s, s);
    }

    // Inherited
    @Override
    public String toString() {
        return "Square and I am also a " + super.toString();
    }
}
