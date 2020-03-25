public class Main {
  public static void main(String[] args) {
    final int foo = 12;
    if (Ask.forBoolean("Run test")) test();
    else {
      exercises();
    }
  }
  public static void test () {
    while (true) {
      System.out.println(Exercises.exercise24WithoutRegex(Ask.forString("str")));
    }
  }
  public static void exercises () {
    System.out.println(Exercises.exercise3(Ask.forInt("int n")));
  }
}