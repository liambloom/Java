package dev.liambloom.softwareEngineering.chapter4;

public class Projects {
  public static void project6 () {
    int digit7 = 0;
    final String id = Ask.forToken("Please enter your six digit ID", "^-?\\d{6}$", "Your student ID must be 6 digits");
    for (int digit = 0; digit < id.length(); digit++) {
      digit7 += (digit + 1) * Character.getNumericValue(id.charAt(digit));
    }
    System.out.println("Check digit = " + digit7 % 10);
  }
}