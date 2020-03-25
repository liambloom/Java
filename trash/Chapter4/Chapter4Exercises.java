public class Chapter4Exercises {
  public static void main (String[] args) {
    /*exercise7(5);
    exercise7(6);*/
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
}
