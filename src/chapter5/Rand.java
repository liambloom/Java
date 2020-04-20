package chapter5;

import java.util.Random;

public class Rand { // Cannot name this "Random" becuase it uses Random
  public static final Random r = new Random();
  public static int nextInt () {
    return r.nextInt();
  }
  public static int nextInt (int max) {
    return r.nextInt(max + 1);
  }
  public static int nextInt (int min, int max) { // min and max are inclusive
    return r.nextInt((max - min) + 1) + min;
  }
  public static boolean nextBoolean () {
    return r.nextBoolean();
  }
  public static char nextChar () {
    return (char) nextInt(127);
  }
  public static char nextChar (char min, char max) {
    return (char) nextInt((int) min, (int) max);
  }
  public static char nextLetter () {
    return nextChar('A', 'z');
  }
  public static char nextUpperCase () {
    return nextChar('A', 'Z');
  }
  public static char nextLowerCase () {
    return nextChar('a', 'z');
  }
  public static String nextString (int length, char min, char max) {
    String str = "";
    for (int i = 0; i < length; i++) {
      str += nextChar(min, max);
    }
    return str;
  }
  public static String nextWord (int length, boolean mixCases) {
    return nextString(length, mixCases ? 'A' : 'a', 'z');
  }
  public static String nextWord (int length) {
    return nextWord(length, false);
  }
}