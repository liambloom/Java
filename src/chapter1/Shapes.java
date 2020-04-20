package chapter1;

public class Shapes {
    public static void x () {
        System.out.println(" * * ");
        System.out.println("  *  ");
        System.out.println(" * * ");
    }
    public static void line (int length) {
        for (int i = 0; i < length; i++) {
            System.out.print("*");
        }
        System.out.println();
    }
    public static void centerLine (int length, int totalSpace) {
        for (int i = 0; i < (totalSpace - length) / 2; i++) {
            System.out.print(" ");
        }
        line(length);
    }
    public static void equals () {
        for (int i = 0; i < 2; i++) {
            line(5);
        }
    }
    public static void equalsX () {
        equals();
        x();
    }
    public static void verticalLine () {
        for (int i = 0; i < 3; i++) {
            System.out.println("  *  ");
        }
    }
    public static void wideCone () {
        centerLine(5, 13);
        centerLine(9, 13);
        line(13);
    }
    public static void grate () {
        System.out.println("* | | | | | *");
    }
}
