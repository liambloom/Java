package dev.liambloom.softwareEngineering.chapter8.programmingProjects;

public class RationalNumber { // PP1
    private final int numerator;
    private final int denominator; // Should always be positive

    public RationalNumber(int numerator, int denominator) {
        if (denominator == 0)
            throw new IllegalArgumentException();
        final int gcd = gcd(numerator *= Integer.signum(denominator), denominator = Math.abs(denominator));
        this.numerator = numerator / gcd;
        this.denominator = denominator / gcd;
    }

    public RationalNumber() {
        this(0, 1);
    }

    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    // This is one of my favorite things about rust: 
    // https://doc.rust-lang.org/std/ops/index.html
    public RationalNumber add(RationalNumber o) {
        final int lcm = lcm(this.denominator, o.denominator);
        return new RationalNumber(this.numerator * lcm / this.denominator + o.numerator * lcm / o.denominator, lcm);
    }

    public RationalNumber subtract(RationalNumber o) {
        final int lcm = lcm(this.denominator, o.denominator);
        return new RationalNumber(this.numerator * lcm / this.denominator - o.numerator * lcm / o.denominator, lcm);
    }

    public RationalNumber multiply(RationalNumber o) {
        return new RationalNumber(this.numerator * o.numerator, this.denominator * o.denominator);
    }

    public RationalNumber divide(RationalNumber o ) {
        return new RationalNumber(this.numerator * o.denominator, this.denominator * o.numerator);
    }

    protected static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    protected static int lcm(int a, int b) {
        return a * (b / gcd(a, b));
    }

    double getDecimalValue() {
        return (double) numerator / denominator;
    }

    public String toString() {
        return getDecimalValue() % 1 == 0 ? Integer.toString(numerator) : numerator + "/" + denominator;
    }
}
