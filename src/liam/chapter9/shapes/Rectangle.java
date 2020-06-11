package liam.chapter9.shapes;

public class Rectangle implements Shape {
    public final double width;
    public final double height;
    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }
    public double getArea() {
        return width * height;
    }
    public double getPerimeter() {
        return 2.0 * (width + height);
    }

    public boolean equals (Rectangle other) {
        return this.width == other.width && this.height == other.height;
    }
}
