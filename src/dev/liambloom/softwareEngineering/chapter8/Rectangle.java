package dev.liambloom.softwareEngineering.chapter8;

import dev.liambloom.softwareEngineering.Globals;

public class Rectangle { // Exercise 18
    public final int x;
    public final int y;
    public final int width;
    public final int height;

    public Rectangle (int x, int y, int width, int height) {
        if (width < 0) throw new IllegalArgumentException("width cannot be negative");
        if (height < 0) throw new IllegalArgumentException("height cannot be negative");

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    public Rectangle (Point p, int width, int height) { // Exercise 19
        this(p.x, p.y, width, height);
    }

    public boolean contains (int x, int y) { // Exercise 20
        return this.x <= x && this.y <= y && this.x + this.width >= x && this.y + this.height >= y;
    }
    public boolean contains (Point p) { // Also Exercise 20
        return contains(p.x, p.y);
    }

    public Rectangle union (Rectangle rect) { // Exercise 21
        final int x = Math.min(this.x, rect.x);
        final int y = Math.min(this.y, rect.y);
        return new Rectangle(x, y, Math.max(this.x + this.width, rect.x + rect.width) - x, Math.max(this.y + this.height, rect.y + rect.height) - y);
    }
    public Rectangle intersection (Rectangle rect) { // Exercise 22
        final int x = Globals.Math.max(this.x, rect.x, 0);
        final int y = Globals.Math.max(this.y, rect.y, 0);
        return new Rectangle(x, y, Math.max(0, Math.min(this.x + this.width, rect.x + rect.width) - x), Math.max(0, Math.max(this.y + this.height, rect.y + rect.height) - y));
    }

    public String toString() {
        return this.getClass().getName() + "[x=" + x + ",y=" + y + ",width=" + width + ",height=" + height + "]";
    }
    public boolean equals (Rectangle rect) {
        return this.x == rect.x && this.y == rect.y && this.width == rect.width && this.height == rect.height;
    }
    public void print () {
        // This I did because it seemed fun and I know how to do it.
        // I know how to do it because I made an entire JS library that draws stuff to the command line
        // Library on Github: https://github.com/liambloom/command-line-draw
        // Library on npm (node package manager): https://www.npmjs.com/package/command-line-draw

        for (int i = 0; i < y; i++) System.out.println();

        final String xString = " ".repeat(x * 2);
        final String horizontal = "\u2500".repeat(width * 2 - 2);
        final String space = " ".repeat(width * 2 - 2);

        System.out.println(xString + "\u250c" + horizontal + "\u2510");
        for (int i = 0; i < height - 2; i++) System.out.println(xString + "\u2502" + space + "\u2502");
        System.out.println(xString + "\u2514" + horizontal + "\u2518");
    }
}
