package io.github.liambloom.softwareEngineering.chapter5;

import java.util.regex.Pattern;

import liam.Globals;
import liam.chapter4.Ask;

public class Exercises {
  public static void exercise1 (int n) {
    System.out.print(n + " = ");
    factorOut2(n);
  }
  private static void factorOut2 (int n) {
    if (n % 2 == 0) {
      System.out.print("2 * ");
      factorOut2(n / 2);
    }
    else System.out.println(n);
  }
  public static int exercise2 (int a, int b) {
    return b == 0 ? Math.abs(a) : exercise2(b, a % b);
  }
  public static String exercise3 (int n) {
    String bin = "";
    for (int digit = (int) Math.pow(2, (int) $.log2(n)); digit >= 1; digit /= 2) {
      if (n >= digit) {
        n = n % digit;
        bin += "1";
      }
      else bin += "0";
    }
    return bin;
  }
  public static void exercise4 () {
    int stars;
    do {
      stars = Rand.nextInt(5, 20);
      for (int i = 0; i < stars; i++) {
        System.out.print('x');
      }
      System.out.println();
    }
    while (stars < 16);
  }
  public static void exercise5 () {
    final int lines = Rand.nextInt(5, 10);
    for (int i = 0; i < lines; i++) {
      System.out.println(Rand.nextWord(Rand.nextInt(80)));
    }
  }
  public static void exercise6 () {
    int guess;
    int guesses = 0;
    do {
      guess = Rand.nextInt(1, 50);
      System.out.println("guess = " + guess);
      guesses++;
    }
    while (guess <= 48);
    System.out.println("total guesses = " + guesses);
  }
  public static void exercise7 () {
    final int desiredSum = Ask.forInt("Desired Dice Sum", 2, 12, "The sum of two dice must be between 2 and 12");
    int d1, d2, sum;
    do {
      d1 = Rand.nextInt(1, 6);
      d2 = Rand.nextInt(1, 6);
      sum = d1 + d2;
      System.out.printf("%d and %d = %d%n", d1, d2, sum);
    }
    while (sum != desiredSum);
  }
  public static void exercise8 () {
    int max = 0;
    int position = 0;
    do {
      if (Rand.nextBoolean()) position++;
      else position--;
      max = Math.max(max, position);
      System.out.println("position = " + position);
    }
    while (Math.abs(position) < 3); // probably worse performance than two checks, but I'm lazy
    System.out.println("max position = " + max);
  }
  public static void exercise9 (int num) {
    for (int i = 1; i <= num / 2; i++) {
      if ((double) num / i % 1 == 0) System.out.print(i + " and ");
    }
    System.out.println(num);
  }
  public static void exercise10 (int hops) {
    int hop = 1;
    if (hops >= 0) System.out.printf("%4d%n", hop++);
    for (int i = 0; i < hops; i++) {
      System.out.printf("%-4d%3d%n%4d%n", hop++, hop++, hop++);
    }
  }
  public static void exercise11 () {
    int heads = 0;
    while (heads < 3) {
      if (Rand.nextBoolean()) {
        System.out.print("H ");
        heads++;
      }
      else {
        System.out.print("T ");
        heads = 0;
      }
    }
    System.out.println("\nThree heads in a row!");
  }
  public static void exercise12 () {
    int total = 0; 
    int inputs = 0;
    while (true) {
      int in = Ask.forInt("Type a number");
      if (in < 0) break;
      else {
        total += in;
        inputs++;
      }
    }
    System.out.println("Average was " + (double) total / inputs);
  }
  public static boolean exercise13 (int a, int b, int c) {
    int min = Globals.Math.min(a, b, c);
    int max = Globals.Math.max(a, b, c);
    int mid = $.mid(a, b, c);
    return min + 1 == mid && min + 2 == max;
  }
  public static boolean exercise14 (int a, int b, int c) {
    int min = Globals.Math.min(a, b, c);
    int max = Globals.Math.max(a, b, c);
    return $.mid(a, b, c) == (double) (min + max) / 2;
  }
  public static boolean exercise15 (int a, int b, int c) {
    return a + b < c || a + c < b || b + c < a;
  }
  public static boolean exercise16 (int a, int b, int c) {
    return (a + b == 90 || a + c == 90 || b + c == 90) &&
      (a + b == 180 || a + c == 180 || b + c == 180);
  }
  public static boolean exercise17 (int m1, int d1, int m2, int d2) {
    if (m1 == m2) return false;
    else if (Math.abs(m1 - m2) > 1) return true;
    else return m1 > m2 && d1 > d2 || m2 > m1 && d2 > d1;
  }
  public static int exercise18 (int num) {
    String str = "" + Math.abs(num);
    int sum = 0;
    for (int digit = 0; digit < str.length(); digit++) {
      sum += Character.getNumericValue(str.charAt(digit));
    }
    return sum;
  }
  public static int exercise19 (int num) {
    return Character.getNumericValue(("" + Math.abs(num)).charAt(0));
  }
  public static int exercise20 (int num) {
    int min = 10;
    int max = 0;
    for (int i = 1; i <= num; i *= 10) {
      int digit = (num % (10 * i) - num % i) / i;
      min = Math.min(min, digit);
      max = Math.max(max, digit);
    }
    return max - min + 1;
  }
  public static int exercise21 (int num) {
    int swapped = 0;
    int digits = (int) Math.ceil(Math.log10(num + 1));
    for (int i = 10; i < Math.pow(10, digits + (digits % 2 == 0 ? 1 : 0)); i *= 100) {
      swapped += (num % i - num % (i / 10)) * 10;
      swapped += (num % (i * 10) - num % i) / 10;
    }
    if (digits % 2 != 0) swapped += num % Math.pow(10, digits) - num % Math.pow(10, digits - 1);
    return swapped;
  }
  public static boolean exercise22 (int num) {
    String str = "" + num;
    for (int i = 0; i < str.length(); i++) {
      if (Character.getNumericValue(str.charAt(i)) % 2 == 0) return false;
    }
    return true;
  }
  public static boolean exercise23 (int num) {
    String str = "" + num;
    for (int i = 0; i < str.length(); i++) {
      if (Character.getNumericValue(str.charAt(i)) % 2 != 0) return false;
    }
    return true;
  }
  public static boolean exercise24UsingRegex (String str) {
    return Pattern.compile("^[aeiou]*$", Pattern.CASE_INSENSITIVE).matcher(str).matches();
  }
  public static boolean exercise24WithoutRegex (String str) {
    for (int i = 0; i < str.length(); i++) {
      final char c = Character.toLowerCase(str.charAt(i));
      if (!(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u')) return false;
    }
    return true;
  }
}