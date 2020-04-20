package chapter3;

import java.util.*;

public class BookWork {
    public static final Scanner console = new Scanner(System.in);
    public static void stringTest () {
        // Page 165 says strings are immutable
        // It says that means they cannot be changed
        String foo = "hi";
        foo = "bye"; // But I change it here?
        // Answer: "bye" is a different String object, still stored
        // in the variable foo.
        System.out.println(foo);
    }
    public static void takingInputs () {
        System.out.print("What's your name? (first and last)\n\t");
        String firstName = console.next();
        String lastName = console.next();
        /*String fullName = */console.nextLine();
        System.out.print("How old are you?\n\t");
        int age = console.nextInt();
        console.nextLine();/* If you don't do this, the newlines from
        pressing enter will be leftover, and the next time you run
        the program, the console.nextLine() will get that instead */
        System.out.print("How tall are you?\n\tfeet: ");
        int feet = console.nextInt();
        console.nextLine();
        System.out.print("\tinches: ");
        double inches = console.nextDouble();
        console.nextLine();
        System.out.println("Hello, " + /*fullName */firstName + ' ' + lastName + ". You are " + age + " years old and " + feet + '\'' + inches + '"' + " tall.");
    }
    public static void bmi () {
        System.out.println("You height:");
        int feet = (int) Ask.num("\tfeet");
        double inches = Ask.num("\tinches");
        double weight = Ask.num("weight");
        System.out.println("\nYour BMI is " + weight / Math.pow((feet * 12) + inches, 2) * 703);
    }
}