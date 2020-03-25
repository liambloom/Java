public class Chapter2Exercises {
    public static void main(String[] args) {
        exercise22_23(7);
    }
    public static double exercise1 (double s0, double v0, double t, double a) {
        return s0 + v0 * t + a * Math.pow(t, 2.0) / 2.0;
    }
    public static void exercise2 () {
        for (int i = 1; i <= 10; i++) {
            // Method 1 - Best (I think)
            System.out.print((int) Math.pow(i, 2) + " ");
            // Method 2 - Only ch2 stuff
            //System.out.print(i * i + " ");
            // Method 3 - No multiplication challenge
            /*
            int out = 0;
            for (int j = 0; j < i; j++) {
                out += i;
            }
            System.out.print(out + " ");
            */
        }
    }
    public static void exercise3 () {
        int previous = 0;
        int current = 1;
        for (int i = 0; i < 12; i++) {
            System.out.print(current + " ");
            int next = current + previous;
            previous = current;
            current = next;
        }
    }
    public static void exercise4 () {
        for (int i = 0; i < 4; i++) {
            line(5, '*');
            System.out.println();
        }
    }
    public static void exercise5 () {
        for (int i = 1; i <= 5; i++) {
            line(i, '*');
            System.out.println();
        }
    }
    public static void exercise6 () {
        for (int i = 1; i <= 7; i++) {
            line(i, i);
            System.out.println();
        }
    }
    public static void exercise7 () {
        for (int i = 1; i <= 5; i++) {
            line(5 - i, ' ');
            System.out.println(i);
        }
    }
    public static void exercise8 () {
       for (int i = 1; i <= 5; i++) {
            line(5 - i, ' ');
            line(i, i);
            System.out.println();
        } 
    }
    public static void exercise9 () {
        line(40, '-');
        System.out.println();
        line(10, "_-^-");
        System.out.println();
        for (int i = 1; i <= 20; i++) {
            //System.out.print("" + i % 10 + i % 10);
            line(2, i % 10);
        }
        System.out.println();
        line(40, '-');
    }
    public static void exercise10 (int length, int base) {
        for (int i = 0; i < length / base; i++) {
            line(base - 1,  ' ');
            System.out.print("|");
        }
        System.out.println();
        for (int i = 1; i <= length; i++) {
            System.out.print(i % base);
        }
    }
    // exercise11 is to change the code from exercise10
    public static void numbersLinear (int lineCount, int numberCount, String direction) {
        exercise12_13_14(lineCount, numberCount, direction == "up", true);
    }
    public static void numbersNonLinear (int lineCount, String direction) {
        exercise12_13_14(lineCount, -1, direction == "up", false); // the number doesn't matter
        // It would be best to check direction value with a regex, but idk how to do that
    }
    private static void exercise12_13_14 (int lines, int numbers, boolean increment, boolean linear) {
        for (int line = 0; line < lines; line++) {
            for (int number = increment ? 0 : 9; increment ? number < 10 : number >= 0; number += increment ? 1 : -1) {
                line(linear ? numbers : number, number);
            }
            System.out.println();
        }
    }
    public static void exercise15 () {
        for (int i = 1; i <= 9; i += 2) {
            line((11 - i) / 2, '-');
            line(i, i);
            line((11 - i) / 2, '-');
            System.out.println();
        } 
    }
    public static void exercise16 (int height) {
        for (int line = 0; line < height; line++) {
            line(line * 2, '\\'); // OMG LINE IS PASSED INTO LINE THIS IS SO WEIRD!!!!!
            line((4 * height - 2) - (line * 4), '!');
            line(line * 2, '/');
            System.out.println();
        }
    }
    /* exercise 17
      method windowHorizontalLine:
        twice:
          print '+'
          print '=' three times
        print '+'

      twice:
        call windowHorizontalLine
        three times:
          3 times print "|   \n"
      
      call windowHorizontalLine
    */
    public static void exercise18 () {
      window.draw(9);
    }
    public static void exercise20_21 (int height) {
      // width = (height - 1) * 8
      for (int line = 0; line < height; line++) {
        line(4 * (height - line - 1), '/');
        line(8 * line, '*');
        line(4 * (height - line - 1), '\\');
        System.out.println();
      } 
    }
    public static void exercise22_23 (int height) {
      for (int line = 0; line < height; line++) {
        line(line * 2, '*');
        line(height - line, '$');
        line((height - line) * 2, '*');
        line(height - line, '$');
        line(line * 2, '*');
        System.out.println();
      }
    }
    public static void line (int length, int character) {
      line(length, character + "");
    }
    public static void line (int length, char character) {
      // Why is this the only way to convert a char into a string!?
      line(length, character + "");
    }
    public static void line (int length, String character) {
        for (int i = 0; i < length; i++) {
            System.out.print(character);
        }
    }
    public static double bmi (int feet, double inches, double weight) {
        return weight / Math.pow((feet * 12) + inches, 2) * 703;
    }
    public static void test () {
        System.out.println("1" + 2 * 3 + 4);
    }
    public static void infininteLoop () {
        for (int i = 0; true; i++) {
            System.out.println(i);
        }
    }
}