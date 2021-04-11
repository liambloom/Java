package dev.liambloom.softwareEngineering.chapter3;

import java.util.*;

public class Ask {
    public static Scanner console = new Scanner(System.in);
    public static String str (String prompt) {
        prompt(prompt);
        String value = console.next();
        line();
        return value;
    }
    public static double num (String prompt) {
        prompt(prompt);
        double value = console.nextDouble();
        line();
        return value;
    }
    public static String line (String prompt) {
        prompt(prompt);
        String value = line();
        return value;
    }
    public static String line () {
        return console.nextLine();
    }
    public static void prompt (String prompt) {
        System.out.print(prompt + ": ");
    }
}