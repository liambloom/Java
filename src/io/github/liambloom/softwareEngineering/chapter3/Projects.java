package io.github.liambloom.softwareEngineering.chapter3;

public class Projects { // 1 & 6
    public static void tree (int segments, int height) {
        for (int segment = 0; segment < segments; segment++) {
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < segments - segment + height - i - 2; j++) {
                    System.out.print(' ');
                }
                for (int j = 0; j < 1 + 2 * (segment + i); j++) {
                    System.out.print('*');
                }
                System.out.println();
            }
        }
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < segments + height - 2; j++) {
                System.out.print(' ');
            }
            System.out.println('*');
        }
        for (int i = 0; i < segments + height - 5; i++) {
            System.out.print(' ');
        }
        for (int i = 0; i < 7; i++) {
            System.out.print('*');
        }
    }
    private static final String[] dayNames = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
    public static void calendar (int days, int firstSun) {
        if (days > 31 || days < 28) throw new IllegalArgumentException("You cannot have " + days + " days in a month");
        else if (firstSun < 1 || firstSun > 7) throw new IllegalArgumentException("The first sunday of the month cannot be the " + firstSun + "th of the month");
        for (int i = 0; i < 7; i++) {
            System.out.print("  " + dayNames[i] + "  ");
        }
        System.out.println();
        line('+', '-', 50, 7);
        for (int weekOf = firstSun - 7; weekOf < days; weekOf += 7) {
            for (int i = 0; i < 7; i++) {
                int today = weekOf + i;
                if (today <= days && today >= 1) section(today);
                else section();
            }
            System.out.println('|');
        }
        line('+', '-', 50, 7);
    }
    private static void line (char border, char main, int width, int interval) {
        for (int i = 0; i < width / interval; i++) {
            System.out.print(border);
            for (int j = 0; j < interval - 1; j++) {
                System.out.print(main);
            }
        }
        System.out.println(border);
    }
    private static void section (int day) {
        System.out.print('|');
        for (int i = 0; i < 3 - (int)Math.log10(day); i++) {
            System.out.print(' ');
        }
        System.out.print(day);
        for (int i = 0; i < 2; i++) {
            System.out.print(' ');
        }
    }
    private static void section () {
        System.out.print('|');
        for (int i = 0; i < 6; i++) {
            System.out.print(' ');
        }
    }
}
