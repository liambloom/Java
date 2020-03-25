public class Project {
    public static void p1 () {
        for (int i = 0; i < 7; i++) {
            linear(i, 6, -1, '*');
            linear(i, 1, 1, ' ');
            linear(i, 12, -2, '/');
            linear(i, 0, 2, '\\');
            linear(i, 1, 1, ' ');
            linear(i, 6, -1, '*');
            System.out.println();
        }
    }
    public static void p2 () {
        line(8, '+', '-');
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print('|');
                linear(j, 2, -1, ' ');
                System.out.print('^');
                linear(j, 0, 2, ' ');
                System.out.print('^');
                linear(j, 2, -1, ' ');
                System.out.println('|');
            }
        }
        line(8, '+', '-');
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print('|');
                linear(j, 0, 1, ' ');
                System.out.print('v');
                linear(j, 4, -2, ' ');
                System.out.print('v');
                linear(j, 0, 1, ' ');
                System.out.println('|');
            }
        }
        line(8, '+', '-');
    }
    public static void p3 () { // This soulution seems quite inefficient, but I can't find a better one
        line(11, '+', '-');
        p3Grow();
        p3Shrink();
        line(11, '+', '-');
        p3Shrink();
        p3Grow();
        line(11, '+', '-');
    }
    private static void p3Grow () {
        for (int i = 0; i < 4; i++) {
            System.out.print('|');
            linear(i, 4, -1, ' ');
            linear(i, 0, 1, '/');
            System.out.print('*');
            linear(i, 0, 1, '\\');
            linear(i, 4, -1, ' ');
            System.out.println('|');
        }
    }
    private static void p3Shrink () {
        for (int i = 0; i < 4; i++) {
            System.out.print('|');
            linear(i, 1, 1, ' ');
            linear(i, 3, -1, '\\');
            System.out.print('*');
            linear(i, 3, -1, '/');
            linear(i, 1, 1, ' ');
            System.out.println('|');
        }
    }
    public static void p4 () {
        line(12, '|', '"');
        for (int i = 0; i < 4; i++) {
            linear(i, 1, 1, ' ');
            System.out.print('\\');
            linear(i, 8, -2, ':');
            System.out.println('/');
        }
        System.out.println("     ||");
        for (int i = 0; i < 4; i++) {
            linear(i, 4, -1, ' ');
            System.out.print('/');
            linear(i, 2, 2, ':');
            System.out.println('\\');
        }
        line(12, '|', '"');
    }
    public static void p5 () {
        for (int i = 0; i < 5; i++) {
            line(22 - (i * 5), ' ');
            System.out.print("o  *");
            line(5, '*');
            line(5 * i, ' ');
            System.out.println('*');
            line(21 - (i * 5), ' ');
            System.out.print("/|\\ *");
            line(5 * (i + 1), ' ');
            System.out.println('*');
            line(21 - (i * 5), ' ');
            System.out.print("/ \\ *");
            line(5 * (i + 1), ' ');
            System.out.println('*');
        }
        line(32, '*');
    }
    public static void p6 () {
        p6Cone();
        line(14, '+', "=*");
        p6Grow();
        p6Shrink();
        line(14, '+', "=*");
        p6Shrink();
        p6Grow();
        line(14, '+', "=*");
        p6Cone();
    }
    private static void p6Cone () {
        for (int i = 0; i < 5; i++) {
            linear(i, 5, -1, ' ');
            linear(i, 1, 1, '/');
            System.out.print("**");
            linear(i, 1, 1, '\\');
            linear(i, 5, -1, ' ');
            System.out.println();
        }
    }
    private static void p6Grow () {
        for (int i = 0; i < 3; i++) {
            p6piece(i, "/\\");
        }
    }
    private static void p6Shrink () {
        for (int i = 2; i >= 0; i--) {
            p6piece(i, "\\/");
        }
    }
    private static void p6piece (int i, String middle) {
        System.out.print('|');
        for (int j = 0; j < 2; j++) {
            linear(i, 2, -1, '.');
            linear(i, 2, 2, middle);
            linear(i, 2, -1, '.');
        }
        System.out.println('|');
    }
    public static void p7 () {
        p7Line();
        p7Grow();
        for (int i = 0; i < 4; i++) {
            linear(i, 0, 2, ' ');
            System.out.print("\\_");
            linear(i, 22, -4, "/\\");
            System.out.println("_/");
        }
        p7Line();
        for (int i = 0; i < 16; i++) {
            line(9, ' ');
            line(8, "|%%|");
            System.out.println();
        }
        p7Grow();
    }
    private static void p7Line () {
        for (int i = 0; i < 4; i++) {
            line(12, ' ');
            System.out.println("||");
        }
    }
    private static void p7Grow () {
        for (int i = 0; i < 4; i++) {
            linear(i, 9, -3, ' ');
            System.out.print("__/");
            linear(i, 0, 3, ':');
            System.out.print("||");
            linear(i, 0, 3, ':');
            System.out.println("\\__");
        }
        line(26, '|', '"');
    }
    public static void p8 () {
        line(11, ' ');
        line(32, '+', '-', false);
        System.out.println();
        for (int i = 0; i < 10; i++) {
            linear(i, 10, -1, ' ');
            System.out.print('/');
            linear(i, 27, -3, ' ');
            System.out.print('_');
            linear(i, 3, 3, "__/");
            linear(i, 0, 1, '/');
            System.out.println();
        }
        line(32, '+', '-', false);
        line(10, '/');
        System.out.println();
        for (int i = 0; i < 5; i++) {
            System.out.print('|');
            line(4, ' ');
            System.out.print("Building Java Programs");
            line(4, ' ');
            System.out.print('|');
            linear(i, 10, -2, '/');
            System.out.println();
        }
        line(32, '+', '-', false);
    }
    private static void linear (int element, int start, int interval, String character) {
        // The best thing to do would be to return an array with all elements of the pattern
        // but Arrays are chapert 7, so I don't know them.
        for (int i = 0; i < (interval * element + start) / character.length(); i++) {
            System.out.print(character);
        }
    }
    private static void linear (int element, int start, int interval, char character) {
        linear(element, start, interval, "" + character);
    }
    private static void line (int width, char border, String inner) {
        line(width, border, inner, true);
    }
    private static void line (int width, char border, char inner) {
        line(width, border, "" + inner);
    }
    private static void line (int width, char border, char inner, Boolean newline) {
        line(width, border, "" + inner, newline);
    }
    private static void line (int width, char border, String inner, Boolean newline) {
        System.out.print(border);
        line(width - 2, inner);
        System.out.print(border);
        if (newline) System.out.println();
    }
    private static void line (int width, String character) {
        for (int i = 0; i < width / character.length(); i++) {
            System.out.print(character);
        }
    }
    private static void line (int width, char character) {
        line(width, "" + character);
    }
}