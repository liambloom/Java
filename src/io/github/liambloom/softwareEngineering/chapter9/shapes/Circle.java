package io.github.liambloom.softwareEngineering.chapter9.shapes;

public class Circle implements Shape {
    public final double radius;
    public Circle(double radius) {
        this.radius = radius;
    }
    public double getArea() {
        return Math.PI * radius * radius;
    }
    public double getPerimeter() {
        return 2.0 * Math.PI * radius;
    }

    public boolean equals (Circle other) {
        return this.radius == other.radius;
    }
}