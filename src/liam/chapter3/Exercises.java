package liam.chapter3;

import java.util.*;

public class Exercises {
  public static void exercise1 (int max) {
      for (int  i = 1; i <= max; i++) {
          System.out.print("[" + i + "] ");
      }
      System.out.println();
  }
  public static void exercise2 (int max) {
      for (int i = 0; i <= max; i++) {
          System.out.print(Math.pow(2, i));
      }
      System.out.println();
  }
  public static void exercise3 (int base, int max) {
      for (int i = 0; i <= max; i++) {
          System.out.print(Math.pow(base, i));
      }
      System.out.println();
  }
  public static void exercise4 (int min, int max) {
      for (int i = min; i <= max; i++) {
          for (int j = i; j <= max; j++) System.out.print(j);
          for (int j = min; j < i; j++) System.out.print(j);
          System.out.println();
      }
  }
  public static void exercise5 (int rows, int columns) {
      for (int i = 1; i <= rows; i++) {
          for (int j = i; j <= rows * columns; j += rows) {
              System.out.print(j + " ");
          }
          System.out.println();
      }
  }
  public static int exercise6_7 (int ...nums) {
      int max = 0;
      for (int i = 0; i < nums.length; i++) {
          max = Math.max(max, Math.abs(nums[i]));
      }
      return max;
  }
  public static void exercise8 (double a, double b, double c) {
      double d = Math.sqrt(Math.pow(b, 2) - 4 * a * c);
      double denominator = 2 * a;
      System.out.println((-b + d) / denominator + ", " + (-b - d) / denominator);
  }
  public static int exercise9 (int num) {
      return Math.abs(num % 10);
  }
  public static double exercise10 (double radius) {
      return Math.PI * Math.pow(radius, 10);
  }
  public static double exercise11 (int x1, int y1, int x2, int y2) {
      return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
  }
  public static double exercise12 (double base, int exponent) {
      return base * Math.pow(10, exponent);
  }
  public static double exercise13 (double salary, int hours) {
      double normalTime = Math.min(hours, 8);
      return salary * (-0.5 * Math.min(hours, 8) + 1.5 * hours);
  }
  public static double exercise14 (double radius, double height) {
      return 2 * Math.PI * (Math.pow(radius, 2) + radius * height);
  }
  public static double exercise15 (double radius) {
      return 4.0/3.0 * Math.PI * Math.pow(radius, 3);
  }
  public static double exercise16 (double a, double b, double c) {
      double s = (a + b + c) / 2;
      return Math.sqrt(s * (s - a) * (s - b) * (s - c));
  }
  public static String exercise17 (String original, int length) {
      while (original.length() < length) {
          original += " ";
      }
      return original;
      /* If I only knew chapters 1-3, I could replace the while loop with:
      * for (int i = 0; i < length - original.length; i++)
      * Also, I'm honestly surprised that this isn't already built
      * into java
      */
  }
  public static void exercise18 (String str) {
      for (int i = 0; i < str.length(); i++) {
          System.out.println(str.charAt(i));
      }
  }
  public static void exercise19 (String str) {
      for (int i = str.length(); i > 0; i--) {
          System.out.print(str.charAt(i - 1));
      }
      System.out.println();
  }
  public static void exercise20 (Scanner console) {
      Ask.console = console;
      String month = Ask.str("What month were you born in?");
      String day = Ask.str("What day of " + month + " were you born on?");
      String year = Ask.str("What year were you born in?");
      System.out.println("You were born on " + month + " " + day + ", " + year + ".");
  }
  public static void exercise21 (Scanner console) {
      Ask.console = console;
      Ask.prompt("Please enter your full name");
      String firstName = console.next();
      String lastName = console.next();
      System.out.println("Welcome, "+ lastName + ", " + firstName);
  }
  public static void exercise22 () {
      System.out.println("University admission prgram!");
      double gpa = Ask.num("What is your GPA?");
      int sat = (int) Ask.num("What is your SAT score");
      System.out.println("You were " + (gpa < 1.8 || sat < 900 ? "rejected!" : "accepted!"));
  }
}
