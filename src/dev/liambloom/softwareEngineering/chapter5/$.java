package dev.liambloom.softwareEngineering.chapter5;

import dev.liambloom.softwareEngineering.Globals;

public class $ {

  public static boolean between (int min, int max, int c) {
    return min < c && c < max;
  }
  public static int mid (int a, int b, int c) {
    int min = Globals.Math.min(a, b, c);
    int max = Globals.Math.max(a, b, c);
    return between(min, max, a) ? a : between(min, max, b) ? b : c;
  }
  public static double logBase (int base, int val) {
    return Math.log(val) / Math.log(base);
  }
  public static double log2 (int val) {
    return logBase(2, val);
  }
}