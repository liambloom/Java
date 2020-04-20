package chapter4;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Ask {
  public static final Scanner console = new Scanner(System.in);
  public static final Pattern all = Pattern.compile(".*");
  public static char seperator = ':';
  public static String defaultPrompt = null;
  public static String forToken (String prompt, Pattern pattern, String customError) {
    prompt(prompt);
    if (console.hasNext(pattern)) {
      String val = console.next(pattern);
      if (!console.nextLine().isBlank()) System.out.println("Warning: your input was shortened to " + val);
      return val;
    }
    else {
      System.out.println(customError);
      console.nextLine();
      return forToken(prompt, pattern);
    }
  }
  public static String forToken (String prompt, String pattern, String customError) {
    return forToken(prompt, Pattern.compile(pattern), customError);
  }
  public static String forToken (String prompt, Pattern pattern) {
    return forToken(prompt, pattern, "Input not valid, please provide input that matches the pattern " + pattern.toString());
  }
  public static String forToken (String prompt, String pattern) {
    return forToken(prompt, Pattern.compile(pattern), "Input not valid, please provide input that matches the pattern " + pattern);
  }
  public static String forToken (String prompt) {
    return forToken(prompt, all, "");
  }
  public static String forToken() {
    return forToken(defaultPrompt("Word"), all, "");
  }
  public static String forString (String prompt) {
    prompt(prompt);
    String val = console.nextLine();
    return val;
  }
  public static String forString () {
    return forString(defaultPrompt("Word(s)"));
  }
  public static int forInt (String prompt) {
    prompt(prompt);
    if (console.hasNextInt()) {
      int val = console.nextInt();
      console.nextLine();
      return val;
    }
    else if (console.hasNext("^-?\\d+$")) System.out.println("Please input a whole number between -2^31 and 2^31-1 (inclusive)");
    else System.out.println("Please input a whole number");
    console.nextLine();
    return forInt(prompt);
  }
  public static int forInt(String prompt, int min, int max, String customError) {
    final int userInput = forInt(prompt);
    if (userInput >= min && userInput <= max)
      return userInput;
    else {
      System.out.println(customError);
      return forInt(prompt, min, max);
    }
  }
  public static int forInt(String prompt, int min, int max) {
    return forInt(prompt, min, max, "Invalid input: Please enter a number between " + min + " and " + max);
  }
  public static int forInt(String prompt, int max) {
    return forInt(prompt, 0, max);
  }
  public static int forInt() {
    return forInt(defaultPrompt("Integer"));
  }
  public static double forDouble (String prompt) {
    prompt(prompt);
    if (console.hasNextDouble()) {
      double val = console.nextDouble();
      console.nextLine();
      return val;
    }
    else if (console.hasNext("^-?(?:\\d+.?\\d*|\\d*.?\\d+)$")) System.out.println("Your number has too many digits");
    else System.out.println("Please input a number");
    console.nextLine();
    return forDouble(prompt);
  }
  public static double forDouble() {
    return forDouble(defaultPrompt("Decimal"));
  }
  public static boolean forBoolean (String prompt) {
    prompt(prompt);
    boolean val;
    if (console.hasNext(Pattern.compile("y(?:es)?|t(?:rue)?", Pattern.CASE_INSENSITIVE))) val = true;
    else if (console.hasNext(Pattern.compile("no?|f(?:alse)?", Pattern.CASE_INSENSITIVE))) val = false;
    else {
      System.out.println("Please input a boolean value (true/false)");
      console.nextLine();
      return forBoolean(prompt);
    }
    String accepted = console.next();
    if (!console.nextLine().isBlank()) System.out.println("Only your first value (" + accepted + ") was accepted.");
    return val;
  }
  public static boolean forBoolean() {
    return forBoolean(defaultPrompt("Boolean (true/false)"));
  }
  public static char forChar (String prompt) {
    String str = forToken(prompt);
    if (str.length() == 1) return str.charAt(0);
    else {
      System.out.println("Please input a single character");
      return forChar(prompt);
    }
  }
  public static char forChar () {
    return forChar(defaultPrompt("Character"));
  }
  public static void prompt (String prompt) {
    System.out.print(prompt + seperator + ' ');
  }
  private static String defaultPrompt (String fallback) {
    return defaultPrompt == null ? fallback : defaultPrompt;
  }
}