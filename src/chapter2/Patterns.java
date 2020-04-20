package chapter2;

// I wrote javascript to write this. It was able to solve all but problems 7 and 15.
public class Patterns {
    public static void main () {
        
    }
    public static void pattern1 () {
        System.out.print("1. ");
        for (int i = 0; i < 6; i++) {
            System.out.print((int)(1 * Math.pow(10, i)));
            if (i < 5) System.out.print(", ");
        }
        System.out.println();
    }
    public static void pattern2 () {
        System.out.print("2. ");
        for (int i = 0; i < 5; i++) {
            System.out.print(180 + i * 180);
            if (i < 4) System.out.print(", ");
        }
        System.out.println();
    }
    public static void pattern3 () {
        System.out.print("3. ");
        for (int i = 0; i < 8; i++) {
            System.out.print((int)(0.5 * Math.pow(i, 2) + i * 9.5 + 0));
            if (i < 7) System.out.print(", ");
        }
        System.out.println();
    }
    public static void pattern4 () {
        System.out.print("4. ");
        for (int i = 0; i < 8; i++) {
            System.out.print((int)(0.5 * Math.pow(i, 2) + i * 1.5 + 1));
            if (i < 7) System.out.print(", ");
        }
        System.out.println();
    }
    public static void pattern5 () {
        System.out.print("5. ");
        for (int i = 0; i < 8; i++) {
            System.out.print((int)(1 * Math.pow(i, 2) + i * 2 + 1));
            if (i < 7) System.out.print(", ");
        }
        System.out.println();
    }
    public static void pattern6 () {
        System.out.print("6. ");
        for (int i = 0; i < 8; i++) {
            System.out.print((char)(i * -3 + 87));
            if (i < 7) System.out.print(", ");
        }
        System.out.println();
    }
    public static void pattern7 () {// pattern 7 idk if i need to simplify, or if then need to be fractions at all. Also, it breaks the JS that wrote this
        System.out.print("7. ");
        for (int i = 1; i <= 6; i++) {
            if (i / 6 != 0) System.out.print(i / 6); // Should have a space after, but I will never have a mixed number so it doesn't matter
            if (i % 6 != 0) {
                for (int divisor = 3; ; divisor--) {
                    if (i % divisor == 0) {
                        System.out.print(i / divisor + "/" + 6 / divisor);
                        break;
                    }
                }
            }
            if (i <= 5) System.out.print(", ");
        }
        System.out.println();
    }
    public static void pattern8 () {
        System.out.print("8. ");
        for (int i = 0; i < 4; i++) {
            System.out.print(((char)(i * 2 + 97)) + ", " + (i * 6 + 6));
            if (i < 3) System.out.print(", ");
        }
        System.out.println();
    }
    public static void pattern9 () {
        System.out.print("9. ");
        for (int i = 0; i < 4; i++) {
            System.out.print(((i * 1 + 1) + "/" + (i * 1 + 2)) + ", " + (i * 1 + 9));
            if (i < 3) System.out.print(", ");
        }
        System.out.println();
    }
    public static void pattern10 () {
        System.out.print("10. ");
        for (int i = 0; i < 7; i++) {
            System.out.print((char)((int)(0.5 * Math.pow(i, 2) + i * 0.5 + 65)));
            if (i < 6) System.out.print(", ");
        }
        System.out.println();
    }
    public static void pattern11 () {
        System.out.print("11. ");
        int current = 1;
        int next = 1;
        for (int i = 0; i < 9; i++) {
            System.out.print(current);
            int nextnext = next + current;
            current = next;
            next = nextnext;
            if (i < 8) System.out.print(", ");
        }
        System.out.println();
    }
    public static void pattern12 () {
        System.out.print("12. ");
        int current = 1;
        int next = 3;
        for (int i = 0; i < 8; i++) {
            System.out.print(current);
            int nextnext = next + current;
            current = next;
            next = nextnext;
            if (i < 7) System.out.print(", ");
        }
        System.out.println();
    }
    public static void pattern13 () {
        System.out.print("13. ");
        for (int i = 0; i < 8; i++) {
            System.out.print((int)(1 * Math.pow(2, i)));
            if (i < 7) System.out.print(", ");
        }
        System.out.println();
    }
    public static void pattern14 () {
        System.out.print("14. ");
        for (int i = 0; i < 8; i++) {
            System.out.print((int)(Math.pow(2, i + 1) + -1) / 1);
            if (i < 7) System.out.print(", ");
        }
        System.out.println();
    }
    public static void pattern15 () { // This one was also hardcoded, as it would require a third difference, another thing that I won't implement at 11:46pm
        System.out.print("15. ");
        int k = 1; // This is just a 3rd degree polynomial. I just don't know what it is
        for (int i = 1; i <= 8; i++) {
            k += Math.pow(i, 2);
            System.out.print(k); // apparently returns value of k before adding
            if (i < 8) System.out.print(", ");
        }
        System.out.println();
    }
    public static void pattern16 () {
        System.out.print("16. ");
        for (int i = 0; i < 8; i++) {
            System.out.print((int)(Math.pow(3, i + 0) + 2) / 1);
            if (i < 7) System.out.print(", ");
        }
        System.out.println();
    }
    public static void pattern17 () {
        System.out.print("17. ");
        for (int i = 0; i < 8; i++) {
            System.out.print((int)(1 * Math.pow(i, 2) + i * 2 + 0));
            if (i < 7) System.out.print(", ");
        }
        System.out.println();
    }
    public static void pattern18 () {
        System.out.print("18. ");
        for (int i = 0; i < 7; i++) {
            System.out.print((int)(3 * Math.pow(-4, i)));
            if (i < 6) System.out.print(", ");
        }
        System.out.println();
    }
    public static void pattern19 () {
        System.out.print("19. ");
        for (int i = 0; i < 8; i++) {
            System.out.print((int)(Math.pow(3, i + 0) + 1) / 2);
            if (i < 7) System.out.print(", ");
        }
        System.out.println();
    }
    public static void pattern20 () {
        System.out.print("20. ");
        for (int i = 0; i < 6; i++) {
            System.out.print((i * 0 + 1) + "/" + ((int)(2 * Math.pow(2, i))));
            if (i < 5) System.out.print(", ");
        }
        System.out.println();
    }
}