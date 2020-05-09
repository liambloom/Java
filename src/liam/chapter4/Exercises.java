package liam.chapter4;

import liam.Globals;

public class Exercises {
  public static int Infinity = (int) Globals.Math.Infinity;
  public static void main (String[] args) {
    exercise21(500);
  }
  public static double exercise1 (int n) {
    // There's probably a formula for this, but I don't know it
    double total = 0;
    for (double i = 1; i <= n; i++) {
        total += 1 / i;
    }
    return total;
  }
  public static String exercise2 (String str, int repititions) {
    String returnValue = "";
    for (int i = 0; i < repititions; i++) {
        returnValue += str;
    }
    return returnValue;
  }
  public static String exercise3 (int month, int day) {
    double date = month + day / 100;
    if (date > 3.16 && date < 6.15) return "spring";
    else if (date > 6.16 && date < 9.15) return "summer";
    else if (date > 9.16 && date < 9.15) return "fall";
    else return "winter";
  }
  public static final int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
  public static int exercise4 (int month) {
    return daysInMonth[month];
  }
  public static int exercise5 (int base, int exponent) {
    return (int) Math.pow(base, exponent);
  }
  public static void exercise6 (int min, int max) {
    for (int i = min; i <= max; i++) {
      System.out.print(i + " ");
    }
    System.out.println();
  }
  public static void exercise7 (int size) {
    //System.out.println("Exercise 7")
    final double CENTER = (size - 1) / 2.0;
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < -Math.abs(i - CENTER) + CENTER; j++) System.out.print("o");
      System.out.print("x");
      for (int j = 0; j < 2 * Math.abs(i - CENTER) - 1; j++) System.out.print("o");
      if (size % 2 == 0 || i != CENTER) System.out.print("x");
      for (int j = 0; j < -Math.abs(i - CENTER) + CENTER; j++) System.out.print("o");
      System.out.println();
    }
    System.out.println();
  }
  public static void exercise8 () {
    Ask.seperator = '?';
    int min = Infinity;
    int max = -Infinity;
    int nums = Ask.forInt("How many numbers do you want to enter");
    for (int i = 0; i < nums; i++) {
      int next = Ask.forInt("Number " + (i + 1));
      min = Math.min(min, next);
      max = Math.max(max, next);
    }
    System.out.printf("Smallest = %d\n", min);
    System.out.printf("Largest = %d\n", max);
  }
  public static void exercise9 () {
    Ask.seperator = '?';
    int sum = 0;
    int max = -Infinity;
    int nums = Ask.forInt("How many integers");
    for (int i = 0; i < nums; i++) {
      int next = Ask.forInt("Next integer");
      if (next % 2 == 0) {
        sum += next;
        max = Math.max(max, next);
      }
    }
    System.out.printf("Even sum = %d, Even max = %d\n", sum, max);
  }
  public static void exercise10 () {
    Ask.seperator = ':';
    Ask.prompt("Enter a student record");
    String name = Ask.console.next();
    int grades = Ask.console.nextInt();
    double average = 0;
    for (int i = 0; i < grades; i++) {
      average += Ask.console.nextDouble() / grades;
    }
    Ask.console.nextLine();
    System.out.printf("%s's grade is ", name);
    System.out.println(average); // IDK why putting it in using "%f" adds "0"s
  }
  public static void exercise10Better () { // This version does not require you to say how many grades you're inputing, it works regardless
    Ask.seperator = ':';
    String[] grades = Ask.forString("Enter a student record").split(" ");
    double average = 0;
    for (int i = 1; i < grades.length; i++) {
      average += Double.parseDouble(grades[i]) / (grades.length - 1);
    }
    System.out.printf("%s's grade is %f", grades[0], average);
  }
  public static void exercise11 (int names) {
    String longest = "";
    for (int i = 1; i <= names; i++) {
      String name = Ask.forToken("Name #" + i);
      if (longest.length() < name.length()) longest = name;
    }
    System.out.printf("%s's name is longest", longest.toUpperCase().charAt(0) + longest.substring(1).toLowerCase());
  }
  public static String exercise12 (int a, int b, int c) {
    if (a == b && b == c) return "equilateral";
    else if (a == b || b == c || a == c) return "isoceles";
    else return "scaline";
  }
  public static double exercise13 (int ...nums) {
    double total = 0;
    for (int i = 0; i < nums.length; i++) total += nums[i];
    return total / nums.length;
  }
  public static double exercise14 (int base, int exponent) {
    return Math.pow(base, exponent);
  }
  public static double exercise15 (int grade) { 
    if (grade < 60) return 0.0;
    else if (grade >= 60 && grade <= 62) return 0.7;
    else if (grade >= 95) return 4.0;
    else return (double) grade / 10 - 5.5;
  }
  public static void exercise16 () {
    Ask.seperator = ':';
    String str = Ask.forToken("Enter a word").toLowerCase();
    String reversed = "";
    for (int i = str.length() - 1; i >= 0; i--) reversed += str.charAt(i);
    if (str.equals(reversed)) System.out.println("It's a palendrome");
    else System.out.println("It's not a palendrome");
  }
  public static String exercise17 (String str) {
    String reversed = "";
    for (int i = 0; i < str.length() / 2; i++) {
      reversed += str.charAt(2 * i + 1);
      reversed += str.charAt(2 * i);
    }
    if (str.length() % 2 != 0) reversed += str.charAt(str.length() - 1);
    return reversed;
  }
  public static int exercise18 (String str) {
    boolean inword = false;
    int wordcount = 0;
    for (int i = 0; i < str.length(); i++) {
      if (str.charAt(i) == ' ') inword = false;
      else if (!inword) {
        inword = true;
        wordcount++;
      }
    }
    return wordcount;
  }
  public static int exercise19 (double x, double y) {
    if (x == 0 || y == 0) throw new IllegalArgumentException("The point (" + x + ", " + y + ") is not in any quadrant");
    if (x > 0) {
      if (y > 0) return 1;
      else return 4;
    }
    else {
      if (y > 0) return 2;
      else return 3;
    }
  }
  public static int exercise20 (int a, int b, int c) { // I don't know if this is the intended behaviour, the for is confusing
    int unique = 3;
    if (a == b) unique--;
    if (a == c || b == c) unique--;
    return unique;
  }
  public static void exercise21 (int max) {
    String perfect = "";
    for (int i = 1; i <= max; i++) {
      int sumOfFactors = 0;
      for (int j = 1; j <= i / 2; j++) if (i % j == 0) sumOfFactors += j;
      if (sumOfFactors == i) perfect += i + " ";
    }
    System.out.printf("Perfect number up to %d: %s%n", max, perfect);
  }
}