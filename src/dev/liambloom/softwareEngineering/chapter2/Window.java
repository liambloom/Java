package dev.liambloom.softwareEngineering.chapter2;

public class Window {
  public static void draw (int size) {
    for (int i = 0; i < 2; i++) {
      horizontalBar(size);
      for (int j = 0; j < 3; j++) {
        for (int c = 0; c < 3; c++) {
          System.out.print('|');
          Chapter2Exercises.line((size - 3) / 2, ' ');
        }
        System.out.println();
      }
    }
    horizontalBar(size);
  }
  private static void horizontalBar (int size) {
    for (int i = 0; i < 2; i++) {
      System.out.print('+');
      Chapter2Exercises.line((size - 3) / 2, '=');
    }
    System.out.println('+');
  }
}