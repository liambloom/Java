package liam.chapter3;

public class StringExercises {
  public static String exercise1 (String first, String middle, String last) {
    return first + " " + middle + " " + last;
  }
  public static String exercise2 (String first, String middle, String last) {
    return "" + first.charAt(0) + middle.charAt(0) + last.charAt(0);
  }
  public static String exercise3 (String first, String middle, String last) {
    return last + ", " + first + " " + middle.charAt(0) + ".";
  }
  public static String exercise4 (String name) {
    int firstSpace = name.indexOf(" ");
    int lastSpace = name.lastIndexOf(" ");
    return exercise3(name.substring(0, firstSpace), name.substring(firstSpace + 1, lastSpace), name.substring(lastSpace + 1));
  }
  public static String exercise4UsingArrays (String name) {
    String[] nameArr = name.split(" ");
    return exercise3(nameArr[0], nameArr[1], nameArr[2]);
  }
  public static void exercise5 (String str) {
    System.out.printf("\"%s\" and \"%s\"%n", str.toLowerCase(), str.toUpperCase());
  }
  public static void exercise6 (String str1, String str2) {
    int str1Length = str1.length(); // I'm pretty sure that storing it in a variable has better performance than calling .length() twice
    int str2Length = str2.length();
    System.out.printf("Longest Length: %d \t Shortest Length: %d%n", Math.max(str1Length, str2Length), Math.min(str1Length, str2Length));
  }
  public static void exercise7 (String str) {
    for (int i = 0; i < str.length(); i++) {
      System.out.print(str.charAt(i) + "\t");
    }
    System.out.println();
  }
  public static String exercise8 (String str) {
    String reversed = "";
    for (int i = str.length() - 1; i >= 0; i--) {
      reversed += str.charAt(i);
    }
    return reversed;
  }
  public static void exercise9 (String str) {
    for (int i = 0; i < str.length(); i++) {
      System.out.print((int) str.charAt(i) + "\t");
    }
    System.out.println();
  }
  public static void exercise10 (String str) {
    char highest = max(str);
    char lowest = min(str);
    System.out.printf("highest=%d  (\"%c\")\tlowest=%d  (\"%c\")", (int) highest, highest, (int) lowest, lowest);
  }
  private static char min (String str) {
    int min = 127;
    for (int i = 0; i < str.length(); i++) {
      min = Math.min(min, (int) str.charAt(i));
    }
    return (char) min;
  }
  private static char max (String str) {
    int max = 0;
    for (int i = 0; i < str.length(); i++) {
      max = Math.max(max, (int) str.charAt(i));
    }
    return (char) max;
  }
  /*public static void exercise11 (String str) { // Not possible without if statements
      int min = 127 * 2;
      char min1, min2;
      for (int i = 0; i < str.length() - 1; i++) {
          min = Math.min(min, (int) str.charAt(i) + (int) str.charAt(i));
      }
      
  }*/
  public static void exercise11 (String str) {
    System.out.printf("highest=%d\tlowest=%d%n", maxSum(str), minSum(str));
  }
  private static int minSum (String str) {
    int min = 127 * 2;
    for (int i = 0; i < str.length() - 1; i++) {
      min = Math.min(min, (int) str.charAt(i) + str.charAt(i + 1));
    }
    return min;
  }
  private static int maxSum (String str) {
    int max = 0;
    for (int i = 0; i < str.length() - 1; i++) {
      max = Math.max(max, (int) str.charAt(i) + str.charAt(i + 1));
    }
    return max;
  }
  public static String exercise12 (String longWord, String shortWord, int position) {
    return longWord.substring(0, position) + shortWord + longWord.substring(position);
  }
  public static String exercise13 (String longWord, String shortWord) {
    int removePos = longWord.indexOf(shortWord);
    return longWord.substring(0, removePos) + longWord.substring(removePos + shortWord.length());
  }
  public static String exercise14 (String firstWord, String secondWord, String shortWord) {
    return exercise12(secondWord, shortWord, firstWord.indexOf(shortWord));
  }
  public static String exercise15 (String str, int break1, int break2, int break3) {
    return str.substring(break2, break3) + str.substring(0, break1) + str.substring(break3) + str.substring(break1, break2);
  }
}