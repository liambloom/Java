package io.github.liambloom.softwareEngineering.chapter7;

public class ANSI {
    public static class Display {
        public static enum Color {
            Black(0), Red(1), Green(2), Yellow(3), Blue(4), Magenta(5), Cyan(6), White(7);

            int colorCode;

            Color(int colorCode) {
                this.colorCode = colorCode;
            }
        }

        public static void setFgColor(Color color) {
            System.out.printf("\u001B[3%dm", color.colorCode);
        }

        public static void setBgColor(Color color) {
            System.out.printf("\u001B[4%dm", color.colorCode);
        }

        public static void reset() {
            System.out.print("\u001B[0m");
        }

        public static void bold() {
            System.out.print("\u001B[1m");
        }

        public static void dim() {
            System.out.print("\u001B[2m");
        }

        public static void italic() {
            System.out.print("\u001B[3m");
        }

        public static void underline() {
            System.out.print("\u001B[4m");
        }
    }

    public static class Cursor {
        public static void moveTo(int line, int col) {
            System.out.printf("\u001B[%d;%dH", line, col);
        }

        public static void moveUp(int d) {
            System.out.printf("\u001B[%dA", d);
        }
        
        public static void moveDown(int d) {
            System.out.printf("\u001B[%dB", d);
        }
        
        public static void moveRight(int d) {
            System.out.printf("\u001B[%dC", d);
        }
        
        public static void moveLeft(int d) {
            System.out.printf("\u001B[%dD", d);
        }

        public static void moveToCol(int col) {
            System.out.printf("\u001B[%dG", col);
        }

        public static void save() {
            System.out.print("\u001B[s");
        }

        public static void revert() {
            System.out.print("\u001B[u");
        }
    }

    public static class Eraser {
        public static void clearDown() {
            System.out.print("\u001B[0J");
        }

        public static void clearUp() {
            System.out.print("\u001B[1J");
        }

        public static void clear() {
            System.out.print("\u001B[2J");
        }

        public static void clearRight() {
            System.out.print("\u001B[0K");
        }

        public static void clearLeft() {
            System.out.print("\u001B[1K");
        }

        public static void clearLine() {
            System.out.print("\u001B[2K");
        }
    }
}
