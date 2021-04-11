package dev.liambloom.softwareEngineering.chapter1;

public class Exercises {
    public static void main (Boolean runExercise16) {
        exercise1();
        exercise2();
        exercise3();
        exercise4();
        exercise5();
        exercise6();
        exercise7();
        exercise8();
        exercise9();
        exercise10();
        exercise11();
        exercise12();
        exercise13();
        exercise14();
        exercise15();
        if (runExercise16) {
            exercise16();
            //exercise16Loop();
        }
        challenge4();
    }
    public static void exercise1 () {
        String out = "Exercise 1:\n";
        out += victory();
        System.out.println(out);
    }
    public static void exercise2 () {
        String out = "Exercise 2:\n";
        out += "  \\/\n";
        out += " \\\\//\n";
        out += "\\\\\\///\n";
        out += "///\\\\\\\n";
        out += " //\\\\\n";
        out += "  /\\\n";
        System.out.println(out);
    }
    public static void exercise3 () {
        String out = "Exercise 3:\n";
        out += "A well-formed Java program has\n";
        out += "a main method with { and }\n";
        out += "braces.\n";
        out += "\n";
        out += "A System.out.println statement\n";
        out += "has ( and ) and usually a\n";
        out += "String that starts and ends\n";
        out += "with a \" character.\n";
        out += "(But we type \\\" instead!)\n";
        System.out.println(out);
    }
    public static void exercise4 () {
        String out = "Exercise 4:\n";
        out += "What is the difference between\n";
        out += "a ' and a \"? Or between a \" and a \\\"?\n";
        out += "One is what we see when we're typing our program.\n";
        out += "The other is what appears on the \"console.\"\n";
        System.out.println(out);
    }
    public static void exercise5 () {
        String out = "Exercise 5:\n";
        out += "A \"quoted\" String is\n";
        out += "'much' better if you learn\n";
        out += "the rules of\"escape sequences.\"\n";
        out += "Also, \"\" represents an empty String.\n";
        out += "Don't forget: use \\\" instead of \" !\n";
        out += "'' is not the same as \"\n";
        System.out.println(out);
    }
    public static void exercise6 () {
        String out = "Exercise 6:\n";
        out += "public class HelloWorld {\n";
        out += "\tpublic static void main () {\n";
        out += "\t\tSystem.out.println(\"Hello, World!\");\n";
        out += "\t}\n";
        out += "}\n";
        System.out.println(out);
    }
    public static void exercise7 () {
        String out = "Exercise 7:\n";
        out += exercise7Piece();
        out += "\n";
        out += exercise7Piece();
        System.out.println(out);
    }
    public static void exercise8 () {
        exercise(8);
        String out = "//////////////////////\n";
        for (int i = 0; i < 4; i++) {
            out += victory();
        }
        System.out.print(out);
    }
    public static void exercise9 () {
        exercise(9);
        Egg.main();
    }
    public static void exercise10 () {
        exercise(10);
        for (int i = 0; i < 2; i++) {
            Egg.top();
            Egg.bottom();
            Egg.middle();
        }
        Egg.bottom();
        Egg.main();
    }
    public static void exercise11 () {
        exercise(11);
        Rocket.main(2);
    }
    public static void exercise12 () {
        exercise(12);
        cheerPiece();
        System.out.println();
        for (int i = 0; i < 2; i++) {
            cheerVerse();
        }
        /*cheerVerse();
        cheerVerse();*/
        cheerPiece();
    }
    public static void exercise13 () {
        exercise(13);
        Shapes.equalsX();
        System.out.println(); // Is this valid? Yes
        Shapes.equalsX();
        Shapes.equals();
        System.out.println();
        Shapes.verticalLine();
        Shapes.equalsX();
    }
    public static void exercise14 () {
        exercise(14);
        Shapes.wideCone();
        System.out.println();
        Shapes.wideCone();
        Shapes.grate();
        Shapes.line(13);
        Shapes.wideCone();
        System.out.println();
        Shapes.wideCone();
        Shapes.centerLine(5, 13);
        for (int i = 0; i < 2; i++) {
            Shapes.grate();
        }
        for (int i = 0; i < 2; i++) {
            Shapes.centerLine(5, 13);
        }
    }
    public static void exercise15 () {
        exercise(15);
        Egg.top();
        Egg.bottom();
        asciiLine(9, 11);
        Egg.top();
        System.out.println("|   STOP  |");
        Egg.bottom();
        Egg.top();
        asciiLine(11, 11);
    }
    public static void exercise16Loop () { // Exercise 16 in the most efficient way posable
        System.out.println("Exercise 16 using a loop:");
        for (int i = 0; i < 1000; i++) {
            System.out.println("All work and no play makes Jack a dull boy.");
        }
    }
    public static void exercise16 () { // Exercise 16 using only things we learned in chapter 1
        // It seems like this should not take 31 lines of code, but it's the most efficient solution I could come up with.
        exercise(16);
        ex16_125();
        ex16_125();
        ex16_125();
        ex16_125();
        ex16_125();
        ex16_125();
        ex16_125();
        ex16_125();
    }
    private static void ex16_5 () {
        System.out.println("All work and no play makes Jack a dull boy.");
        System.out.println("All work and no play makes Jack a dull boy.");
        System.out.println("All work and no play makes Jack a dull boy.");
        System.out.println("All work and no play makes Jack a dull boy.");
        System.out.println("All work and no play makes Jack a dull boy.");
    }
    private static void ex16_25 () {
        ex16_5();
        ex16_5();
        ex16_5();
        ex16_5();
        ex16_5();
    }
    private static void ex16_125 () {
        ex16_25();
        ex16_25();
        ex16_25();
        ex16_25();
        ex16_25();
    }
    public static void challenge4 () {
        System.out.println("Challenge 4:");
        TwelveDaysOfChristmas.main();
    }
    private static void asciiLine (int length, int totalSpace) {
        for (int i = 0; i < (totalSpace - length) / 2; i++) {
            System.out.print(" ");
        }
        System.out.print("+");
        for (int i = 0; i < length - 2; i++) {
            System.out.print("-");
        }
        System.out.println("+");
    }
    private static void exercise (int num) {
        System.out.println("\nExercise " + num + ":");
    }
    private static String victory () {
        String out = "|| Victory is mine! ||\n";
        out += "\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\n";
        return out;
    }
    private static String exercise7Piece () {
        String out = "There's one thing every coder must understand:\n";
        out += "The System.out.println command.\n";
        return out;
    }
    private static void cheerPiece () {
        System.out.println("Go, team, go!");
        System.out.println("You can do it.");
    }
    private static void cheerVerse () {
        cheerPiece();
        System.out.println("You're the best,");
        System.out.println("In the West.");
        cheerPiece();
        System.out.println();
    }
}
