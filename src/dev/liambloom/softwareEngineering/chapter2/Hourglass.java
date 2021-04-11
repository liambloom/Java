package dev.liambloom.softwareEngineering.chapter2;

public class Hourglass { // this should really create an hourglass object, but that's ch3
    private static int height;
    public static void main (int inputHeight) {
        if (inputHeight % 2 == 1) throw new IllegalArgumentException("Height must be even"); 
        height = inputHeight;
        // hourglass
        line();
        downCone();
        upCone();
        line();
        // diamond
        upCone();
        downCone();
        line();
    }
    public static void downCone () {
        for (int i = height - 4; i >= 0; i -= 2) {
            sand(i, true);
        }
    }
    public static void upCone () {
        for (int i = 0; i <= height - 4; i += 2) {
            sand(i, false);
        }
    }
    public static void line () {
        System.out.print('+');
        for (int i = 0; i < height - 2; i++) {
            System.out.print('-');
        }
        System.out.println('+');
    }
    public static void sand (int grains, Boolean down) {
        System.out.print('|');
        spaces(grains);
        System.out.print(down ? '\\' : '/');
        for (int i = 0; i < grains; i++) {
            System.out.print('.');
        }
        System.out.print(down ? '/' : '\\');
        spaces(grains);
        System.out.println('|');
    }
    private static void spaces (int grains) {
        /*
        8 => 2
        10 => 2.5
         */
        for (int i = 0; i < (height - grains) / 2 - 2; i++) {
            System.out.print(' ');
        }
    }
}
/*
4 => 0
3 => 0.5
2 => 1
1 => 1.5
0 => 2
 */